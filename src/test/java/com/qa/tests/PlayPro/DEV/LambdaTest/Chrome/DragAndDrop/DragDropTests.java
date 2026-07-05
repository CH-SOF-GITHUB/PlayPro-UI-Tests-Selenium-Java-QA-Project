package com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.DragAndDrop;

import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class DragDropTests {
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

    @Test(testName = "Drag And Drop Test 1", description = "This test attempts to drag and drop using source and destination.")
    @Feature("Drag,Drop and Mouse Over,Mouse Events Features")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void TC001() throws IOException {
        Allure.suite("Drag&Drop Tests");
        // navigate to the page with drag and drop
        driver.get("https://pynishant.github.io/");
        // create an object to class Actions
        Actions a = new Actions(driver);
        // Drag and Drop using a source and destination
        WebElement src = driver.findElement(By.id("div1"));
        WebElement dst = driver.findElement(By.id("div2"));
        a.dragAndDrop(src, dst).build().perform();
        // Capture Screen Shot fo Browser
        File srcFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile1, new File("src/test/java/com/qa/tests/Screenshots/allure/DragAndDrop.png"));
    }

    @Test(testName = "Drag And Drop Test 2", description = "This test attempts to drag and drop to specific position.")
    @Feature("Drag,Drop and Mouse Over,Mouse Events Features")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void TC002() throws IOException {
        Allure.suite("Drag&Drop Tests");
        // navigate to the page with drag and drop
        driver.get("https://pynishant.github.io/");
        // create an object to class Actions
        Actions a = new Actions(driver);
        // Drag and Drop using a source and destination
        WebElement src = driver.findElement(By.id("div1"));
        // WebElement dst = driver.findElement(By.id("div2"));
        a.dragAndDropBy(src, 200, 0).build().perform();
        // Capture Screen Shot fo Browser
        File srcFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile1, new File("src/test/java/com/qa/tests/Screenshots/allure/DragAndDrop2.png"));
    }
}
