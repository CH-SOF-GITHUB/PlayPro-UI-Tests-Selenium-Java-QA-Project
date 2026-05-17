const {Builder, By, Key, until, Browser, logging} = require('selenium-webdriver');
const assert = require("assert");
const {expect} = require('chai');
const fs = require('fs');
const path = require('path');
const os = require('os');
const chrome = require('selenium-webdriver/chrome');
logging.installConsoleHandler();
const logger = logging.getLogger('promise.ControlFlow');
logger.setLevel(logging.Level.INFO);
let driver;

/**
 * Retourne un chemin vers chromedriver s'il est trouvable :
 * - prefer `require('chromedriver').path`
 * - sinon quelques emplacements courants selon la plateforme
 * - sinon null (service sans path -> rely on PATH)
 */
function resolveChromeDriverPath() {
    try {
        const chromedriver = require('chromedriver');
        if (chromedriver && chromedriver.path && fs.existsSync(chromedriver.path)) {
            return chromedriver.path;
        }
    } catch (e) {
        // module non installé, continuer avec candidats
    }

    const platform = process.platform;
    const candidates = platform === 'win32' ? [
        path.join(process.cwd(), 'node_modules', 'chromedriver', 'lib', 'chromedriver', 'chromedriver.exe'),
        'C:\\chromedriver\\chromedriver.exe'
    ] : [
        '/usr/bin/chromedriver',
        '/usr/local/bin/chromedriver'
    ];

    for (const p of candidates) {
        if (p && fs.existsSync(p)) return p;
    }
    return null;
}


// javascript
async function getDriver() {
    const options = new chrome.Options();

    // headless toggle: default = true, set HEADLESS=false to voir le navigateur
    const headless = (process.env.HEADLESS || 'true').toLowerCase() !== 'false';
    console.log('Chrome headless:', headless);

    // n'appliquer setChromeBinaryPath que sur Linux/CI si nécessaire
    if (process.platform !== 'win32') {
        options.setChromeBinaryPath('/usr/bin/chromium-browser'); // utile en CI linux
    }

    const args = [
        '--no-sandbox',
        '--disable-dev-shm-usage',
        '--window-size=1920,1080',
        '--log-level=3',
        '--disable-logging'
    ];

    if (headless) {
        args.unshift('--headless=new');
    }

    options.addArguments(...args);

    const chromedriverPath = resolveChromeDriverPath();
    let serviceBuilder;
    const nulPath = process.platform === 'win32' ? 'NUL' : '/dev/null';

    if (chromedriverPath) {
        console.log('Using chromedriver at:', chromedriverPath);
        serviceBuilder = new chrome.ServiceBuilder(chromedriverPath).loggingTo(nulPath);
    } else {
        console.warn('No explicit chromedriver found, relying on PATH to locate chromedriver.');
        serviceBuilder = new chrome.ServiceBuilder().loggingTo(nulPath);
    }

    process.env.CHROME_LOG_FILE = nulPath;

    driver = await new Builder()
        .forBrowser(Browser.CHROME)
        .setChromeOptions(options)
        .setChromeService(serviceBuilder)
        .build();

    return driver;
}


(async function example() {
    console.log("========== START JS TEST ==========");
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
        logger.info("Page Title 1 is : " + pageTitle1);
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
        logger.info("Page Title 2 is : " + pageTitle2);
        // assert using chai
        expect(pageTitle2).to.equal("DEMO TENANT");
        //Check if redirect to login page was successful
        await driver.wait(until.titleIs("DEMO TENANT"), 5000);
    } catch (e) {
        throw e;
    } finally {
        await driver.quit();
        console.log("Browser closed");
        console.log("========== END JS TEST ==========");
    }

})();

