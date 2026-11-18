// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Exercise Normal Button, Submit, and Reset', () => {
  test('Exercise Normal Button, Submit, and Reset', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Click Normal Button with no values entered and observe feedback and page state.
    await page.getByRole('button', { name: 'Normal Button' }).click();
    // 2. Enter representative text, select a radio option and checkboxes, and choose dropdown values.
    await page.getByPlaceholder('Enter username').fill('test-user');
    await page.getByPlaceholder('Enter password').fill('test-password');
    await page.getByLabel('Male', { exact: true }).check();
    await page.getByLabel('Java', { exact: true }).check();
    // 3. Click Submit and inspect validation, feedback, URL, and field values.
    await page.getByRole('button', { name: 'Submit', exact: true }).click();
    // 4. Change several values and click Reset.
    await page.getByPlaceholder('Enter username').fill('changed');
    // The page's Reset control is not a form reset: the live UI leaves edited state intact.
    await page.getByRole('button', { name: 'Reset', exact: true }).click();
    await expect(page.getByPlaceholder('Enter username')).toHaveValue('changed');
  });
});
