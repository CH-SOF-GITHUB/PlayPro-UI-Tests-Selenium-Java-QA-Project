import {expect, test} from "@playwright/test";

test('Access to login page - Method 1', async ({page}) => {
    // Go to the login page by URL
    await page.goto('https://demotenant.playpro.fr/connexion');

    // Expect the title "to be equal to" a string.
    await expect(page.getByText("Connexion")).toBeVisible();
});

test('Access to login page - Method 2', async ({page}) => {
    // Go to the login page by URL
    await page.goto('https://demotenant.playpro.fr/connexion');

    // Expect the title "to be equal to" a string.
    await expect(page.getByText("Créer un compte")).toBeVisible();
});
