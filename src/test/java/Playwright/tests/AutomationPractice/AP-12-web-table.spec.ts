// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Read and validate the web table', () => {
  test('Read and validate the web table', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Locate the Web Table and inspect its headers and rows.
    const table = page.getByRole('table').first();
    await expect(table.getByRole('columnheader')).toHaveText(['ID', 'Name', 'Role']);
    // 2. Verify the displayed records: IDs 1–3 and the associated names and roles.
    await expect(table.getByRole('row')).toHaveCount(4);
    // 3. Check that each row's cells align with the correct headers.
    await expect(table.getByRole('row').nth(1)).toContainText('1');
    await expect(table.getByRole('row').nth(1)).toContainText('Deepesh');
    await expect(table.getByRole('row').nth(1)).toContainText('Trainer');
    await expect(table.getByRole('row').nth(2)).toContainText('Rahul');
    await expect(table.getByRole('row').nth(3)).toContainText('Anita');
  });
});
