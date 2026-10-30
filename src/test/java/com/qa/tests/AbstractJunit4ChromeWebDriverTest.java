package com.qa.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract class AbstractJunit4ChromeWebDriverTest {
    // define your web driver
    protected WebDriver driver;

    public AbstractJunit4ChromeWebDriverTest() {
        super();
    }

    @Before
    public void beforeTest() {
        // set up web driver manager
        WebDriverManager.chromedriver().setup();

        // create an instance of your web driver - chrome
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void afterTest() {
        // close each browser
        driver.quit();
    }
}
