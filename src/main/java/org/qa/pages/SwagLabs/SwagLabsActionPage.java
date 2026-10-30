package org.qa.pages.SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

public class SwagLabsActionPage {

    // Page Object Web Driver
    private final WebDriver driver;

    public SwagLabsActionPage(WebDriver driver) {
        this.driver = driver;
    }

    // ACTIONS
    public List<WebElement> getList(By by) {
        try {
            return driver.findElements(by);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element Not Exist : " + e.getMessage());
        }
    }

    public void clickOnElement(Object locatorOrElement) {
        try {
            WebElement element;
            // If you use locator By
            if (locatorOrElement instanceof By) {
                element = driver.findElement((By) locatorOrElement);
            } else if (locatorOrElement instanceof WebElement) {
                // If you use web element
                element = (WebElement) locatorOrElement;
            } else {
                throw new IllegalArgumentException("Parameter should be of type By or WebElement");
            }
            element.click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element Not Exist : " + e.getMessage());
        }
    }

    public void enter(By by, String value) {
        try {
            driver.findElement(by).click();
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(value);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element Not Exist : " + e.getMessage());
        }
    }

    public boolean isContain(String Text) {
        try {
            String currentURL = driver.getCurrentUrl();
            return currentURL != null && currentURL.contains(Text);
        } catch (Exception e) {
            throw new RuntimeException("Impossible to retrieve URL of current Page", e);
        }
    }

    public boolean isTitle(String expectedTitle) {
        try {
            String currentTITLE = driver.getTitle();
            return currentTITLE != null && currentTITLE.equals(expectedTitle);
        } catch (Exception e) {
            throw new RuntimeException("Impossible to retrieve URL of current Page", e);
        }
    }

    public boolean isElementDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No such element : " + e.getMessage());
        }
    }

    public String getElementText(By by) {
        try {
            return driver.findElement(by).getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("cannot get Text of this element:  " + e.getMessage());
        }
    }

    public boolean compareAttribute(By by, String attribute, String expectedValue) {
        try {
            System.out.println("Attribute's Element : " + driver.findElement(by).getDomAttribute(attribute));
            return Objects.equals(driver.findElement(by).getDomAttribute(attribute), expectedValue);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("cannot get Text of this element:  " + e.getMessage());
        }
    }


    public boolean compareElement(By by, String expectedValue) {
        try {
            return driver.findElement(by).getText().equals(expectedValue);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("cannot compare this element:  " + e.getMessage());
        }
    }
}
