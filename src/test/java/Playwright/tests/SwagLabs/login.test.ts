import {test, expect} from "@playwright/test";

// TC01: Cas UI/UX:
test("Chargement de la page Login", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    // Assertions
    await expect(page).toHaveTitle("Swag Labs");
    const headerTitle = await page.locator(".login_logo").textContent();
    console.log("Login Page Title : " + headerTitle);
    await expect(page.locator(".login_logo")).toHaveText("Swag Labs");
});

// Cas positives
//TC02
test("Connexion avec identifiants valides", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.fill("#user-name", "standard_user");
    await page.fill("#password", "secret_sauce");
    await page.click("#login-button");
    // Assertions
    await expect(page).toHaveURL("https://www.saucedemo.com/inventory.html");
    await expect(page.locator(".app_logo")).toHaveText("Swag Labs");
});

// TC03
test("Connexion avec problem_user", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.fill("#user-name", "problem_user");
    await page.fill("#password", "secret_sauce");
    await page.click("#login-button");
    // Assertions
    await expect(page).toHaveURL("https://www.saucedemo.com/inventory.html");
    await expect(page.locator(".app_logo")).toHaveText("Swag Labs");
});

// TC04
test("Connexion avec performance_glitch_user", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.fill("#user-name", "performance_glitch_user");
    await page.fill("#password", "secret_sauce");
    await page.click("#login-button");
    // Assertions
    await expect(page).toHaveURL("https://www.saucedemo.com/inventory.html");
    await expect(page.locator(".app_logo")).toHaveText("Swag Labs");
});

// TC05
test("Déconnexion puis reconnexion", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.fill("#user-name", "standard_user");
    await page.fill("#password", "secret_sauce");
    await page.click("#login-button");
    // open the menu
    await page.click("#react-burger-menu-btn");
    // click on Logout
    const logoutLink = page.getByText("Logout");
    await logoutLink.waitFor({state: 'visible'}); // Attend que le menu soit ouvert et visible
    await logoutLink.click();
    // Reconnexion
    await expect(page).toHaveURL("https://www.saucedemo.com/");
    await page.fill("#user-name", "standard_user");
    await page.fill("#password", "secret_sauce");
    await page.click("#login-button");
    // Assertions
    await expect(page).toHaveURL("https://www.saucedemo.com/inventory.html");
    await expect(page.locator(".app_logo")).toHaveText("Swag Labs");
});

// Cas négatifs
// TC06
test("Utilisateur bloqué", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.fill("#user-name", "locked_out_user");
    await page.fill("#password", "secret_sauce");
    await page.click("#login-button");
    let errorMsg = await page.locator("[data-test='error']").textContent();
    expect(errorMsg).toBe("Epic sadface: Sorry, this user has been locked out.");
});

// TC07
test("Mot de passe incorrect", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.fill("#user-name", "locked_out_user");
    await page.fill("#password", "secret_sau");
    await page.click("#login-button");
    let errorMsg = await page.locator("[data-test='error']").textContent();
    expect(errorMsg).toBe("Epic sadface: Username and password do not match any user in this service");
});

// TC08
test("Utilisateur inexistant", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.fill("#user-name", "locked_out__user");
    await page.fill("#password", "secret_sauce");
    await page.click("#login-button");
    let errorMsg = await page.locator("[data-test='error']").textContent();
    expect(errorMsg).toBe("Epic sadface: Username and password do not match any user in this service");
});

// TC09
test("Les deux champs vides", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.click("#login-button");
    let errorMsg = await page.locator("[data-test='error']").textContent();
    expect(errorMsg).toBe("Epic sadface: Username is required");
});

//TC10
test("Username vide", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.fill("#password", "secret_sauce");
    await page.click("#login-button");
    let errorMsg = await page.locator("[data-test='error']").textContent();
    expect(errorMsg).toBe("Epic sadface: Username is required");
});

//TC11
test("Password vide", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.fill("#user-name", "standard_user");
    await page.click("#login-button");
    let errorMsg = await page.locator("[data-test='error']").textContent();
    expect(errorMsg).toBe("Epic sadface: Password is required");
});

// TC12
test("Accès direct sans authentification", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.goto("https://www.saucedemo.com/inventory.html");
    let errorMsg = await page.locator("[data-test='error']").textContent();
    expect(errorMsg).toBe("Epic sadface: You can only access '/inventory.html' when you are logged in.");
});

// TC13
test("Retour arrière après logout", async ({page}) => {
    await page.goto("https://www.saucedemo.com/");
    await page.fill("#user-name", "standard_user");
    await page.fill("#password", "secret_sauce");
    await page.click("#login-button");
    // open the menu
    await page.click("#react-burger-menu-btn");
    // click on Logout
    const logoutLink = page.getByText("Logout");
    await logoutLink.waitFor({state: 'visible'}); // Attend que le menu soit ouvert et visible
    await logoutLink.click();
    // go back in browser
    await page.goBack();
    // Assertions
    let errorMsg = await page.locator("[data-test='error']").textContent();
    expect(errorMsg).toBe("Epic sadface: You can only access '/inventory.html' when you are logged in.");
    await expect(page).toHaveURL("https://www.saucedemo.com/");
    expect(page.url()).not.toBe("https://www.saucedemo.com/inventory.html");
});
