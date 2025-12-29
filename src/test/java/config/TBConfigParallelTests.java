package config;

import com.testingbot.testingbotrest.TestingbotUnauthorizedException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TBConfigParallelTests {
    // TestingBot key
    public static final String KEY = "5c797835f6a08f70fd15b5823c2a6e52";
    // TestingBot Secret
    public static final String SECRET = "9fb978750fc0c45558d5caa85c9f239b";
    // Run cross browser Selenium tests on your local network or CI with TestingBot
    // public static String LOCAL_URL = "http://" + KEY + ":" + SECRET + "@localhost:4445/wd/hub";
    // Run cross browser Selenium tests on TestingBot cloud platform
    public static String HUB_URL = "https://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";
    // declare a remote web driver
    RemoteWebDriver driver = null;

    // ConfigTestingBot Constructor
    public TBConfigParallelTests() {
    }

    public RemoteWebDriver getDriverForParallelTests(String browser, String version, String platform) throws MalformedURLException {
        try {
            System.out.println("----Driver is configured now for parallel tests!----\n");
            // set capabilities of tests
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
            capabilities.setCapability(CapabilityType.BROWSER_VERSION, version);
            capabilities.setCapability(CapabilityType.PLATFORM_NAME, platform);

            // capabilites specific to TestingBot
            Map<String, Object> testingBotOptions = new HashMap<>();
            testingBotOptions.put("build", "Build-TestingBot-Playpro-Demo-Parallel-Tests");
            testingBotOptions.put("tunnel", true);
            testingBotOptions.put("screenshot", true);
            capabilities.setCapability("tb:options", testingBotOptions);
            // initialise webdriver
            driver = new RemoteWebDriver((new URL(HUB_URL)), capabilities);
            // return now the driver
            return driver;
        } catch (TestingbotUnauthorizedException e) {
            throw e;
        }
    }

}
