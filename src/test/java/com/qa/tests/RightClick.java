package com.qa.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class RightClick {

    public static void main(String[] args) throws InterruptedException {
        // Define a selenium web driver
        WebDriver driver;
        // create a selenium web driver
        driver = new ChromeDriver();
        // define the mouse actions selenium class
        Actions actions = new Actions(driver);

        // Scenario: Right Click on button
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        driver.manage().window().maximize();
        WebElement btn = driver.findElement(By.xpath("//span[text()='right click me']"));
        actions.contextClick(btn).perform();
        Thread.sleep(2000);

        actions.sendKeys(Keys.ARROW_DOWN).perform();
        Thread.sleep(1000);
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        Thread.sleep(1000);
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        Thread.sleep(1000);

        actions.sendKeys(Keys.RETURN).perform();

        // Handle Alert action
        Alert alert = driver.switchTo().alert();
        String AlertText = alert.getText();
        if (AlertText.equals("clicked: copy")) {
            System.out.println("Text Alert Verification passed!");
        } else {
            System.out.println("Text Alert Verification failed!");
        }
        Thread.sleep(1000);
        alert.accept();

        // Close the webdriver browser
        driver.quit();
    }

}
