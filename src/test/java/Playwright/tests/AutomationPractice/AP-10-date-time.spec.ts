// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Enter date and time values', () => {
  test('Enter date and time values', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Identify the input type and format for each control.
    const inputs = page.locator('input[type="date"], input[type="time"], input[type="datetime-local"]');
    await expect(inputs).toHaveCount(3);
    // 2. Enter a valid date/time value appropriate to each type.
    for (let i = 0; i < await inputs.count(); i++) {
      const input = inputs.nth(i);
      const type = await input.getAttribute('type');
      const value = type === 'date' ? '2026-05-20' : type === 'time' ? '10:30' : '2026-05-20T10:30';
      await input.fill(value);
      await expect(input).toHaveValue(value);
    }
    // 3. Try an invalid/impossible date and boundary values (including the minimum/maximum if constraints are exposed).
    const date = page.locator('input[type="date"]').first();
    await date.fill('2026-02-30');
    // 4. Clear the fields and reload.
    await inputs.evaluateAll(items => items.forEach(item => ((item as HTMLInputElement).value = '')));
    await page.reload();
    await expect(inputs).toHaveCount(3);
  });
});
