package org.qa.actionDriver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.base.BaseClass;
import org.qa.utilities.ExtentManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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
            applyBorder(by, "green");
            waitForElementToBeClickable(by);
            driver.findElement(by).click();
            // Add extent manager to log a step for the report
            ExtentManager.logStep("Clicked on element  ---> : " + elementDescription);
            loggr.info("Clicked on element  ---> : {}", elementDescription);
        } catch (Exception e) {
            applyBorder(by, "red");
            ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Unable to click en this element!", elementDescription + "_unable_to_click");
            loggr.error("Element is not clickable: {}", e.getMessage());
        }
    }

    // Method to enter text into input field
    public void enter(By by, String text) {
        String elementDescription = getElementDescription(by);
        try {
            applyBorder(by, "green");
            waitForElementToBeVisible(by);
            driver.findElement(by).sendKeys(text);
            // Add extent manager to log a step for the report
            ExtentManager.logStep("Value entered on: " + elementDescription + " is " + text);
            loggr.info("Value entered on {} is  {}", elementDescription, text);
        } catch (Exception e) {
            applyBorder(by, "red");
            ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Unable to enter a value on this element!", elementDescription + "_unable_to_enter_value");
            loggr.error("Element is not entered: {}", e.getMessage());
        }
    }

    // Method to get text from input field
    public String getText(By by) {
        try {
            applyBorder(by, "green");
            waitForElementToBeVisible(by);
            return driver.findElement(by).getText();
        } catch (Exception e) {
            applyBorder(by, "red");
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
                ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Compare Text!", "Test Verified successfully: " + expectedText + " equals " + actualText);
                applyBorder(by, "green");
                return true;
            } else {
                loggr.info("ERROR: Actual Text : {} does not match | Expected Value: {}", actualText, expectedText);
                ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Text Comparison fails!", "Test failed: " + expectedText + " not equals " + actualText);
                applyBorder(by, "red");
                return false;
            }
        } catch (Exception e) {
            applyBorder(by, "red");
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
                ExtentManager.logStep("Element is displayed: " + elementDescription);
                ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Element is displayed !", elementDescription + "_is_displayed");
                loggr.info("Element : {} is displayed", elementDescription);
                applyBorder(by, "green");
                return true;
            } else {
                loggr.info("Element : {} is not displayed", elementDescription);
                applyBorder(by, "red");
                return false;
            }
        } catch (Exception e) {
            applyBorder(by, "red");
            ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Element is not displayed !", elementDescription + "_is_not_displayed");
            loggr.error("Element is not displayed: {}", e.getMessage());
            return false;
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

    // ========================= Select Methods ================================
    // Method to select a dropdown by visible text
    public void selectByVisibleText(By by, String TextOption) {
        try {
            WebElement element = driver.findElement(by);
            new Select(element).selectByVisibleText(TextOption);
            applyBorder(by, "green");
            loggr.info("Selected dropdown text value : {}", TextOption);
        } catch (Exception e) {
            applyBorder(by, "red");
            loggr.info("Unable to select a dropdown text value : {}", e.getMessage());
            e.fillInStackTrace();
        }
    }

    // Method to select a dropdown by visible text
    public void selectByValue(By by, String ValueOption) {
        try {
            WebElement element = driver.findElement(by);
            new Select(element).selectByValue(ValueOption);
            applyBorder(by, "green");
            loggr.info("Selected dropdown value : {}", ValueOption);
        } catch (Exception e) {
            applyBorder(by, "red");
            loggr.info("Unable to select a dropdown value : {}", e.getMessage());
        }
    }

    // Method to select a dropdown by index
    public void selectByIndex(By by, String index) {
        try {
            WebElement element = driver.findElement(by);
            new Select(element).selectByValue(index);
            applyBorder(by, "green");
            loggr.info("Selected dropdown value by index : {}", index);
        } catch (Exception e) {
            applyBorder(by, "red");
            loggr.info("Unable to select a dropdown value by index : {}", e.getMessage());
        }
    }

    // Method to get all options from DropDown
    public List<String> getDroPDownOptions(By by) {
        List<String> options = new ArrayList<>();
        try {
            WebElement dropDownElement = driver.findElement(by);
            Select select = new Select(dropDownElement);
            for (WebElement option : select.getOptions()) {
                options.add(option.getText());
            }
            applyBorder(by, "green");
            loggr.info("Retrieved options from dropdown : {}", options);
            return options;
        } catch (Exception e) {
            applyBorder(by, "red");
            loggr.info("Unable to get dropdown options : {}", e.getMessage());
            return null;
        }
    }

    // ========================= JS Methods ================================
    // Method to scroll to an element
    public void scrollToElement(By by) {
        try {
            waitForElementToBeVisible(by);
            WebElement element = driver.findElement(by);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            applyBorder(by, "green");
            loggr.info("Scrolled to element using JS : {}", getElementDescription(by));
        } catch (Exception e) {
            applyBorder(by, "red");
            loggr.error("Element to scroll is not visible: {}", e.getMessage());
        }
    }

    // Utility Method to border an element: Highlight element
    public void applyBorder(By by, String color) {
        try {
            // Locate the lement
            WebElement element = driver.findElement(by);
            String eleDis = getElementDescription(by);
            // Apply the border
            String script = "arguments[0].style.border = '3px solid " + color + "'";
            ((JavascriptExecutor) driver).executeScript(script, element);
            loggr.info("Border applied on {} with color {}", eleDis, color);
        } catch (Exception e) {
            loggr.error("Error when try to apply color in a border: {}", e.getMessage());
        }
    }

    // Method to click on element by JS
    public void clickUsingJS(By by) {
        try {
            WebElement element = driver.findElement(by);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            applyBorder(by, "green");
            loggr.info("Clicked on element using JS : {}", getElementDescription(by));
        } catch (Exception e) {
            applyBorder(by, "red");
            loggr.error("Unable to click using JS element: {}", e.getMessage());
        }
    }

    // ========================= Window and Frame Methods ================================
    // Method to switch between browser widows
    // Method to switch to an iframe
    public void switchToFrame(By by) {
        try {
            WebElement frame = driver.findElement(by);
            driver.switchTo().frame(frame);
            loggr.info("Switched to Frame : {}", getElementDescription(by));
        } catch (Exception e) {
            loggr.info("Unable to Switch to Frame : {}", e.getMessage());
        }
    }

    // Method to default content
    public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
            loggr.info("Switched to Default content !");
        } catch (Exception e) {
            loggr.info("Unable to Switch to Default Content : {}", e.getMessage());
        }
    }

    // ========================= Alert Handling Methods ================================
    // Method to accept an alert
    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
            loggr.info("Switched to alert and accepted !");
        } catch (Exception e) {
            loggr.info("Unable to Switch to Alert and Accept : {}", e.getMessage());
        }
    }

    // Method to accept an alert
    public void dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
            loggr.info("Switched to alert and dismissed !");
        } catch (Exception e) {
            loggr.info("Unable to Switch to Alert and Dismissed : {}", e.getMessage());
        }
    }

    // Method to accept an alert
    public String getAlertText() {
        try {
            String Text = driver.switchTo().alert().getText();
            loggr.info("Switched to alert and retrieved text : {}", Text);
            return Text;
        } catch (Exception e) {
            loggr.info("Unable to Switch to Alert and Retrieve text : {}", e.getMessage());
            return "";
        }
    }

    // ========================= Alert Handling Methods ================================
    // Method to load a browser
    public void refreshPage() {
        try {
            driver.navigate().refresh();
            loggr.info("Loading a page in Browser !");
        } catch (Exception e) {
            loggr.info("Unable to load a browser : {}", e.getMessage());
        }
    }

    // Method to get current URL of Page
    public String getCurrentURL() {
        try {
            loggr.info("Retrieved a current URL of Page : {}", driver.getCurrentUrl());
            return driver.getCurrentUrl();
        } catch (Exception e) {
            loggr.info("Unable to Retrieve a current URL of Page: {}", e.getMessage());
            return null;
        }
    }

    // Method to maximize a browser
    public void maximizeWindow() {
        try {
            driver.manage().window().maximize();
            loggr.info("Maximized a Browser !");
        } catch (Exception e) {
            loggr.info("Unable to maximize a browser : {}", e.getMessage());
        }
    }
}
