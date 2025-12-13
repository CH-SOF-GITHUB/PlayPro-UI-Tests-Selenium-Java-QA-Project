package StepDefinitions;

import com.testingbot.testingbotrest.TestingbotREST;
import config.ConfigTBCucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static config.ConfigTestingBot.KEY;
import static config.ConfigTestingBot.SECRET;


public class Hooks {
    public static RemoteWebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = (RemoteWebDriver) ConfigTBCucumber.getDriver();
        System.out.println("🚀 Driver TestingBot initialisé avec succès !");
        new BackgroundLogin();
    }


    @After
    public void tearDown(Scenario scenario) throws Exception {
        try {
            System.out.println("\n------ TEARDOWN 1: Update Cucumber TestingBot Cloud Data ------\n");
            String sessionId = driver.getSessionId().toString();
            TestingbotREST r = new TestingbotREST(KEY, SECRET);
            Map<String, Object> data = new HashMap<>();
            data.put("success", true);
            data.put("name", scenario.getName());
            r.updateTest(sessionId, data);
        } catch (Exception e) {
            System.out.println("Error in tearDown: " + e.getMessage());
        } finally {
            if (driver != null) {
                System.out.println("------ TEARDOWN 2: Closing the browser of test Cucumber ------\n");
                driver.quit();
            }
        }
    }
}
