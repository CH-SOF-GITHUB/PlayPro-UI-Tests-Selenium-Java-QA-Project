import {test, expect} from "@playwright/test";

test("Client should not login with invalid credentials", async ({page}) => {
    // Go to the login page by URL
    await page.goto('https://demotenant.playpro.fr/connexion');
    // Fill in the email and password fields
    await page.getByPlaceholder("Adresse email").first().fill("demotenant33@yopmail.com");
    await page.getByPlaceholder("Mot de passe").first().fill("Admin1234!");
    // Click the login button
    await page.getByRole('button', {name: 'Me connecter'}).click();

    // Expect the user to be logged successfully
    await expect(page.getByText("Veuillez vérifier votre email/ mot de passe")).toBeVisible();
});
