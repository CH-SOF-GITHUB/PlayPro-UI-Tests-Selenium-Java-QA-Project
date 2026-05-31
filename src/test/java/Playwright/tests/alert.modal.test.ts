import {expect, test} from "@playwright/test";


test("Alert Modal Test 1.0", async ({page}) => {
    // go to alert modal lambdatest page demo
    await page.goto("https://www.lambdatest.com/selenium-playground/bootstrap-modal-demo");

    // click on launch modal button
    const LaunchModalBtn = page.locator("button[data-target='#myModal']");
    await LaunchModalBtn.click();

    // wait for 3 seconds
    await page.waitForTimeout(3000);

    // declare modal
    const Modal = page.locator("#myModal");

    const role = await Modal.getAttribute("role");
    expect(role).toBe("dialog");

    const modalText = page.locator("div[id='myModal'] p");
    await expect(modalText).toHaveText("This is the place where the content for the modal dialog displays");

    const SaveChangesBtn = page.getByRole('button', {name: 'Save Changes'});
    await SaveChangesBtn.click();

    // wait for 2 seconds
    await page.waitForTimeout(2000);

    const style2 = await Modal.getAttribute("style");
    expect(style2).toBe("display: none;");
});
