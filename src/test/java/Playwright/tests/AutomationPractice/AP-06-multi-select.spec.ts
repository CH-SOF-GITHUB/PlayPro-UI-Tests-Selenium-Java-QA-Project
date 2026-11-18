// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Use and clear the multi-select dropdown', () => {
  test('Use and clear the multi-select dropdown', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Inspect available options.
    const select = page.locator('select[multiple]').first();
    const options = await select.locator('option').evaluateAll(items => items.map(item => (item as HTMLOptionElement).value).filter(Boolean));
    expect(options.length).toBeGreaterThanOrEqual(2);
    // 2. Select two or more distinct values using the control's supported selection method.
    await select.selectOption(options.slice(0, 2));
    await expect(select).toHaveValues(options.slice(0, 2));
    // 3. Deselect one value, then clear all selections if supported.
    await select.selectOption(options[0]);
    await expect(select).toHaveValues([options[0]]);
    await select.selectOption([]);
    // 4. Reload the page.
    await page.reload();
    await expect(select).toHaveValues([]);
  });
});
