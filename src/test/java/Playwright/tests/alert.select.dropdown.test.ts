import {expect, test} from "@playwright/test";


test("Test Alert Select Dropdown 1", async ({page}) => {
    // go to lambdatest demo select dropdown
    await page.goto("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");
    // declare select dropdown of days
    const SelectDays = page.locator("#select-demo");
    // scroll into view if needed
    await SelectDays.scrollIntoViewIfNeeded();
    // select "Wednesday" from dropdown avec Option
    await SelectDays.selectOption("Wednesday");
    // wait for 5s
    await page.waitForTimeout(5000);
});

test("Test Alert Select Dropdown 2", async ({page}) => {
    // go to lambdatest demo select dropdown
    await page.goto("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");
    // declare select dropdown of countries
    await page.selectOption("#multi-select", [
        {label: "Florida"},
        {label: "Washington"}
    ]);
    await page.getByRole("button", {name: "First Selected"}).click();
    await page.getByRole("button", {name: "Get Last Selected"}).click();
    // wait for 2s
    // await page.waitForTimeout(2000);
    // const selectedFirst = page.locator("//p[@class='text-size-14']");
    // console.log(selectedFirst.inputValue());
    // await expect(selectedFirst).toHaveText("Washington");
    // wait for 2s
    await page.waitForTimeout(2000);
});
