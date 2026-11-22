const { expect, test } = require('@playwright/test');

const SWAG_LABS_URL = 'https://www.saucedemo.com/';
const REPETITIONS = 3;

test('measure cold initial loads of the Swag Labs login page', async ({ browser }) => {
  const runs = [];
  const failedResponses = [];

  for (let runNumber = 1; runNumber <= REPETITIONS; runNumber += 1) {
    // Each new context has an independent, cold browser cache.
    const context = await browser.newContext();
    const page = await context.newPage();

    page.on('response', (response) => {
      if (response.status() >= 400) {
        failedResponses.push({
          run: runNumber,
          url: response.url(),
          status: response.status(),
        });
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

    const startedAt = Date.now();
    await page.goto(SWAG_LABS_URL);
    await page.locator('.login_logo').waitFor({ state: 'visible' });
    await page.locator('#user-name').waitFor({ state: 'visible' });
    await page.locator('#login-button').waitFor({ state: 'visible' });
    const usableDurationMs = Date.now() - startedAt;

    const pageMetrics = await page.evaluate(() => {
      const navigation = performance.getEntriesByType('navigation')[0];
      const resources = performance.getEntriesByType('resource');
      return {
        title: document.title,
        ttfbMs: navigation ? navigation.responseStart - navigation.requestStart : null,
        domContentLoadedMs: navigation ? navigation.domContentLoadedEventEnd : null,
        loadEventEndMs: navigation && navigation.loadEventEnd > 0 ? navigation.loadEventEnd : null,
        lcpMs: window.__swagLabsLcp,
        cls: window.__swagLabsCls,
        resources: resources.map((resource) => ({
          name: resource.name,
          durationMs: resource.duration,
          transferSizeBytes: resource.transferSize,
        })),
      };
    });

    const run = { runNumber, usableDurationMs, ...pageMetrics };
    runs.push(run);
    test.info().annotations.push({
      type: `PERF-01 cold login page load, run ${runNumber}`,
      description: `${usableDurationMs} ms until login page is usable`,
    });

    await context.close();
  }

  const sortedDurations = runs.map((run) => run.usableDurationMs).sort((a, b) => a - b);
  const summary = {
    target: SWAG_LABS_URL,
    scenario: 'PERF-01 initial login page load; new context and cold cache per run',
    browserVersion: browser.version(),
    repetitions: REPETITIONS,
    summary: {
      minMs: sortedDurations[0],
      medianMs: sortedDurations[Math.floor(sortedDurations.length / 2)],
      maxMs: sortedDurations[sortedDurations.length - 1],
    },
    runs,
    failedHttpResponses: failedResponses,
  };

  await test.info().attach('swag-labs-login-load-metrics.json', {
    body: Buffer.from(JSON.stringify(summary, null, 2)),
    contentType: 'application/json',
  });

  expect(failedResponses, 'no response should have an HTTP status of 400 or greater').toEqual([]);
});
