package org.qa.actionDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.base.BaseClass;

import java.time.Duration;

public class ActionDriver {
    private WebDriver driver;
    private WebDriverWait wait;

    // add a constructor of class
    public ActionDriver(WebDriver driver) {
        this.driver = driver;
        int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicit"));
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(explicitWait));
    }

    // Method to clic kin element
    public void click(By by) {
        try {
            waitForElementToBeClickable(by);
            driver.findElement(by).click();
        } catch (Exception e) {
            System.out.println("Element is not clickable: " + e.getMessage());
        }
    }

    // Method to enter text into input field
    public void enter(By by, String text) {
        try {
            waitForElementToBeVisible(by);
            driver.findElement(by).sendKeys(text);
        } catch (Exception e) {
            System.out.println("Element is not entered: " + e.getMessage());
        }
    }

    // Method to get text from input field
    public String getText(By by) {
        try {
            waitForElementToBeVisible(by);
            return driver.findElement(by).getText();
        } catch (Exception e) {
            System.out.println("Element is not visible: " + e.getMessage());
            return null;
        }
    }

    // Method to compare Two Text - (change return type)
    public boolean compareText(By by, String expectedText) {
        try {
            waitForElementToBeVisible(by);
            String actualText = driver.findElement(by).getText();
            if (expectedText.equals(actualText)) {
                System.out.println("Actual Text : " + actualText + " Math" + " Expected: " + expectedText);
                return true;
            } else {
                System.out.println("ERROR=> Actual Text : " + actualText + " does not Math" + " Expected: " + expectedText);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Element is not visible: " + e.getMessage());
            return false;
        }
    }

    // Method to check if an element is displayed
    public boolean isDisplayed(By by) {
        try {
            waitForElementToBeVisible(by);
            boolean isDisplayed = driver.findElement(by).isDisplayed();
            if (isDisplayed) {
                System.out.println("Element is displayed");
                return true;
            } else {
                System.out.println("Element is not displayed");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Element is not displayed: " + e.getMessage());
            return false;
        }
    }

    // Method to scroll to an element
    public void scrollToElement(By by) {
        try {
            waitForElementToBeVisible(by);
            WebElement element = driver.findElement(by);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            System.out.println("Element is not visible: " + e.getMessage());
        }
    }

    // Method to wait and load page
    public void waitForPageLoad(int timeOutInSec) {
        try {
            wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete"));
            System.out.println("Page load successfully!");
        } catch (Exception e) {
            System.out.println("Page is not loaded within " + timeOutInSec + " seconds. Exception: " + e.getMessage());
        }
    }

    // Wait for element to be clickable
    public void waitForElementToBeClickable(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            System.out.println("Element is not clickable: " + e.getMessage());
        }
    }

    // Wait for element to be visible
    public void waitForElementToBeVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("Element is not visible: " + e.getMessage());
        }
    }
}
