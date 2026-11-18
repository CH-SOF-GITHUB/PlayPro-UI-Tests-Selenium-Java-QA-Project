// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Test hover interaction', () => {
  test('Test hover interaction', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Record the initial state of Hover Over Me.
    const hover = page.getByRole('button', { name: 'Hover Over Me' });
    await expect(hover).toBeVisible();
    // 2. Move the pointer over the button and inspect any revealed content or style change.
    await hover.hover();
    await expect(hover).toBeVisible();
    // 3. Move the pointer away and verify the original state returns.
    await page.mouse.move(0, 0);
    await expect(hover).toBeVisible();
  });
});
