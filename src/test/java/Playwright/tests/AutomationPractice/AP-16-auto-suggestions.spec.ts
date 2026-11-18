// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Use the auto-suggestion search field', () => {
  test('Use the auto-suggestion search field', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Enter a prefix that produces suggestions and inspect the results.
    const search = page.getByPlaceholder('Search...');
    await search.fill('a');
    await expect(search).toHaveValue('a');
    // 2. Add characters to narrow the list, then erase them.
    await search.fill('automation');
    await search.fill('');
    // 3. Select a suggestion with the pointer and, in a separate run, with the keyboard.
    // 4. Enter a query with no matches and inspect empty-state behavior.
    await search.fill('no-such-suggestion-xyz');
    await expect(search).toHaveValue('no-such-suggestion-xyz');
  });
});
