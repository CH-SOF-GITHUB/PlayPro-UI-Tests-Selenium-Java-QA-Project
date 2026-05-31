import {chromium, expect, test} from "@playwright/test";

test("interaction with inputs", async ({page}) => {

    // STEP 1: go to lambdatest demo ecommerce website
    await page.goto("https://www.lambdatest.com/selenium-playground/simple-form-demo");

    // STEP 2: declare Message Input
    const messageInput = page.locator("input#user-message");
    // scroll messageInput into view, unless it is visible
    await messageInput.scrollIntoViewIfNeeded();
    //console.log(await messageInput.getAttribute("placeholder"));
    await expect(messageInput).toHaveAttribute("placeholder", "Please enter your Message");
    // get the input value of input message before Test
    console.log("Before Test : " + await messageInput.inputValue());
    // STEP 3: fill the message input with "Welcome to LambdaTest"
    await messageInput.fill("Welcome to LambdaTest");
    // get the input value of input message after Test
    console.log("After Test : " + await messageInput.inputValue());
});

test("sum de 2 nombres", async ({page}) => {
    // go to lambdatest demo form site
    await page.goto("https://www.lambdatest.com/selenium-playground/simple-form-demo");

    // delare 2 nombres inputs
    const NbInput1 = page.locator("#sum1");
    const NbInput2 = page.locator("#sum2");

    // declare GetValues Button
    const GetValuesBtn = page.locator("button[class='mt-20 mb-10 bg-lambda-900 hover:bg-transparent hover:text-lambda-900 border border-lambda-900 text-white rounded p-10 focus:outline-none w-180 ']");
    // set the values of 2 numbers
    let nb1 = 15;
    let nb2 = 25;
    await NbInput1.fill("" + nb1);
    await NbInput2.fill("" + nb2);
    await GetValuesBtn.click();

    // declare total result
    const ResultTotal = page.locator("#addmessage");
    console.log(await ResultTotal.textContent());
    let ExpectedResult = nb1 + nb2;
    await expect(ResultTotal).toHaveText("" + ExpectedResult);
});

test("Test Checkbox", async ({page}) => {
    // go to checkbox lambdatest demo page
    await page.goto("https://www.lambdatest.com/selenium-playground/checkbox-demo");
    const SingleCheckbox = page.$("//label[normalize-space()='Click on check box']");
    SingleCheckbox.then(async c => {
        const isChecked1 = await c.isChecked();
        console.log("Before checkbox status : ", isChecked1);
        expect(isChecked1).toBeFalsy();
        await c.check();
        const isChecked2 = await c.isChecked();
        console.log("After checkbox status : ", isChecked2);
        expect(isChecked2).toBeTruthy();
    })
        .catch(error => {
            console.error("Une erreur s'est produite :", error);
        });

    await page.waitForTimeout(3000);
    const SuccessCheckboxMsg = page.locator("div[class='mt-40'] p");
    let ExpectedCheckboxMsg = "Checked!";
    // <p> to have text </p>
    await expect(SuccessCheckboxMsg).toHaveText(ExpectedCheckboxMsg);
});
