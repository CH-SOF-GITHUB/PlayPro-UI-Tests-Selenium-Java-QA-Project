package BDD;

import config.LTConfigCucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;


public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = LTConfigCucumber.getCucumberDriver();
        System.out.println("🚀 Driver TestingBot initialisé avec succès !\n");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            System.out.println("\n 🚀 Le Scenario Testé: " + scenario.getName() + " Et le status : " + scenario.getStatus());
            driver.quit();
            System.out.println("\n 🚀 Teardown Driver of Cucumber Tests\n");
        }
    }
}
