package Mobile;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class SampleTest {

    AndroidDriver driver;
    // define a explicit wait object
    WebDriverWait Wait;

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

        // define the explicit wait with a timeout of 15 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
    }

    @Test
    public void testGoogle() throws InterruptedException, IOException {
        driver.get("https://demotenant.playpro.fr/");
        Thread.sleep(7000);
        System.out.println(driver.getTitle());
        // Close the cookie banner if it appears
        WebElement acceptBtn = driver.findElement(By.xpath("//button[@data-testid='cookie-banner-accept-button']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", acceptBtn);
        js.executeScript("arguments[0].click();", acceptBtn);
        // Click on the menu button
        WebElement MenuBtn = driver.findElement(By.xpath("//button[@aria-label='Navigation.open_menu']"));
        Assert.assertTrue(MenuBtn.isEnabled());
        MenuBtn.click();
        System.out.println("Menu button clicked successfully.");
        // take the screenshot of the menu
        TakesScreenshot ts = (TakesScreenshot) driver;
        File file = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/java/Mobile/Screenshots/MenuScreenshot.png"));
    }

    @AfterTest
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
