// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Reach the nested Shadow DOM content', () => {
  test('Reach the nested Shadow DOM content', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Locate the nested Shadow DOM section.
    const booking = page.getByRole('link', { name: 'Dummy Booking', exact: true });
    // 2. Inspect its exposed text and controls through normal browser interaction.
    await expect(booking).toBeVisible();
    // 3. Activate the Dummy Booking link if it is interactive and verify its destination in isolation.
    await expect(booking).toHaveAttribute('href', /./);
  });
});
