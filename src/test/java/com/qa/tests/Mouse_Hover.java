package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Mouse_Hover {


    public static void main(String[] args) throws InterruptedException {
        // Define a selenium web driver
        WebDriver driver;
        // create a selenium web driver
        driver = new ChromeDriver();
        // define the mouse actions selenium class
        Actions actions = new Actions(driver);

        // Scenario: Mouse hover on Prime Element in Amazon.in
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement PrimeLink = driver.findElement(By.linkText("Prime"));
        actions.moveToElement(PrimeLink).perform();
        Thread.sleep(5000);
        WebElement JoinPrimeLink = driver.findElement(By.linkText("Join Prime Now"));
        assert JoinPrimeLink.isDisplayed() : "Prime link is not displayed";
        JoinPrimeLink.click();
        System.out.println("URL of Join Prime Link is: " + driver.getCurrentUrl());
        assert driver.getCurrentUrl().equals("https://www.amazon.in/amazonprime");
        Thread.sleep(2000);
        driver.quit();
    }
}
