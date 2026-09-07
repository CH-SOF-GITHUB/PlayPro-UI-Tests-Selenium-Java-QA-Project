package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchTraining {

    WebDriver driver;

    // launching the firefox browser
    public void LaunchBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://www.simplilearn.com");
    }

    // Searching for selenium training and click in it
    public void Search() throws InterruptedException {
        driver.findElement(By.id("header_srch")).sendKeys("Selenium");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@class='input-search-btn']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//h5[text()='Introduction to Selenium 4']")).click();
        System.out.println("The page title is : " + driver.getTitle());
    }

    //Close the browser
    public void CloseBrowser() {
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        SearchTraining obj = new SearchTraining();
        obj.LaunchBrowser();
        obj.Search();
        obj.CloseBrowser();
    }
}
