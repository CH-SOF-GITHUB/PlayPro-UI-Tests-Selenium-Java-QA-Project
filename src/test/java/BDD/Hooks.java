package BDD;

import configuration.LTConfigCucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;


public class Hooks extends LTConfigCucumber {
    private static final Log log = LogFactory.getLog(Hooks.class);
    // initialize the web driver
    public static WebDriver driver;
    // define explicit wait for web elements conditions
    // public static WebDriverWait wait;

    @Before
    public void setUp() {
        driver = getCucumberDriver();
        // wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(25));
        log.info("🚀 Driver For Cucumber Tests initialized successfully !\n");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            log.info("\n 🚀 Le Scenario Testé: " + scenario.getName() + " Et le status : " + scenario.getStatus());
            driver.quit();
            log.info("\n 🚀 Teardown Driver For Cucumber Tests\n");
        }
    }
}
