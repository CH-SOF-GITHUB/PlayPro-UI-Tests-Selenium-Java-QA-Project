package config;

import com.testingbot.testingbotrest.TestingbotUnauthorizedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ConfigTestingBot {
    // integrate TestingBot here
    // "TestingBot Key = 5d2216ac43e908df72d5246c717d0715 | c17d99279baca27c772209c0ab678953
    public static final String KEY = "f4f9a9f3e65be3bca6762f0521693de1";
    // "TestingBot Secret = d05892a99eeb2bce005ff322fe1cba2b | 77f8f0a9ce9489c5ab1ac93fd4468f63"
    public static final String SECRET = "027e95bda707ba75b14add8f3f0a8884";
    // Run cross browser Selenium tests on your local network or CI with TestingBot
    public static String LOCAL_URL = "http://" + KEY + ":" + SECRET + "@localhost:4445/wd/hub";
    // Run cross browser Selenium tests on TestingBot cloud platform
    public static String HUB_URL = "https://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";
    // declare a remote web driver
    protected RemoteWebDriver driver = null;

    // ConfigTestingBot Constructor
    public ConfigTestingBot() {
    }

    public RemoteWebDriver getTestingBotDriver() throws MalformedURLException, TestingbotUnauthorizedException {
        System.out.println("----Driver is configured now !----\n");
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> testingBotOptions = new HashMap<>();
        testingBotOptions.put("platform", "WIN10");
        testingBotOptions.put("version", "latest");
        testingBotOptions.put("build", "Build-TestingBot-Playpro-HUB-Grid-Automation - version 1.2");
        testingBotOptions.put("tunnel", true);
        testingBotOptions.put("screenshot", true);
        options.setCapability("tb:options", testingBotOptions);

        // initialise webdriver
        driver = new RemoteWebDriver((new URL(HUB_URL)), options);

        // return now the driver
        return driver;
    }
}
