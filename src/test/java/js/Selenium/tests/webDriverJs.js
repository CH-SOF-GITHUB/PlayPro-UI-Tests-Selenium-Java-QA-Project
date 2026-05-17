const {Builder, By, Key, until, Browser, logging} = require('selenium-webdriver');
logging.installConsoleHandler();
const logger = logging.getLogger('promise.ControlFlow');
logger.setLevel(logging.Level.INFO);


(async function run() {
    /** * @type {import('selenium-webdriver').WebDriver} */
    const driver = new Builder().forBrowser(Browser.CHROME).build();
    try {
        await driver.get('https://www.google.com');
        const title = await driver.getTitle();
        logger.info(title);
        log
    } finally {
        await driver.quit();
    }
})();
