import {test, expect} from '@playwright/test';


test('Click to profile of user', async ({page}) => {
    /* Desktop viewport
    await page.setViewportSize({
        width: 1920,
        height: 1080
    }); */
    // Go to the login page by URL
    await page.goto('https://demotenant.playpro.fr/connexion');
    // Close the cookie
    await page.getByRole('button', {name: 'Accepter'}).click();
    // Fill in the email and password fields
    await page.getByPlaceholder("Adresse email").first().fill("demotenant3@yopmail.com");
    await page.getByPlaceholder("Mot de passe").first().fill("Admin1234!");
    // Click the login button
    await page.getByRole('button', {name: 'Me connecter'}).click();
    // Wait for successful login
    await page.waitForURL('https://demotenant.playpro.fr/');
    // Wait a little
    await page.waitForTimeout(7000);
    // CLICK USER PROFILE BUTTON
    await page.locator('button.flex.items-center.gap-1.cursor-pointer.focus\\:outline-none').click();
    // Print A MESSAGE TO THE CONSOLE
    console.log("Clicked on the user profile icon");
})

test('Access to the profile page', async ({page}) => {
    await page.goto('https://demotenant.playpro.fr/connexion');
    // Close the cookie
    await page.getByRole('button', {name: 'Accepter'}).click();
    // Fill in the email and password fields
    await page.getByPlaceholder("Adresse email").first().fill("demotenant3@yopmail.com");
    await page.getByPlaceholder("Mot de passe").first().fill("Admin1234!");
    // Click the login button
    await page.getByRole('button', {name: 'Me connecter'}).click();
    // Time sleep to load page
    await page.waitForTimeout(7000);
    // CLICK USER PROFILE BUTTON
    await page.locator('button.flex.items-center.gap-1.cursor-pointer.focus\\:outline-none').click();
    // Click en sub menu "Mon profil"
    await page.getByRole('menuitem', {name: 'Mon profil'}).click({timeout: 35000});
    // Expect the URL to be the profile page URL
    await expect(page).toHaveURL('https://demotenant.playpro.fr/profile?tab=profile', {timeout: 10000});
})
