package com.qa.locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePageObject {
    protected WebDriver driver;

    // Constructor to initialize the WebDriver
    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    protected WebElement find(By locator) {
        // Implement a method to find elements using the locator
        // This is a placeholder; actual implementation will depend on the locator strategy
        return driver.findElement(locator);
    }

    protected List<WebElement> finds(By locator) {
        // Implement a method to find elements using the locator
        // This is a placeholder; actual implementation will depend on the locator strategy
        return driver.findElements(locator);
    }
}
