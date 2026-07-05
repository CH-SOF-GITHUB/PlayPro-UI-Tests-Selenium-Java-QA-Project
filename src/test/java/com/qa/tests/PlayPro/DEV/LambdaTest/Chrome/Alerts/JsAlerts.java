package com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.Alerts;

import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class JsAlerts {

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

    @Test(testName = "Try Simple Alert 1", description = "This test attempts to handle and accept a simple alert.")
    @Feature("Alerts Features")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void TC001() throws IOException {
        Allure.suite("Alerts Tests");
        // navigate to the page with the alert
        driver.get("file:///C:/Users/chaker/Desktop/automation/circleci-test/PlayPro-UI-Tests-Selenium-Java-QA-Project/src/test/java/com/qa/Widgets/jsalerts.html");
        // click on simple alert button
        WebElement simpleBtn = driver.findElement(By.id("simple"));
        simpleBtn.click();
        // To handle alert, first we need to switch to alert
        Alert al = driver.switchTo().alert();
        // manage the actions of alert
        String AlertText = al.getText();
        System.out.println("Alert Text is: " + AlertText);
        al.accept();
        // Capture Screen Shot fo Browser
        File srcFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile1, new File("src/test/java/com/qa/tests/Screenshots/allure/simpleAlert.png"));
    }

    @Test(testName = "Try Simple Alert 2", description = "This test attempts to handle and dismiss a simple alert.")
    @Feature("Alerts Features")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void TC002() throws IOException {
        Allure.suite("Alerts Tests");
        // navigate to the page with the alert
        driver.get("file:///C:/Users/chaker/Desktop/automation/circleci-test/PlayPro-UI-Tests-Selenium-Java-QA-Project/src/test/java/com/qa/Widgets/jsalerts.html");
        // click on simple alert button
        WebElement simpleBtn = driver.findElement(By.id("simple"));
        simpleBtn.click();
        // To handle alert, first we need to switch to alert
        Alert al = driver.switchTo().alert();
        // manage the actions of alert
        String AlertText = al.getText();
        System.out.println("Alert Text is: " + AlertText);
        al.dismiss();
        // Capture Screen Shot fo Browser
        File srcFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile2, new File("src/test/java/com/qa/tests/Screenshots/allure/simpleAlert2.png"));
    }

    @Test(testName = "Try Prompt Alert 1", description = "This test attempts to handle a prompt alert, accept alert and send Text.")
    @Feature("Alerts Features")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void TC003() throws IOException {
        Allure.suite("Alerts Tests");
        // navigate to the page with the alert
        driver.get("file:///C:/Users/chaker/Desktop/automation/circleci-test/PlayPro-UI-Tests-Selenium-Java-QA-Project/src/test/java/com/qa/Widgets/jsalerts.html");
        // click on prompt alert button
        WebElement promptBtn = driver.findElement(By.id("prompt"));
        promptBtn.click();
        // To handle alert, first we need to switch to alert
        Alert al = driver.switchTo().alert();
        // manage the actions of alert
        String AlertText = al.getText();
        System.out.println("Prompt Alert Text is: " + AlertText);
        // send text to the prompt alert
        al.sendKeys("5646548");
        al.accept();
        // check the message displayed on the page after accepting the prompt alert
        WebElement promptMsg = driver.findElement(By.id("lambdaTestDemo"));
        System.out.println("Prompt Alert Message is: " + promptMsg.getText());
        // Capture Screen Shot fo Browser
        File srcFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile2, new File("src/test/java/com/qa/tests/Screenshots/allure/PromptAlert1.png"));
    }

    @Test(testName = "Try Prompt Alert 2", description = "This test attempts to handle a prompt alert, dismiss alert and send Text.")
    @Feature("Alerts Features")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void TC004() throws IOException {
        Allure.suite("Alerts Tests");
        // navigate to the page with the alert
        driver.get("file:///C:/Users/chaker/Desktop/automation/circleci-test/PlayPro-UI-Tests-Selenium-Java-QA-Project/src/test/java/com/qa/Widgets/jsalerts.html");
        // click on prompt alert button
        WebElement promptBtn = driver.findElement(By.id("prompt"));
        promptBtn.click();
        // To handle alert, first we need to switch to alert
        Alert al = driver.switchTo().alert();
        // manage the actions of alert
        String AlertText = al.getText();
        System.out.println("Prompt Alert Text is: " + AlertText);
        // send text to the prompt alert
        al.sendKeys("5646548");
        al.dismiss();
        // check the message displayed on the page after accepting the prompt alert
        WebElement promptMsg = driver.findElement(By.id("lambdaTestDemo"));
        System.out.println("Prompt Alert Message is: " + promptMsg.getText());
        // Capture Screen Shot fo Browser
        File srcFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile2, new File("src/test/java/com/qa/tests/Screenshots/allure/PromptAlert2.png"));
    }

    @Test(testName = "Try Confirm Alert 1", description = "This test attempts to handle a confirm alert, accept alert and send Text.")
    @Feature("Alerts Features")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void TC005() throws IOException {
        Allure.suite("Alerts Tests");
        // navigate to the page with the alert
        driver.get("file:///C:/Users/chaker/Desktop/automation/circleci-test/PlayPro-UI-Tests-Selenium-Java-QA-Project/src/test/java/com/qa/Widgets/jsalerts.html");
        // click on prompt alert button
        WebElement promptBtn = driver.findElement(By.id("confirm"));
        promptBtn.click();
        // To handle alert, first we need to switch to alert
        Alert al = driver.switchTo().alert();
        // manage the actions of alert
        String AlertText = al.getText();
        System.out.println("Prompt Alert Text is: " + AlertText);
        al.accept();
        // check the message displayed on the page after accepting the prompt alert
        WebElement promptMsg = driver.findElement(By.id("lambdaTestDemo"));
        System.out.println("Prompt Alert Message is: " + promptMsg.getText());
        // Capture Screen Shot fo Browser
        File srcFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile2, new File("src/test/java/com/qa/tests/Screenshots/allure/ConfirmAlert1.png"));
    }

    @Test(testName = "Try Confirm Alert 2", description = "This test attempts to handle a confirm alert, dismiss alert and send Text.")
    @Feature("Alerts Features")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void TC006() throws IOException {
        Allure.suite("Alerts Tests");
        // navigate to the page with the alert
        driver.get("file:///C:/Users/chaker/Desktop/automation/circleci-test/PlayPro-UI-Tests-Selenium-Java-QA-Project/src/test/java/com/qa/Widgets/jsalerts.html");
        // click on prompt alert button
        WebElement promptBtn = driver.findElement(By.id("confirm"));
        promptBtn.click();
        // To handle alert, first we need to switch to alert
        Alert al = driver.switchTo().alert();
        // manage the actions of alert
        String AlertText = al.getText();
        System.out.println("Prompt Alert Text is: " + AlertText);
        al.dismiss();
        // check the message displayed on the page after accepting the prompt alert
        WebElement promptMsg = driver.findElement(By.id("lambdaTestDemo"));
        System.out.println("Prompt Alert Message is: " + promptMsg.getText());
        // Capture Screen Shot fo Browser
        File srcFile2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile2, new File("src/test/java/com/qa/tests/Screenshots/allure/ConfirmAlert2.png"));
    }
}
