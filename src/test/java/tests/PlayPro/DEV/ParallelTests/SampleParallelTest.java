package tests.PlayPro.DEV.ParallelTests;

import com.testingbot.testingbotrest.TestingbotREST;
import config.TBConfigParallelTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class SampleParallelTest extends TBConfigParallelTests {

    RemoteWebDriver driver = null;

    @BeforeTest
    @Parameters({"browser", "version", "platform"})
    public void setUp(String browser, String version, String platform) throws MalformedURLException {
        System.out.println("Setup for Parallel Tests");
        driver = getDriverForParallelTests(browser, version, platform);
    }

    @Test
    public void testSimple() {
        driver.get("https://www.google.com");
        System.out.println("Page title is: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("TestingBot");
        element.submit();
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            TestingbotREST r = new TestingbotREST(KEY, SECRET);
            Map<String, Object> data = new HashMap<>();
            data.put("success", "1");
            data.put("name", "Sample Selenium Parallel Test 1");
            r.updateTest(driver.getSessionId().toString(), data);
            driver.quit();
            System.out.println("Teardown Parallel Tests OK");
        }
    }
}
