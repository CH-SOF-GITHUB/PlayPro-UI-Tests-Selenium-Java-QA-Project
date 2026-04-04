package configuration;

import com.testingbot.testingbotrest.TestingbotUnauthorizedException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ConfigTBParallelTest {
    // TestingBot key
    public static final String KEY = "808e4119926aacc013ff3e5e63952fe4";
    // TestingBot Secret
    public static final String SECRET = "98d7be26aaf7196de13e6cceaa34df18";
    // Run cross browser Selenium tests on your local network or CI with TestingBot
    // public static String LOCAL_URL = "http://" + KEY + ":" + SECRET + "@localhost:4445/wd/hub";
    // Run cross browser Selenium tests on TestingBot cloud platform
    public static String HUB_URL = "https://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";
    // declare a remote web driver
    static RemoteWebDriver driver = null;

    public static RemoteWebDriver getParallelTestDriver(String browser, String version, String platform) throws MalformedURLException, TestingbotUnauthorizedException {
        System.out.println("----Driver For Parallel Testing is configured now !----\n");
        // set capabilities of tests
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, platform);

        // capabilites specific to TestingBot
        Map<String, Object> testingBotOptions = new HashMap<>();
        testingBotOptions.put("build", "Build-TestingBot-For-Parallel-Tests");
        testingBotOptions.put("tunnel", true);
        testingBotOptions.put("screenshot", true);
        capabilities.setCapability("tb:options", testingBotOptions);
        // initialise webdriver
        driver = new RemoteWebDriver((new URL(HUB_URL)), capabilities);
        // return now the driver
        return driver;
    }
}
