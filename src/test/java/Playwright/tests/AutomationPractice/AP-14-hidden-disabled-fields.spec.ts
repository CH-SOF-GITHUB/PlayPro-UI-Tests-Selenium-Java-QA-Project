// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Confirm hidden and enabled/disabled field behavior', () => {
  test('Confirm hidden and enabled/disabled field behavior', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Inspect the Hidden Element control using normal user-visible interaction only.
    const hidden = page.locator('input[type="hidden"]').first();
    // 2. Verify that it is not visible or actionable if intentionally hidden.
    await expect(hidden).toBeHidden();
    // 3. Inspect the Enabled & Disabled Fields section and attempt to focus and type into each field.
    const disabled = page.locator('input:disabled').first();
    const enabled = page.locator('input:not([type="hidden"]):not(:disabled)').last();
    await expect(disabled).toBeDisabled();
    await expect(enabled).toBeEnabled();
  });
});
