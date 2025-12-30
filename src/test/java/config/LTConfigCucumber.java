package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LTConfigCucumber {
    // LambdaTest Credentials
    static final String LT_USERNAME_CUCUMBER = "chakerbensaid555";
    static final String LT_ACCESSKEY_CUCUMBER = "LT_6tPniB7x45TXRmqWcTF45zqrsCzOU1I1gbJSQg6xkqwfbTG";

    // declare the web driver to control navigateurs web
    static WebDriver driver = null;
    // declare the LambdaTest Grid URL
    static final String LT_GRID_URL = "https://" + LT_USERNAME_CUCUMBER + ":" + LT_ACCESSKEY_CUCUMBER + "@hub.lambdatest.com/wd/hub";

    public static WebDriver getCucumberDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, "latest");
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "WIN10");

        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("project", "UI-PlayPro-Testing");
        ltOptions.put("build", "Build-Cucumber-In-LambdaTest");
        ltOptions.put("w3c", true);
        ltOptions.put("network", true);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("screenshot", true);
        ltOptions.put("console", true);
        ltOptions.put("terminal", true);
        ltOptions.put("devicelog", true);

        capabilities.setCapability("lt:options", ltOptions);
        try {
            driver = new RemoteWebDriver(new URL(LT_GRID_URL), capabilities);
        } catch (MalformedURLException e) {
            e.fillInStackTrace();
        }
        return driver;
    }
}
