import {test, expect} from "@playwright/test";

// TC001
test('Access to the reclamation page', async ({page}) => {

    // Increase timeout for this test
    test.setTimeout(70000);

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

    // Click profile menu
    const profileButton = page.locator(
        'button.flex.items-center.gap-1.cursor-pointer.focus\\:outline-none'
    ).first();

    await expect(profileButton).toBeVisible();

    await profileButton.click();

    // Click "Mon profil"
    const monProfil = page.getByRole('menuitem', {
        name: 'Mon profil'
    });

    await expect(monProfil).toBeVisible();

    await monProfil.click();

    // Wait dashboard loaded
    await page.waitForURL('https://demotenant.playpro.fr/profile?tab=profile');

    // Click on tab
    const reclamationTab = page.getByText(/réclamation/i);

    await expect(reclamationTab).toBeVisible({timeout: 15000});

    await reclamationTab.click();

});

// TC02
test('Access to my bank cartes page', async ({page}) => {

    // Increase timeout for this test
    test.setTimeout(70000);

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

    // Click profile menu
    const profileButton = page.locator(
        'button.flex.items-center.gap-1.cursor-pointer.focus\\:outline-none'
    ).first();

    await expect(profileButton).toBeVisible();

    await profileButton.click();

    // Click "Mon profil"
    const monProfil = page.getByRole('menuitem', {
        name: 'Mon profil'
    });

    await expect(monProfil).toBeVisible();

    await monProfil.click();

    // Wait dashboard loaded
    await page.waitForURL('https://demotenant.playpro.fr/profile?tab=profile');

    // Click on tab
    const Tab2 = page.getByText(/Mes cartes bancaires/i);
    await expect(Tab2).toBeVisible({timeout: 15000});

    await Tab2.click();

});

// TC03
test('Access to my subscriptions page', async ({page}) => {

    // Increase timeout for this test
    test.setTimeout(70000);

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

    // Click profile menu
    const profileButton = page.locator(
        'button.flex.items-center.gap-1.cursor-pointer.focus\\:outline-none'
    ).first();

    await expect(profileButton).toBeVisible();

    await profileButton.click();

    // Click "Mon profil"
    const monProfil = page.getByRole('menuitem', {
        name: 'Mon profil'
    });

    await expect(monProfil).toBeVisible();

    await monProfil.click();

    // Wait dashboard loaded
    await page.waitForURL('https://demotenant.playpro.fr/profile?tab=profile');

    // Click on tab
    const Tab2 = page.getByText(/Mes abonnements/i);
    await expect(Tab2).toBeVisible({timeout: 15000});

    await Tab2.click();

});
