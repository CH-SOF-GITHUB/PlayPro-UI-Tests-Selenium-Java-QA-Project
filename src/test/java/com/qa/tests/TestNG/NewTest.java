package com.qa.tests.TestNG;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NewTest {

    WebDriver driver;

    // @BeforeSuite: The annotation method will be run before all tests in this suite have run.
    @BeforeSuite
    public void configureLogs() {
        // AJOUT : masquer les logs Selenium INFO/WARNING
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket").setLevel(Level.SEVERE);
        System.out.println("================ Suite Started ================");
    }

    // @BeforeMethod: The annotation method will be run before each test method.
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }

    // @AfterMethod: The annotation method will be run after each test method.
    @AfterMethod
    public void end() {
        driver.close();
    }

    @Test(dependsOnMethods = {"y"})
    public void z() {
        System.out.println("Inside method Z");
    }

    @Test
    public void y() {
        System.out.println("Inside method Y");
    }


    //@AfterSuite: The annotation method will be run after all tests in this suite have run.
    @AfterSuite
    public void finish() {
        System.out.println("================ Suite Finished ================");
    }
}
