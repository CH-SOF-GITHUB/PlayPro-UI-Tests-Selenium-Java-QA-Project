import {test, expect} from '@playwright/test';


test('Access to activite', async ({page}) => {
    // Increase timeout for this test
    test.setTimeout(250000);

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
    await expect(reservationLink).toBeVisible({timeout: 15000});
    await reservationLink.click();

    // Click on one activité and check if the page is correct
    const Activite = page.getByRole('button', {name: 'Créer une expérience'});
    await expect(Activite).toBeVisible({timeout: 15000});
    await Activite.click();

    // Expect the correct results
    await expect(page.getByText('Créer une expérience')).toBeVisible({ timeout: 15000});
})
