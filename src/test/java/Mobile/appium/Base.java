package Mobile.appium;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;

import java.net.URL;
import java.time.Duration;

public class Base {
    // apache commons logging
    public Log log = LogFactory.getLog(Base.class);
    //  declare web driver
    public AndroidDriver driver;
    // declare the status of the test case
    public String status = "failed";

    @BeforeTest
    public void setup() throws Exception {
        log.info("Mobile Android WebDriver is initialized ...\n");

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

        // define the implicit wait with a timeout of 15 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // navigate to the base URL of the application under test
        driver.get("https://demotenant.playpro.fr/connexion");

        // Wait to load page
        Thread.sleep(7000);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        log.info("Test " + status);
        log.info("Mobile Android WebDriver tear down ...\n");
    }


    @BeforeGroups("smoke")
    public void closeCookieBanner() {
        // Close the cookie banner if it appears
        WebElement acceptBtn = driver.findElement(By.xpath("//button[@data-testid='cookie-banner-accept-button']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", acceptBtn);
        js.executeScript("arguments[0].click();", acceptBtn);
        log.info("Cookie banner closed successfully.");
    }
}
