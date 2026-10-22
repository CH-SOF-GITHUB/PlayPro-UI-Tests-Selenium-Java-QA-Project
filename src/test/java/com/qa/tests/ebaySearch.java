package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ebaySearch {

    WebDriver driver;

    // launching the browser
    public void launchBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://www.ebay.com");
    }

    // TC01
    public void searchProduct() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.id("gh-ac")).sendKeys("JBL Speakers");
        driver.findElement(By.id("gh-ac")).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        driver.findElement(By.linkText("Deals")).click();
    }

    // TC02
    public void navigate() throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().to("https://www.simplilearn.com");
        Thread.sleep(3000);
        driver.navigate().back();
        System.out.println("The page Title is : " + driver.getTitle());
    }


    public void closeBrowser() {
        driver.close();
    }

    public static void main(String[] args) throws InterruptedException {
        ebaySearch obj = new ebaySearch();
        obj.launchBrowser();
        obj.searchProduct();
        obj.navigate();
        obj.closeBrowser();
    }
}
