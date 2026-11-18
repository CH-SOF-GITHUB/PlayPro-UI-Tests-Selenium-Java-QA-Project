// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Enter and edit text fields and address', () => {
  test('Enter and edit text fields and address', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Enter a normal username and password in the corresponding fields.
    const username = page.getByPlaceholder('Enter username');
    const password = page.getByPlaceholder('Enter password');
    await username.fill('playwright-user');
    await password.fill('synthetic-secret');
    await expect(username).toHaveValue('playwright-user');
    await expect(password).toHaveAttribute('type', 'password');
    // 2. Enter a multiline address if the address control supports line breaks.
    const address = page.getByRole('textbox', { name: 'Address' });
    await address.fill('12 Test Road, Lagos');
    // 3. Replace each value, then clear the fields.
    await username.fill('edited-user');
    await password.fill('changed-secret');
    await address.fill('');
    await expect(username).toHaveValue('edited-user');
    await expect(password).toHaveValue('changed-secret');
    await expect(address).toHaveValue('');
    // 4. Repeat with punctuation, non-ASCII characters, and long input.
    await username.fill('qa+test_é-01');
    await expect(username).toHaveValue('qa+test_é-01');
  });
});
