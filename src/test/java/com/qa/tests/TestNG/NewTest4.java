package com.qa.tests.TestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;


@Listeners(TestNGListener.class)
public class NewTest4 {
    WebDriver driver;
    String browserName;

    // @BeforeSuite: The annotation method will be run before all tests in this suite have run.
    @BeforeSuite
    public void configureLogs() {
        // AJOUT : masquer les logs Selenium INFO/WARNING
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket").setLevel(Level.SEVERE);
        // System.out.println("================ Suite Started ================");
    }

    // @BeforeMethod: The annotation method will be run before each test method.
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser) {
        browserName = browser;
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Error: Browser not supported : " + browser);
        }
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }

    // @AfterMethod: The annotation method will be run after each test method.
    @AfterMethod
    public void end() {
        driver.close();
    }

    @Test
    public void z() {
        System.out.println("Inside method Z - Browser : " + browserName);
        System.out.println("method Z is running in thread : " + Thread.currentThread().getId());
    }

    @Test
    public void y() {
        System.out.println("Inside method Y - Browser : " + browserName);
        System.out.println("method Y is running in thread : " + Thread.currentThread().getId());
    }
}
