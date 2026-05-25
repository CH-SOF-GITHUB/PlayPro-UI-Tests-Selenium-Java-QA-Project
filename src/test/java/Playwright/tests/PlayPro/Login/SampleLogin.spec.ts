import {test, expect} from "@playwright/test";

test("Client should login with valid credentials", async ({page}) => {
    // Go to the login page by URL
    await page.goto('https://demotenant.playpro.fr/connexion');
    // Fill in the email and password fields
    await page.getByPlaceholder("Adresse email").first().fill("demotenant3@yopmail.com");
    await page.getByPlaceholder("Mot de passe").first().fill("Admin1234!");
    // Click the login button
    await page.getByRole('button', {name: 'Me connecter'}).click();

    // Expect the user to be logged successfully
    await expect(page).toHaveURL('https://demotenant.playpro.fr/');
});
