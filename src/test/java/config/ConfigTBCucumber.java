package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class ConfigTBCucumber {

    public static String username = "f4f9a9f3e65be3bca6762f0521693de1";
    public static String accesskey = "027e95bda707ba75b14add8f3f0a8884";

    public static WebDriver getDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "latest");
        capabilities.setCapability("platformName", "WIN10");

        Map<String, Object> testingBotOptions = new HashMap<>();
        capabilities.setCapability("tb:options", testingBotOptions);

        try {
            String gridURL = "https://" + username + ":" + accesskey + "@hub.testingbot.com/wd/hub";
            return new RemoteWebDriver(new URL(gridURL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid URL provided for TestingBot hub", e);
        }
    }
}
