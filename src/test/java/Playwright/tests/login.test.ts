import { chromium, test } from "@playwright/test";


test("Login test demo", async () => {
    // declare the browser instance
    const browser = await chromium.launch({
        headless: false
    });
    // declare a new browser context
    const context = await browser.newContext({
        viewport: null
    });
    // declare a page object
    const page = await context.newPage();

    // STEP 1: ouvrir la page de lambdatest demo web site
    await page.goto("https://ecommerce-playground.lambdatest.io/");
    // STEP 2: hover en Lien My account avec css selector
    await page.hover("body > div:nth-child(1) > div:nth-child(5) > header:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > nav:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(6) > a:nth-child(1) > div:nth-child(2) > span:nth-child(1)");
    // STEP 3: cliquer sur le login link avec text selector
    // await page.click("text=Login"); 
    await page.click("'Login'");
    // STEP 4: saisir email valid
    await page.fill("#input-email", "demolambdatestio@yopmail.com");
    // STEP 5: saisir mot de passe valid
    await page.fill("#input-password", "Admin123!");
    // STEP 6: cliquer sur le bouton login
    await page.click("input[value='Login']");

    // wait for 7s
    await page.waitForTimeout(7000);

    // essayer d'ouvrir une noouvel browser = newContext
    const newContext = await browser.newContext();
    const page1 = await newContext.newPage();
    await page1.goto("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");

    // wait for 5s
    await page1.waitForTimeout(5000);
});