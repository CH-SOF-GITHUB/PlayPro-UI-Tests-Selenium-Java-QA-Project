package StepDefinitions;

import com.testingbot.testingbotrest.TestingbotREST;
import config.ConfigTBCucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;

import static config.ConfigTBCucumber.KEY;
import static config.ConfigTBCucumber.SECRET;

public class Hooks {
    public static RemoteWebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = ConfigTBCucumber.getDriver();
        System.out.println("🚀 Driver TestingBot initialisé avec succès !\n");
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        if (driver != null) {
            System.out.println("🚀 Teardown Driver of Cucumber Tests\n");
            String sessionId = driver.getSessionId().toString();
            TestingbotREST r = new TestingbotREST(KEY, SECRET);
            Map<String, Object> data = new HashMap<>();
            data.put("success", true);
            data.put("name", scenario.getName());
            r.updateTest(sessionId, data);
                driver.quit();
            }
    }
}
