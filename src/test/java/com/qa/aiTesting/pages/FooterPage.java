package com.qa.aiTesting.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Page object for the Swag Labs inventory footer. */
public class FooterPage {
    private static final By FOOTER = By.tagName("footer");
    private static final By COPYRIGHT = By.cssSelector("footer .footer_copy");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public FooterPage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void scrollToFooter() {
        WebElement footer = wait.until(ExpectedConditions.presenceOfElementLocated(FOOTER));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'end'});", footer);
        wait.until(ExpectedConditions.visibilityOf(footer));
    }

    public boolean isVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(FOOTER)).isDisplayed();
    }

    public String getCopyrightText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(COPYRIGHT)).getText();
    }

    public String getSocialLinkHref(String network) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(socialLink(network))).getAttribute("href");
    }

    public boolean clickSocialLink(String network) {
        return clickAndReturnToInventory(socialLink(network));
    }

    public String getLegalLinkHref(String linkText) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(linkText))).getAttribute("href");
    }

    public boolean clickLegalLink(String linkText) {
        return clickAndReturnToInventory(By.partialLinkText(linkText));
    }

    private By socialLink(String network) {
        return switch (network.toLowerCase()) {
            case "x" -> By.cssSelector("footer a[href*='x.com'], footer a[href*='twitter.com']");
            case "facebook" -> By.cssSelector("footer a[href*='facebook.com']");
            case "linkedin" -> By.cssSelector("footer a[href*='linkedin.com']");
            default -> throw new IllegalArgumentException("Unknown social network: " + network);
        };
    }

    /** Clicks a footer link and restores the inventory tab if it opened or navigated away. */
    private boolean clickAndReturnToInventory(By linkLocator) {
        String inventoryHandle = driver.getWindowHandle();
        String inventoryUrl = driver.getCurrentUrl();
        int originalWindowCount = driver.getWindowHandles().size();

        wait.until(ExpectedConditions.elementToBeClickable(linkLocator)).click();
        boolean navigationObserved = false;
        try {
            navigationObserved = new WebDriverWait(driver, Duration.ofSeconds(4)).until(current ->
                    current.getWindowHandles().size() > originalWindowCount
                            || !current.getCurrentUrl().equals(inventoryUrl));
        } catch (TimeoutException ignored) {
            // A demo environment may block the external page while retaining a valid href.
        }

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(inventoryHandle)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }

        driver.switchTo().window(inventoryHandle);
        if (!driver.getCurrentUrl().equals(inventoryUrl)) {
            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOfElementLocated(FOOTER));
        }
        return navigationObserved;
    }
}
