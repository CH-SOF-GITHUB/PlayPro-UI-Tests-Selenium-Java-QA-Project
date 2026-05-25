package tests.PlayPro.DEV;

import PageObject.Activites.WebEXP1Page;
import PageObject.Activites.WebEXP2Page;
import PageObject.WebCookiesPage;
import PageObject.WebLoginPage;
import PageObject.WebReservationPage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static configuration.LTConfig.getLTDriver;
import static tests.LTTestStatus.markTestStatusViaJS;

public class BaseTest {
    // define web driver
    protected WebDriver driver;

    // define log from apache
    Log log = LogFactory.getLog(BaseTest.class);

    // define explicit wait object
    protected WebDriverWait Wait;

    // define page objects
    protected WebEXP2Page webEXP2Page = null;
    protected WebReservationPage webReservationPage = null;
    protected WebEXP1Page webEXP1Page = null;

    @BeforeMethod
    public void setUp() {
        log.info("Starting WebDriver...");
        // =========================
        ChromeOptions options = new ChromeOptions();
        String headless = System.getProperty("headless", "false");
        if (headless.equals("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        // =========================
        Wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        // =========================
        // PAGE OBJECT INIT
        // =========================
        webReservationPage = new WebReservationPage(driver);
        webEXP1Page = new WebEXP1Page(driver);
        webEXP2Page = new WebEXP2Page(driver);

        log.info("Navigating to application...");

        driver.get("https://demotenant.playpro.fr/connexion");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File("target/selenium/java/screenshots/failure/" + result.getMethod().getMethodName() + ".png");
            FileUtils.copyFile(srcFile, targetFile);
        }

        markTestStatusViaJS(driver, result.getStatus() == ITestResult.SUCCESS, "Test " + result.getMethod().getMethodName() + " " + (result.getStatus() == ITestResult.SUCCESS ? "passed" : "failed"));

        if (driver != null) {
            log.info("Closing WebDriver...");
            driver.quit();
        }
    }
}
