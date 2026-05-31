package Mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.URL;

public class SampleTest {

    AndroidDriver driver;

    @BeforeTest
    public void setup() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");

        // Appium
        caps.setCapability("appium:automationName", "UiAutomator2");

        // Emulator
        caps.setCapability("appium:deviceName", "emulator-5554");

        // Browser
        caps.setCapability("browserName", "Chrome");

        // Fix stability
        caps.setCapability("appium:ignoreHiddenApiPolicyError", true);
        caps.setCapability("appium:adbExecTimeout", 180000);

        URL url = new URL("http://127.0.0.1:4723/");

        driver = new AndroidDriver(url, caps);
    }

    @Test
    public void testGoogle() {

        driver.get("https://demotenant.playpro.fr/");

        System.out.println(driver.getTitle());
    }

    @AfterTest
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
