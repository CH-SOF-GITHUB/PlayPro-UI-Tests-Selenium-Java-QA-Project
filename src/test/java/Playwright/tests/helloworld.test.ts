import {test, expect} from "@playwright/test";


test("Display The Message 'Hello World'", async ({ page }) => {
    console.log("Hello World from Playwright !");
    await page.goto("https://www.saucedemo.com/");
})
