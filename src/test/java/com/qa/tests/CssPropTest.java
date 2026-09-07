package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CssPropTest {
    // Define a web driver
    WebDriver driver;

    // Launching the browser
    public void launchBrowser() {
        driver = new ChromeDriver();
        // handle and set the browser size
        Dimension d = new Dimension(400, 600);
        driver.manage().window().setSize(d);
    }

    public void getCssProp() {
        driver.get("https://www.facebook.com/");
        System.out.println(driver.findElement(By.name("email")).getCssValue("font-size"));
    }

    public static void main(String[] args) {
        CssPropTest test = new CssPropTest();
        test.launchBrowser();
        test.getCssProp();
    }
}

