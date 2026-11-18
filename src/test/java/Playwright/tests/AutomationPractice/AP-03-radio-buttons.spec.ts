// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Select one radio option at a time', () => {
  test('Select one radio option at a time', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Select Male and verify its selected state.
    const male = page.getByLabel('Male', { exact: true });
    const female = page.getByLabel('Female', { exact: true });
    const other = page.getByLabel('Other', { exact: true });
    await male.check();
    await expect(male).toBeChecked();
    // 2. Select Female, then Other, checking the state after each action.
    await female.check();
    await expect(female).toBeChecked();
    await expect(male).not.toBeChecked();
    await other.check();
    await expect(other).toBeChecked();
    await expect(female).not.toBeChecked();
    // 3. Reload and verify the initial state.
    await page.reload();
    await expect(male).not.toBeChecked();
    await expect(female).not.toBeChecked();
    await expect(other).not.toBeChecked();
  });
});
