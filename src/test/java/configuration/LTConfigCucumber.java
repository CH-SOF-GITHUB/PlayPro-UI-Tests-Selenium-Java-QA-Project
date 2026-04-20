package configuration;

import org.openqa.selenium.SessionNotCreatedException;
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
    public static final String LT_USERNAME_CUCUMBER = "chakerlt2";
    public static final String LT_ACCESSKEY_CUCUMBER = "LT_t7oXNTYoT38A5wcGHphbOohy3FvJK2LprJjCZz8NKLk2LS9";

    // declare the LambdaTest Grid URL
    public static final String LT_GRID_URL = "https://" + LT_USERNAME_CUCUMBER + ":" + LT_ACCESSKEY_CUCUMBER + "@hub.lambdatest.com/wd/hub";

    protected static WebDriver getCucumberDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, "latest");
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "WIN11");

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
            return new RemoteWebDriver(new URL(LT_GRID_URL), capabilities);
        } catch (MalformedURLException | SessionNotCreatedException e) {
            e.fillInStackTrace();
            throw new RuntimeException("Error is generated : ", e);
        }
    }
}
