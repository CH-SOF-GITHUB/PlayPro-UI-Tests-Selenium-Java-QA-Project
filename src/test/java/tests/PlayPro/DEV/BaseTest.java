package tests.PlayPro.DEV;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

import static configuration.LTConfig.getLTDriver;

public class BaseTest {
    // define web driver
    protected WebDriver driver = null;
    // define log from apache
    Log log = LogFactory.getLog(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        log.info("Starting WebDriver...");
        driver = getLTDriver();
        driver.manage().window().maximize();
        log.info("Navigating to URL...");
        driver.navigate().to("https://demotenant.playpro.fr/connexion");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File("tests/Screenshots/failure/" + result.getMethod().getMethodName() + ".png");
            FileUtils.copyFile(srcFile, targetFile);
        }

        if (driver != null) {
            log.info("Closing WebDriver...");
            driver.quit();
        }
    }
}
