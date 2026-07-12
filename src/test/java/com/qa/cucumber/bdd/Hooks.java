package com.qa.cucumber.bdd;

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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.qa.tests.LTTestStatus.markTestStatusViaJS;


public class Hooks {
    private static final Log log = LogFactory.getLog(Hooks.class);
    // initialize the web driver
    public static WebDriver driver = null;
    // define explicit wait object
    protected WebDriverWait Wait;

    @Before
    public void setUp() {
        log.info("🚀 Starting WebDriver For Cucumber BDD...");
        // initialize the ChromeOptions object
        ChromeOptions options = new ChromeOptions();

        // 🔥 default = false (LOCAL)
        String headless = System.getProperty("headless", "false");

        if (headless.equals("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        // 🔥 COMMON STABLE OPTIONS (LOCAL + CI)
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // 🔥 IMPORTANT FIX: always set window size (CI + local)
        // options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        // ❌ DO NOT USE maximize in CI (and avoid it globally)
        // driver.manage().window().maximize(); ❌ REMOVE
        driver.manage().window().maximize();
        Wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Navigate to the login page
        driver.get("https://chakerqa.playpro.fr/connexion");
    }

    @After
    public void tearDown(Scenario scenario) throws IOException, InterruptedException {
        log.info("\n 🚀 Le Scenario Testé: " + scenario.getName() + " Et le status : " + scenario.getStatus());
        if (scenario.getStatus() == Status.FAILED) {
            File srcFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile1, new File("src/test/java/com/qa/cucumber/bdd/Screenshots/failure/scenario_fails.png"));
            Thread.sleep(3000);
        }
        // declare a method to set the test status in LambdaTest via JS
        markTestStatusViaJS(driver, scenario.getStatus() == Status.PASSED, "Scenario " + scenario.getName() + " " + (scenario.getStatus() == Status.PASSED ? "passed" : "failed"));
        if (driver != null) {
            log.info("\n 🚀 Closing WebDriver For Cucumber BDD...");
            driver.quit();
        }
    }
}
