package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectDrop {
    // Define a web driver
    WebDriver driver;

    // Launching the browser
    public void launchBrowser() {
        driver = new ChromeDriver();
    }

    public void selectDrop() {
        driver.get("https://www.facebook.com/reg/?entry_point=login");
        // First find the Day drop down using any attribute
        WebElement day_ele = driver.findElement(By.id("_r_3_"));

        // You need to use select class to work with drop down boxes
        Select day_sel = new Select(day_ele);

        // Select the day : by value
        day_sel.selectByValue("10");
        System.out.println("Selected Day is : " + day_sel.getFirstSelectedOption().getText());
    }

    public static void main(String[] args) throws InterruptedException {
        SelectDrop selectDrop = new SelectDrop();
        selectDrop.launchBrowser();
        Thread.sleep(5000);
        selectDrop.selectDrop();
    }


}
