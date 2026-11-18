// spec: specs/automation-practice.md
// seed: tests/seed.spec.ts
import { test, expect } from '@playwright/test';

test.describe('Upload a file and test replacement', () => {
  test('Upload a file and test replacement', async ({ page }) => {
    await page.goto('https://sqatools.in/automation-practice-page/');
    // 1. Select a supported file and inspect the selected filename and any response.
    const upload = page.locator('input[type="file"]').first();
    await upload.setInputFiles({ name: 'practice.txt', mimeType: 'text/plain', buffer: Buffer.from('harmless fixture') });
    await expect(upload).toHaveValue(/practice\.txt$/);
    // 2. Replace it with another file.
    await upload.setInputFiles({ name: 'replacement.txt', mimeType: 'text/plain', buffer: Buffer.from('replacement fixture') });
    await expect(upload).toHaveValue(/replacement\.txt$/);
  });
});
