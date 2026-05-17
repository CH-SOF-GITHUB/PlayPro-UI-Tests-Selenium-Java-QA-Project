const {Builder, By, Key, until, Browser} = require('selenium-webdriver');
const assert = require("assert");
const {expect} = require('chai');
const chrome = require('selenium-webdriver/chrome');
const logging = require('selenium-webdriver/lib/logging')
logger = logging.getLogger('webdriver')
logger.setLevel(logging.Level.INFO)
let driver;
let InitialDriver;


async function getDriver() {

    let options = new chrome.Options();

    options.setChromeBinaryPath('/usr/bin/chromium-browser');

    options.addArguments(
        '--headless=new',
        '--no-sandbox',
        '--disable-dev-shm-usage',
        '--window-size=1920,1080'
    );

    let service = new chrome.ServiceBuilder('/usr/bin/chromedriver');

    InitialDriver = await new Builder()
        .forBrowser(Browser.CHROME)
        .setChromeOptions(options)
        .setChromeService(service)
        .build();

    return InitialDriver;
}


(async function example() {
    // code of the test
    driver = await getDriver();
    try {
        // Test 1 : Navigate to PlayPro V3 demo tenant
        // maximize the window
        await driver.manage().window().maximize();
        // navigate to the page
        await driver.get('https://demotenant.playpro.fr/');
        // get title of page
        const pageTitle1 = await driver.getTitle();
        // display the title of the page
        console.log("Page Title 1 is : " + pageTitle1);
        // assert that the title is correct
        assert.ok("DEMO TENANT" === pageTitle1, "Title is not correct");
        // chai expect test
        expect(pageTitle1).to.equal("DEMO TENANT");

        // Test 2 (login Test)
        //navigate to PlayPro V3 login page
        await driver.get("https://demotenant.playpro.fr/connexion");
        // Select web elements and fill them out and submit the form
        const AcceptBtn = await driver.wait(until.elementLocated(By.xpath('//*[@data-testid = \'cookie-banner-footer-sub-group\']//*[@data-testid = \'cookie-banner-accept-button\']')), 25000);
        AcceptBtn.click();
        const emailInput = await driver.wait(until.elementLocated(By.name('email')), 25000);
        await emailInput.sendKeys("demotenant3@yopmail.com");
        const pwdInput = await driver.wait(until.elementLocated(By.name('password')), 25000);
        await pwdInput.sendKeys("Admin1234!");
        const loginBtn = await driver.wait(until.elementLocated(By.xpath("//button[@type='submit' and normalize-space()='Me connecter']")), 25000);
        await loginBtn.click();
        // Time sleep
        await driver.sleep(5000);
        //On successful login get page title and Check page title, to confirm login was successful
        const pageTitle2 = await driver.getTitle();
        // display the title of the page
        console.log("Page Title 2 is : " + pageTitle2);
        // assert using chai
        expect(pageTitle2).to.equal("DEMO TENANT");
        //Check if redirect to login page was successful
        await driver.wait(until.titleIs("DEMO TENANT"), 5000);
    } catch (e) {
        throw e;
    } finally {
        await driver.quit();
    }

})();

