// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Validate the comment form and required fields', () => {
  test('Validate the comment form and required fields', async ({ page }) => {
    test.fixme(); // The current practice page has no comment form in its rendered content.
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Locate Name*, Email*, Website, comment/body and save-details controls.
    const name = page.getByLabel('Name *');
    const email = page.getByLabel('Email *');
    const website = page.getByLabel('Website');
    await expect(name).toBeVisible(); await expect(email).toBeVisible(); await expect(website).toBeVisible();
    // 2. Submit with all required values blank.
    // 3. Enter a name but an invalid email and submit.
    await name.fill('Synthetic QA');
    await email.fill('invalid-email');
    // 4. Enter valid-format synthetic name/email, optional website, and comment; submit.
    await email.fill('qa@example.test');
    await website.fill('https://example.test');
    const comment = page.locator('textarea').last();
    await comment.fill('Automated practice page comment test.');
    // 5. Toggle Save my name, email, and website in this browser and observe its effect in an isolated context.
    await page.getByLabel(/Save my name, email, and website in this browser/).check();
    await expect(page.getByLabel(/Save my name, email, and website in this browser/)).toBeChecked();
  });
});
