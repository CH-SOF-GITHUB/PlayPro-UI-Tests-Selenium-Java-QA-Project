package BDD;

import configuration.LTConfigCucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

import static tests.LTTestStatus.markTestStatusViaJS;


public class Hooks extends LTConfigCucumber {
    private static final Log log = LogFactory.getLog(Hooks.class);
    // initialize the web driver
    // public static WebDriver driver = null;
    public static WebDriver driver = null;

    @Before
    public void setUp() {
        log.info("🚀 Starting WebDriver For Cucumber BDD...");
        try {
            driver = new ChromeDriver();
            if (driver == null) {
                log.error("❌ Driver initialization failed: driver is null");
                throw new RuntimeException("Driver initialization failed: driver is null");
            }
            driver.manage().window().maximize();
            log.info("Navigating to URL...");
            driver.navigate().to("https://demotenant.playpro.fr/connexion");
        } catch (Exception e) {
            log.error("❌ Driver initialization failed: " + e.getMessage());
            throw e;
        }
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        log.info("\n 🚀 Le Scenario Testé: " + scenario.getName() + " Et le status : " + scenario.getStatus());
        if (scenario.getStatus() == Status.FAILED) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File("C:\\Users\\chaker\\Desktop\\automation\\Mobile-Web-Testing\\MobileWebTesting\\src\\test\\java\\BDD\\Screenshots\\failure\\" + scenario.getName() + ".png");
            FileUtils.copyFile(srcFile, targetFile);
        }
        // declare a method to set the test status in LambdaTest via JS
        markTestStatusViaJS(driver, scenario.getStatus() == Status.PASSED, "Scenario " + scenario.getName() + " " + (scenario.getStatus() == Status.PASSED ? "passed" : "failed"));
        if (driver != null) {
            log.info("\n 🚀 Closing WebDriver For Cucumber BDD...");
            driver.quit();
        }
    }
}
