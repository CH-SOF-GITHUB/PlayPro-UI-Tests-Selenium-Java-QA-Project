package BDD;

import config.LTConfigCucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Hooks extends LTConfigCucumber {
    // initialize the web driver
    public static WebDriver driver;
    // define explicit wait for web elements conditions
    public static WebDriverWait wait;

    @Before
    public void setUp() {
        driver = getCucumberDriver();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(25));
        System.out.println("🚀 Driver For Cucumber Tests initialized successfully !\n");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            System.out.println("\n 🚀 Le Scenario Testé: " + scenario.getName() + " Et le status : " + scenario.getStatus());
            driver.quit();
            System.out.println("\n 🚀 Teardown Driver For Cucumber Tests\n");
        }
    }
}
