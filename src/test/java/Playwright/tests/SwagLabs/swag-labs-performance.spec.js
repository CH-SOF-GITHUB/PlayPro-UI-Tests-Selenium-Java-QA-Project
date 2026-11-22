const { expect, test } = require('@playwright/test');

const SWAG_LABS_URL = 'https://www.saucedemo.com/';
const PASSWORD = 'secret_sauce';
const INVENTORY_SELECTOR = '.inventory_list';
const STANDARD_USER_MAX_MS = 7500;
const PERFORMANCE_GLITCH_USER_MAX_MS = 15000;

async function measureLoginAndInventory(page, username) {
  await page.goto(SWAG_LABS_URL);
  await page.locator('#user-name').fill(username);
  await page.locator('#password').fill(PASSWORD);

  const startedAt = Date.now();
  await page.locator('#login-button').click();
  await page.locator(INVENTORY_SELECTOR).waitFor({ state: 'visible' });

  return { username, durationMs: Date.now() - startedAt };
}

test('compare Swag Labs login performance and inventory web metrics', async ({ page }) => {
  const failedResponses = [];
  page.on('response', (response) => {
    if (response.status() >= 400) {
      failedResponses.push({ url: response.url(), status: response.status() });
    }
  });

  await page.addInitScript(() => {
    window.__swagLabsLcp = null;
    window.__swagLabsCls = 0;

    try {
      const lcpObserver = new PerformanceObserver((list) => {
        const entries = list.getEntries();
        const lastEntry = entries[entries.length - 1];
        if (lastEntry) window.__swagLabsLcp = lastEntry.startTime;
      });
      lcpObserver.observe({ type: 'largest-contentful-paint', buffered: true });

      const clsObserver = new PerformanceObserver((list) => {
        for (const entry of list.getEntries()) {
          if (!entry.hadRecentInput) window.__swagLabsCls += entry.value;
        }
      });
      clsObserver.observe({ type: 'layout-shift', buffered: true });
    } catch {
      // Unsupported performance entry types remain unset and are reported as such.
    }
  });

  const standard = await measureLoginAndInventory(page, 'standard_user');
  test.info().annotations.push({
    type: 'standard_user login + inventory duration',
    description: `${standard.durationMs} ms`,
  });

  // The inventory is a client-side route, so navigation timing belongs to its document load.
  const standardInventoryMetrics = await page.evaluate(() => {
    const navigation = performance.getEntriesByType('navigation')[0];
    return {
      ttfbMs: navigation ? navigation.responseStart - navigation.requestStart : null,
      domContentLoadedMs: navigation ? navigation.domContentLoadedEventEnd : null,
      loadEventEndMs: navigation && navigation.loadEventEnd > 0 ? navigation.loadEventEnd : null,
      lcpMs: window.__swagLabsLcp,
      cls: window.__swagLabsCls,
    };
  });

  const glitchPage = await page.context().newPage();
  glitchPage.on('response', (response) => {
    if (response.status() >= 400) {
      failedResponses.push({ url: response.url(), status: response.status() });
    }
  });
  const glitch = await measureLoginAndInventory(glitchPage, 'performance_glitch_user');
  test.info().annotations.push({
    type: 'performance_glitch_user login + inventory duration',
    description: `${glitch.durationMs} ms`,
  });

  const metrics = {
    target: SWAG_LABS_URL,
    loginAndInventory: { standardUser: standard, performanceGlitchUser: glitch },
    inventory: {
      measuredAfter: 'standard_user login',
      ...standardInventoryMetrics,
    },
    failedHttpResponses: failedResponses,
  };
  await test.info().attach('swag-labs-perf-metrics.json', {
    body: Buffer.from(JSON.stringify(metrics, null, 2)),
    contentType: 'application/json',
  });

  expect(
    standard.durationMs,
    `standard_user login and inventory render should be under ${STANDARD_USER_MAX_MS} ms`,
  ).toBeLessThan(STANDARD_USER_MAX_MS);
  expect(
    glitch.durationMs,
    `performance_glitch_user login and inventory render should be under ${PERFORMANCE_GLITCH_USER_MAX_MS} ms`,
  ).toBeLessThan(PERFORMANCE_GLITCH_USER_MAX_MS);
  expect(failedResponses, 'no network response should have an HTTP status of 400 or greater').toEqual([]);
});
