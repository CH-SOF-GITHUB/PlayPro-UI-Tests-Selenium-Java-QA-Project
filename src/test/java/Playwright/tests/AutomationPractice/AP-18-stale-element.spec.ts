// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Recover from stale-element replacement', () => {
  test('Recover from stale-element replacement', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Inspect the Click to Replace Me control and nearby response area.
    const original = page.getByRole('button', { name: 'Click to Replace Me' });
    await expect(original).toBeVisible();
    // 2. Activate it once and locate the replacement control/content.
    await original.click();
    await expect(page.getByRole('button', { name: 'New Button (Old is Stale)' })).toBeVisible();
  });
});
