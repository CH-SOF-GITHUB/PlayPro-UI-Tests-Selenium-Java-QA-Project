// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Handle JavaScript simple, confirm, and prompt alerts', () => {
  test('Handle JavaScript simple, confirm, and prompt alerts', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Click Simple Alert and record the dialog type and message; accept it.
    page.once('dialog', async dialog => { expect(dialog.type()).toBe('alert'); await dialog.accept(); });
    await page.getByRole('button', { name: 'Simple Alert' }).click();
    // 2. Click Confirm Alert; test both Accept and Dismiss in separate fresh runs.
    page.once('dialog', async dialog => { expect(dialog.type()).toBe('confirm'); await dialog.accept(); });
    await page.getByRole('button', { name: 'Confirm Alert' }).click();
    await page.reload();
    page.once('dialog', async dialog => { expect(dialog.type()).toBe('confirm'); await dialog.dismiss(); });
    await page.getByRole('button', { name: 'Confirm Alert' }).click();
    // 3. Click Prompt Alert; test entering a normal value and accepting, then dismissing without a value in a separate run.
    await page.reload();
    page.once('dialog', async dialog => { expect(dialog.type()).toBe('prompt'); await dialog.accept('sample'); });
    await page.getByRole('button', { name: 'Prompt Alert' }).click();
    await expect(page.getByRole('button', { name: 'Prompt Alert' })).toBeVisible();
  });
});
