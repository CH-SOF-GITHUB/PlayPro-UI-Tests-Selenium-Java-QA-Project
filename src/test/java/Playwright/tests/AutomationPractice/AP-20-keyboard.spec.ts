// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Test keyboard input and key events', () => {
  test('Test keyboard input and key events', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    const input = page.getByPlaceholder('Press any key');
    // 1. Focus the field and type ordinary text.
    await input.fill('hello');
    await expect(input).toHaveValue('hello');
    // 2. Test representative keys such as Enter, Tab, Escape, and Backspace.
    await input.press('Backspace');
    await expect(input).toHaveValue('hell');
    await input.press('Enter');
    await input.press('Escape');
    // 3. Observe displayed values or key-event feedback and verify focus order.
    await input.press('Tab');
    await expect(input).not.toBeFocused();
  });
});
