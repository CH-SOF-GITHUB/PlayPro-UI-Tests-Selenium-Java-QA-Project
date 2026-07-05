package com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.Navigation;

import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class NavigationTests {

    // create a web driver object
    WebDriver driver = new ChromeDriver();

    /* handle Java Script Alerts Tests
     */

    @BeforeClass
    public void setUp() {
        // maximize the browser window
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(testName = "Try Simple Alert 1", description = "This test attempts to navigate back and forward in browser.")
    @Feature("Navigation Features")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void TC001() throws IOException {
        Allure.suite("Navigation Tests");
        // navigate to the first page
        driver.get("https://www.google.com/");
        // navigate to the second page
        driver.get("https://ecommerce-playground.lambdatest.io/");
        // navigate back to the first page
        driver.navigate().back();
        // Capture Screen Shot fo Browser
        File srcFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile1, new File("src/test/java/com/qa/tests/Screenshots/allure/navigateBack.png"));
        // navigate forward to the second page
        driver.navigate().forward();
        // Capture Screen Shot fo Browser
        File srcFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile2, new File("src/test/java/com/qa/tests/Screenshots/allure/navigateForward.png"));
    }
}
