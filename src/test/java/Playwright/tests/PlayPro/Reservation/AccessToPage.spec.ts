import {test, expect} from "@playwright/test";


test('User should access to reservation page', async ({page}) => {
    // Increase timeout for this test
    test.setTimeout(100000);

    // Open login page
    await page.goto('https://demotenant.playpro.fr/connexion');

    // Accept cookies
    await page.getByRole('button', {name: 'Accepter'}).click();

    // Login
    await page.getByPlaceholder('Adresse email').first().fill('demotenant3@yopmail.com');
    await page.getByPlaceholder('Mot de passe').first().fill('Admin1234!');
    await page.getByRole('button', {name: 'Me connecter'}).click();

    // Wait dashboard loaded
    // await page.waitForURL('https://demotenant.playpro.fr/', {waitUntil: 'load'});

    // Wait toast disappear
    await page.locator('.Toastify__toast-container').waitFor({
        state: 'hidden',
        timeout: 15000
    });

    // Click on "Réservations" in the sidebar
    const reservationLink = page.getByRole('link', {name: 'Réserver en Ligne'});
    await expect(reservationLink).toBeVisible();

    await reservationLink.click();

    const header1 = page.getByText("Nos activités");
    await expect(header1).toBeVisible({timeout: 15000});

    // await expect(page).toHaveURL("https://demotenant.playpro.fr/discover/reservation", {timeout: 15000});
})
