/*
package tests.PlayPro.DEV.TestingBot;

import com.testingbot.testingbotrest.TestingbotREST;
import configuration.ConfigTestingBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class SimpleTest extends ConfigTestingBot {
    RemoteWebDriver driver = null;

    @BeforeTest
    public void setup() throws MalformedURLException {
        System.out.println("Setup OK");
        driver = getTestingBotDriver();
    }

    @Test
    public void testMethod() {
        driver.get("https://site.playpro.fr/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        WebElement ReservationsBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div[1]/ul/a[6]")));
        ReservationsBtn.click();
        System.out.println("Test OK");
    }


    @AfterTest
    public void teardown() {
        if (driver != null) {
            TestingbotREST r = new TestingbotREST(KEY, SECRET);
            Map<String, Object> data = new HashMap<>();
            data.put("success", "1");
            data.put("name", "Sample Selenium Test 1");
            r.updateTest(driver.getSessionId().toString(), data);
            driver.quit();
            System.out.println("Teardown OK");
        }
    }
}
*/
