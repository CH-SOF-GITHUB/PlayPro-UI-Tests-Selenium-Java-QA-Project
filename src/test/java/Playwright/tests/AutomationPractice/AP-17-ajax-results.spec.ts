// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Exercise AJAX success and simulated network failure', () => {
  test('Exercise AJAX success and simulated network failure', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Click Load Success and wait for its response area to settle.
    await page.getByRole('button', { name: 'Load Success' }).click();
    await expect(page.getByRole('button', { name: 'Simulate Network Failure' })).toBeVisible();
    // 2. Record successful status/message and verify repeated activation behavior.
    await page.getByRole('button', { name: 'Load Success' }).click();
    // 3. Reload for an independent run, click Simulate Network Failure, and wait for error handling.
    await page.reload();
    await page.getByRole('button', { name: 'Simulate Network Failure' }).click();
    await expect(page.getByRole('button', { name: 'Load Success' })).toBeVisible();
  });
});
