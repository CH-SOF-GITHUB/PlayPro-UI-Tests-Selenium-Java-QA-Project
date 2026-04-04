package configuration;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class LTMobileConfig {
    // declare username and access key of lambdatest cloud account
    public static final String Username = "LambdaTest02outlook";
    public static final String AccessKey = "LT_OeKIsPcFeDNbCXdCF8F61YHX4U4JIW0qFsvvCM4804D4Cuz";
    // declare URL of lambdatest selenium grid
    public static final String gridURL = "https://" + Username + ":" + AccessKey + "@hub.lambdatest.com/wd/hub";

    // declare a constructor of class
    public LTMobileConfig() {
    }

    public static RemoteWebDriver getMobileDriver() {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "safari");
        caps.setCapability(CapabilityType.BROWSER_VERSION, "18.6");
        caps.setCapability("appium:deviceName", "iPhone 16");
        caps.setCapability(CapabilityType.PLATFORM_NAME, "iOS");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("project", "UI-PlayPro-Testing");
        ltOptions.put("w3c", true);
        ltOptions.put("build", "BuildLT-Mobile-PlayProV3-1.0.0");
        ltOptions.put("network", true);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("screenshot", true);
        ltOptions.put("console", true);
        ltOptions.put("terminal", true);
        ltOptions.put("devicelog", true);
        caps.setCapability("lt:options", ltOptions);
        // URL Selenium Grid de TestingBot Edge
        try {
            return new RemoteWebDriver(new URL(gridURL), caps);
        } catch (MalformedURLException | SessionNotCreatedException e) {
            e.fillInStackTrace();
            throw new RuntimeException("Error is generated : ", e);
        }
    }
}
