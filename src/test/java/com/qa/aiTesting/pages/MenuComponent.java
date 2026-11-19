package com.qa.aiTesting.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Reusable burger-menu actions shared by Swag Labs pages. */
public class MenuComponent {
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By ABOUT_LINK = By.id("about_sidebar_link");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");
    private static final By RESET_LINK = By.id("reset_sidebar_link");
    private final WebDriver driver;
    private final WebDriverWait wait;

    public MenuComponent(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void open() {
        wait.until(ExpectedConditions.elementToBeClickable(MENU_BUTTON)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ABOUT_LINK));
    }

    public void clickAbout() {
        wait.until(ExpectedConditions.elementToBeClickable(ABOUT_LINK)).click();
        wait.until(ExpectedConditions.urlContains("saucelabs.com"));
    }

    public boolean isAboutPage() { return driver.getCurrentUrl().contains("saucelabs.com"); }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
    }

    public void resetAppState() {
        wait.until(ExpectedConditions.elementToBeClickable(RESET_LINK)).click();
        wait.until(driver -> driver.findElements(By.className("shopping_cart_badge")).isEmpty());
    }
}
