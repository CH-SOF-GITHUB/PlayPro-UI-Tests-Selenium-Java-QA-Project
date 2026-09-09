package org.qa.actionDriver;

import org.apache.logging.log4j.Logger;
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

    public static final Logger loggr = BaseClass.loggr;

    // add a constructor of class
    public ActionDriver(WebDriver driver) {
        this.driver = driver;
        int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicit"));
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(explicitWait));
        loggr.info("WebDriver instance is created for : {}", this.getClass().getSimpleName());
    }

    // Method to clic kin element
    public void click(By by) {
        String elementDescription = getElementDescription(by);
        try {
            waitForElementToBeClickable(by);
            driver.findElement(by).click();
            loggr.info("Clicked on element  ---> : {}", elementDescription);
        } catch (Exception e) {
            loggr.error("Element is not clickable: {}", e.getMessage());
        }
    }

    // Method to enter text into input field
    public void enter(By by, String text) {
        String elementDescription = getElementDescription(by);
        try {
            waitForElementToBeVisible(by);
            driver.findElement(by).sendKeys(text);
            loggr.info("Value entered on {} is  {}", elementDescription, text);
        } catch (Exception e) {
            loggr.error("Element is not entered: {}", e.getMessage());
        }
    }

    // Method to get text from input field
    public String getText(By by) {
        try {
            waitForElementToBeVisible(by);
            return driver.findElement(by).getText();
        } catch (Exception e) {
            loggr.error("Element is not visible: {}", e.getMessage());
            return null;
        }
    }

    // Method to compare Two Text - (change return type)
    public boolean compareText(By by, String expectedText) {
        try {
            waitForElementToBeVisible(by);
            String actualText = driver.findElement(by).getText();
            if (expectedText.equals(actualText)) {
                loggr.info("Actual Text : {} match to | Expected Value: {}", actualText, expectedText);
                return true;
            } else {
                loggr.info("ERROR: Actual Text : {} does not match | Expected Value: {}", actualText, expectedText);
                return false;
            }
        } catch (Exception e) {
            loggr.error("Element compared is not visible: {}", e.getMessage());
            return false;
        }
    }

    // Method to check if an element is displayed
    public boolean isDisplayed(By by) {
        String elementDescription = getElementDescription(by);
        try {
            waitForElementToBeVisible(by);
            boolean isDisplayed = driver.findElement(by).isDisplayed();
            if (isDisplayed) {
                loggr.info("Element : {} is displayed", elementDescription);
                return true;
            } else {
                loggr.info("Element : {} is not displayed", elementDescription);
                return false;
            }
        } catch (Exception e) {
            loggr.error("Element is not displayed: {}", e.getMessage());
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
            loggr.error("Element to scroll is not visible: {}", e.getMessage());
        }
    }

    // Method to wait and load page
    public void waitForPageLoad(int timeOutInSec) {
        try {
            wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete"));
            loggr.info("Page load successfully!");
        } catch (Exception e) {
            loggr.error("Page is not loaded within: {}  seconds. Exception: {}", timeOutInSec, e.getMessage());
        }
    }

    // Wait for element to be clickable
    public void waitForElementToBeClickable(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            loggr.error("This Element is not clickable: {}", e.getMessage());
        }
    }

    // Wait for element to be visible
    public void waitForElementToBeVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            loggr.error("This Element is not visible: {}", e.getMessage());
        }
    }


    // Method to get the description of an element using a locator
    public String getElementDescription(By locator) {
        // Check for null driver or locator to avoid NulPointer Exception
        if (driver == null) {
            return "driver is null";
        }
        if (locator == null) {
            return "locator is null";
        }
        try {
            // find the element using locator
            WebElement element = driver.findElement(locator);

            // get element attributes
            String name = element.getDomAttribute("name");
            String id = element.getDomAttribute("id");
            String text = element.getText();
            String className = element.getDomAttribute("class");
            String placeHolder = element.getDomAttribute("placeholder");

            // Return the description based on element attribute
            if (isNotEmpty(name)) {
                return "Element with name: " + name;
            } else if (isNotEmpty(id)) {
                return "Element with id: " + id;
            } else if (isNotEmpty(text)) {
                return "Element with text: " + truncate(text, 50);
            } else if (isNotEmpty(className)) {
                return "Element with class: " + className;
            } else if (isNotEmpty(placeHolder)) {
                return "Element with placeholder: " + placeHolder;
            }
        } catch (Exception e) {
            loggr.error("Unable to describe the element: {}", e.getMessage());
        }
        return "Unable to describe the element";
    }

    // Utility method to check a string is not null or empty
    private boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    // Utility Method to truncate long string
    private String truncate(String value, int maxLength) {
        if (value == null || value.length() <= maxLength) {
            return value;
        }
        return value.substring(0, maxLength) + "...";
    }
}
