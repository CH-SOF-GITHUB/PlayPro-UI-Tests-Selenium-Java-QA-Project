package com.qa.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract class AbstractChromeWebDriverTest {
    // define your web driver
    protected WebDriver driver;

    public AbstractChromeWebDriverTest() {
        super();
    }

    @BeforeEach
    public void beforeTest() {
        // set up web driver manager
        WebDriverManager.chromedriver().setup();

        // create an instance of your web driver - chrome
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterEach
    public void afterTest() {
        // close each browser
        driver.quit();
    }
}
