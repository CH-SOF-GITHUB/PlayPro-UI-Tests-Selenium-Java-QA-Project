package config;

import com.testingbot.testingbotrest.TestingbotUnauthorizedException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class ConfigTBCucumber {
    // Credentials
    // Use these identifiers to authenticate with TestingBot in your automated tests.
    // TestingBot Key
    public static final String KEY = "4f978bbf1c5989cf2b2a8ee530d278d2";
    // TestingBot Secret
    public static final String SECRET = "17e87457ac005023c357a117980c3b9f";
    // Run cross browser Selenium tests on your local network or CI with TestingBot
    // public static String LOCAL_URL = "http://" + KEY + ":" + SECRET + "@localhost:4445/wd/hub";
    // Run cross browser Selenium tests on TestingBot cloud platform
    public static String HUB_URL = "https://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";
    // declare a remote web driver
    static RemoteWebDriver driver = null;

    public ConfigTBCucumber() {
    }

    public static RemoteWebDriver getDriver() throws MalformedURLException, TestingbotUnauthorizedException {
        System.out.println("----Driver For Cucumber Testing is configured now !----\n");
        // Set capabilities: browsername, version, OS
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, "143");
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Windows 11");

        // set specific capabilites to TestingBot cloud
        Map<String, Object> testingBotOptions = new HashMap<>();
        testingBotOptions.put("build", "Build-TestingBot-Cucumber");
        testingBotOptions.put("tunnel", true);
        testingBotOptions.put("screenshot", true);
        capabilities.setCapability("tb:options", testingBotOptions);
        // initialise webdriver
        driver = new RemoteWebDriver((new URL(HUB_URL)), capabilities);
        // return now the driver
        return driver;
    }
}
