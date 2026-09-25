package com.qa.tests.TestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NewTest3 {

    WebDriver driver;

    @DataProvider
    public static Object[][] getdata() {
        return new Object[][]{
                {"Input1", "Error message"},
                {"Input2", "Success message"}
        };
    }

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

    @Test(dataProvider = "getdata")
    public void z(String Input, String ExpectedMessage) {
        System.out.println("Inside method Z and working with the input : " + Input);
        String output = "Error message";
        Assert.assertEquals(output, ExpectedMessage, "Output did not math");
    }
}
