package com.qa.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.qa.utilities.TakeCapture;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractChromeWebDriverTest {

    // TESTING BASE URL
    protected String BASE_URL = "https://www.saucedemo.com";

    // define your web driver
    protected WebDriver driver;

    TakeCapture takeCapture;


    public AbstractChromeWebDriverTest() {
        super();
    }

    @BeforeEach
    public void beforeTest() {
        // set up web driver manager
        WebDriverManager.chromedriver().setup();
        // create an instance of your web driver - chrome
        // désactiver le gestionnaire de mots de passe dans ChromeOptions
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        takeCapture = new TakeCapture(driver);
    }

    @AfterEach
    public void afterTest(TestInfo testInfo) throws IOException {
        // close each browser
        if (driver != null) {
            takeCapture.takeScreenshot(testInfo.getTestMethod().get().getName());
            driver.quit();
        }
    }
}
