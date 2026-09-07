package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorsByLinks {

    // Define a web driver
    WebDriver driver;

    // Launching the browser
    public void launchBrowser() {
        driver = new ChromeDriver();
    }

    public void locateByLinkText() {
        driver.get("https://www.amazon.in/");
        driver.findElement(By.linkText("Today's Deals")).click();
    }

    public void locateByPartialLinkText() {
        driver.get("https://www.amazon.in/");
        driver.findElement(By.partialLinkText("Service")).click();
    }

    public static void main(String[] args) throws InterruptedException {
        LocatorsByLinks obj = new LocatorsByLinks();
        obj.launchBrowser();
        Thread.sleep(5000);
        obj.locateByLinkText();
        Thread.sleep(5000);
        obj.locateByPartialLinkText();
    }
}
