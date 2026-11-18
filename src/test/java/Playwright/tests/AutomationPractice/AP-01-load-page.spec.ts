// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Load the practice page and inspect its primary sections', () => {
  test('Load the practice page and inspect its primary sections', async ({ page }) => {
    // 1. Navigate to the URL in the seed.
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 2. Wait for the document to finish loading and inspect the page title and main heading.
    await expect(page.getByRole('heading', { name: 'Automation Practice Page', exact: true })).toBeVisible();
    await expect(page).toHaveTitle(/Automation Practice Page/i);
    // 3. Scroll from top to bottom through the page.
    await page.getByRole('heading', { name: 'Ultimate Automation Practice Page' }).scrollIntoViewIfNeeded();
    await expect(page.getByText('This is the bottom of the page for scrolling practice.', { exact: true })).toBeVisible();
  });
});
