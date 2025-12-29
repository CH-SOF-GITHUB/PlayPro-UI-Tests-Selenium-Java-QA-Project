package StepDefinitions;

//import StepDefinitions.Login.BackgroundLogin;
import com.testingbot.testingbotrest.TestingbotREST;
import config.TBConfigCucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;


public class Hooks extends TBConfigCucumber {
    public static RemoteWebDriver driver = null;

    @Before
    public void setUp() throws Exception {
        driver = getDriver();
        System.out.println("🚀 Driver TestingBot initialisé avec succès !\n");
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        if (driver != null) {
            TestingbotREST r = new TestingbotREST(KEY, SECRET);
            Map<String, Object> data = new HashMap<>();
            data.put("success", "1");
            data.put("name", scenario.getName());
            r.updateTest(driver.getSessionId().toString(), data);
            driver.quit();
            System.out.println("\n---🚀 Teardown of Driver Testing of Cucumber ---\n");
        }
    }
}
