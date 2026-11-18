// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Verify page navigation links and scrolling link', () => {
  test('Verify page navigation links and scrolling link', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Activate Go to Bottom and verify the viewport moves to the bottom section.
    await page.getByRole('link', { name: 'Go to Bottom' }).click();
    await expect(page.getByText('This is the bottom of the page for scrolling practice.', { exact: true })).toBeInViewport();
    // 2. Verify the Open Google link target without leaving the test context; open it in an isolated tab if it navigates externally.
    const google = page.getByRole('link', { name: 'Open Google' });
    await expect(google).toHaveAttribute('href', /google\.com/);
    // 3. Inspect internal navigation links such as Dummy Page and Login Page, and confirm their targets load when followed in isolated contexts.
    await expect(page.getByRole('link', { name: 'Dummy Page' }).first()).toHaveAttribute('href', /./);
    await expect(page.getByRole('link', { name: 'Login Page' }).first()).toHaveAttribute('href', /./);
  });
});
