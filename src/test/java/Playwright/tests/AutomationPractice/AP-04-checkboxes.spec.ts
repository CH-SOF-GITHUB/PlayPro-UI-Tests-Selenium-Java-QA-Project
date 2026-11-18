// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Toggle language and tool checkboxes', () => {
  test('Toggle language and tool checkboxes', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    const java = page.getByLabel('Java', { exact: true });
    const python = page.getByLabel('Python', { exact: true });
    const selenium = page.getByLabel('Selenium', { exact: true });
    // 1. Check Java, then Python, then Selenium.
    await java.check(); await python.check(); await selenium.check();
    // 2. Uncheck Python and verify the other two remain checked.
    await python.uncheck();
    await expect(java).toBeChecked(); await expect(python).not.toBeChecked(); await expect(selenium).toBeChecked();
    // 3. Toggle all options off and reload.
    await java.uncheck(); await selenium.uncheck();
    await page.reload();
    await expect(java).not.toBeChecked(); await expect(python).not.toBeChecked(); await expect(selenium).not.toBeChecked();
  });
});
