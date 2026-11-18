// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Use the single-select dropdown', () => {
  test('Use the single-select dropdown', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Open the dropdown and inspect all available options.
    const select = page.locator('select').first();
    const options = await select.locator('option').evaluateAll(items => items.map(item => (item as HTMLOptionElement).value));
    expect(options.length).toBeGreaterThan(1);
    // 2. Select each valid option in turn and verify the displayed selection.
    for (const value of options.filter(Boolean)) {
      await select.selectOption(value);
      await expect(select).toHaveValue(value);
    }
    // 3. If a placeholder/default option is available, restore it.
    if (options.includes('')) await select.selectOption('');
  });
});
