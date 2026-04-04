package configuration;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class LTConfigParallelTest {

    // declare username and access key of lambdatest cloud account
    public static final String Username = "LambdaTest02outlook";
    public static final String AccessKey = "LT_OeKIsPcFeDNbCXdCF8F61YHX4U4JIW0qFsvvCM4804D4Cuz";

    // declare URL of lambdatest selenium grid
    public static final String gridURL = "https://" + Username + ":" + AccessKey + "@hub.lambdatest.com/wd/hub";

    // declare a constructor of class
    public LTConfigParallelTest() {}

    // create a method to get LT driver for parallel tests
    protected static WebDriver getLTParallelDriver() {
        // declare desired capabilities of the LT driver
        // liste de clés standard W3C utilisées par Selenium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, "latest");
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "macOS Tahoe");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "LT-ParallelExecution-PlayProDemoV3-1.0.0");
        ltOptions.put("project", "UI-PlayPro-Testing");
        ltOptions.put("w3c", true);
        ltOptions.put("network", true); // To enable network logs
        ltOptions.put("visual", true); // To enable step by step screenshot
        ltOptions.put("video", true); // To enable video recording
        ltOptions.put("screenshot", true);
        ltOptions.put("console", true); // To capture console logs
        ltOptions.put("terminal", true);
        ltOptions.put("devicelog", true);

        capabilities.setCapability("lt:options", ltOptions);
        // URL Selenium Grid de TestingBot Edge
        try {
            return new RemoteWebDriver(new URL(gridURL), capabilities);
        } catch (MalformedURLException | SessionNotCreatedException e) {
            e.fillInStackTrace();
            throw new RuntimeException("Error is generated : ", e);
        }
    }
}
