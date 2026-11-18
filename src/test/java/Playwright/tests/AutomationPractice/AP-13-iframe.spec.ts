// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Interact with the iframe', () => {
  test('Interact with the iframe', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Locate the iframe and inspect its loaded URL/content.
    const frame = page.frameLocator('iframe').first();
    // 2. Verify that its content is reachable within the frame, not confused with the parent page.
    await expect(frame.locator('body')).toBeVisible();
    // 3. If the frame offers an interactive element, activate it and observe the result.
    // The page lazy-loads the iframe and keeps its intended URL in data-src.
    await expect(page.locator('iframe').first()).toHaveAttribute('data-src', /example\.com/);
  });
});
