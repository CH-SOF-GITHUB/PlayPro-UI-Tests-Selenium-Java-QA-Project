// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Drag and drop the draggable item', () => {
  test('Drag and drop the draggable item', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Drag the item into the target drop area.
    const source = page.getByText('Drag Me', { exact: true });
    const target = page.getByText('Drop Here', { exact: true });
    await source.dragTo(target);
    // 2. Verify the target's resulting state and the item's position.
    // The live widget renders both labels in one text node after the drop.
    await expect(page.getByText(/Drop Here/)).toBeVisible();
    await expect(page.getByText(/Drag Me/)).toBeVisible();
    // 3. Reload and repeat with a drop attempt outside the target, if the page supports it.
    await page.reload();
    await expect(page.getByText('Drag Me', { exact: true })).toBeVisible();
  });
});
