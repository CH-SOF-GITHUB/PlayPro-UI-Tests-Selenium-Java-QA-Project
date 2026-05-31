import {expect, test} from "@playwright/test";


test("Test Alert 1", async ({page}) => {
    // go to lambdatest demo alerts page
    await page.goto("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");

    page.on("dialog", async (alert) => {
        const text = alert.message();
        console.log(text);
        await alert.accept();
    });

    await page.getByRole('button', {name: 'Click Me'}).nth(0).click();
});

test("Test Alert 2.1", async ({page}) => {
    // go to lambdatest demo alerts page
    await page.goto("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
    //handle alert actions
    page.on("dialog", async (alert) => {
        const msg = alert.message();
        if (msg == "Press a button!") {
            await alert.accept();
        }
    });

    await page.getByRole('button', {name: 'Click Me'}).nth(1).click();
    const confirmMsg = page.locator("#confirm-demo");
    console.log("Msg Text after confirm: " + await confirmMsg.textContent());
    await expect(confirmMsg).toHaveText("You pressed OK!");
});

test("Test Alert 2.2", async ({page}) => {
    // go to lambdatest demo alerts page
    await page.goto("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
    //handle alert actions
    page.on("dialog", async (alert) => {
        const msg = alert.message();
        console.log(msg + "\n");
        if (msg == "Press a button!") {
            await alert.dismiss();
        }
    });

    await page.getByRole('button', {name: 'Click Me'}).nth(1).click();
    const confirmMsg = page.locator("#confirm-demo");
    console.log("Msg Text after confirm: " + await confirmMsg.textContent());
    await expect(confirmMsg).toHaveText("You pressed Cancel!");
});

test("Test Alert 3.1", async ({page}) => {
    // go to lambdatest demo alerts page
    await page.goto("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
    //handle alert actions
    let expectedName = "Chaker";
    page.on("dialog", async (prompt) => {
        const msg = prompt.message();
        console.log("prompt msg: " + msg + "\n");
        if (msg == "Please enter your name") {
            await prompt.accept(expectedName);
        }
    });

    await page.getByRole('button', {name: 'Click Me'}).nth(2).click();
    const promptMsg = page.locator("#prompt-demo");
    console.log("Msg Text after prompt: " + await promptMsg.textContent());
    await expect(promptMsg).toContainText(expectedName);
});
