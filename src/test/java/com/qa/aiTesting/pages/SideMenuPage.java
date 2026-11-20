package com.qa.aiTesting.pages;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page object for the Swag Labs left-side navigation menu.
 */
public class SideMenuPage {
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By MENU_CLOSE_BUTTON = By.id("react-burger-cross-btn");
    private static final By MENU_WRAP = By.className("bm-menu-wrap");
    private static final By ALL_ITEMS_LINK = By.id("inventory_sidebar_link");
    private static final By ABOUT_LINK = By.id("about_sidebar_link");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");
    private static final By RESET_APP_STATE_LINK = By.id("reset_sidebar_link");
    private static final By DYNAMIC_CATALOG_LINK = By.id("dynamic_catalog_sidebar_link");
    private static final By DYNAMIC_CATALOG_SPINNER_LINK = By.id("dynamic_catalog_spinner_link");
    private static final By DYNAMIC_CATALOG_SLIDER_LINK = By.id("dynamic_catalog_slider_link");
    private static final By CART_BADGE = By.className("shopping_cart_badge");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SideMenuPage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public boolean isClosed() {
        return wait.until(ExpectedConditions.attributeToBe(MENU_WRAP, "aria-hidden", "true"));
    }

    public void open() {
        wait.until(ExpectedConditions.elementToBeClickable(MENU_BUTTON)).click();
        wait.until(ExpectedConditions.attributeToBe(MENU_WRAP, "aria-hidden", "false"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ALL_ITEMS_LINK));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ABOUT_LINK));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_LINK));
        wait.until(ExpectedConditions.visibilityOfElementLocated(RESET_APP_STATE_LINK));
    }

    public boolean arePrimaryLinksVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ALL_ITEMS_LINK)).isDisplayed()
                && wait.until(ExpectedConditions.visibilityOfElementLocated(ABOUT_LINK)).isDisplayed()
                && wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_LINK)).isDisplayed()
                && wait.until(ExpectedConditions.visibilityOfElementLocated(RESET_APP_STATE_LINK)).isDisplayed();
    }

    public void close() {
        wait.until(ExpectedConditions.elementToBeClickable(MENU_CLOSE_BUTTON)).click();
        wait.until(ExpectedConditions.attributeToBe(MENU_WRAP, "aria-hidden", "true"));
    }

    public void clickAllItems() {
        wait.until(ExpectedConditions.elementToBeClickable(ALL_ITEMS_LINK)).click();
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));
    }

    public void clickAbout() {
        wait.until(ExpectedConditions.elementToBeClickable(ABOUT_LINK)).click();
        wait.until(ExpectedConditions.urlContains("saucelabs.com"));
    }

    public boolean isAboutDestination() {
        return driver.getCurrentUrl().contains("saucelabs.com");
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
    }

    public void resetAppState() {
        wait.until(ExpectedConditions.elementToBeClickable(RESET_APP_STATE_LINK)).click();
        wait.until(driver -> isCartBadgeAbsentFromDom());
    }

    public boolean isCartBadgeAbsentFromDom() {
        return driver.findElements(CART_BADGE).isEmpty();
    }

    /**
     * Inspects Dynamic Catalog and records toggle, submenu, or navigation behavior when available.
     */
    public DynamicCatalogObservation inspectDynamicCatalog() {
        List<WebElement> links = driver.findElements(DYNAMIC_CATALOG_LINK);
        if (links.isEmpty() || !links.get(0).isDisplayed()) {
            return new DynamicCatalogObservation(false, false, false, null, null, false, false);
        }

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(DYNAMIC_CATALOG_LINK));
        boolean clickable = link.isEnabled();
        if (!clickable) {
            String expanded = link.getAttribute("aria-expanded");
            return new DynamicCatalogObservation(true, false, false, expanded, expanded,
                    isDynamicCatalogSubmenuVisible(), false);
        }

        String expandedBefore = link.getAttribute("aria-expanded");
        String originalUrl = driver.getCurrentUrl();
        link.click();
        try {
            wait.until(currentDriver -> !Objects.equals(originalUrl, currentDriver.getCurrentUrl())
                    || !Objects.equals(expandedBefore,
                    currentDriver.findElement(DYNAMIC_CATALOG_LINK).getAttribute("aria-expanded"))
                    || isDynamicCatalogSubmenuVisible());
        } catch (TimeoutException ignored) {
            // Some implementations react only visually and expose no stable submenu state.
        }

        List<WebElement> refreshedLinks = driver.findElements(DYNAMIC_CATALOG_LINK);
        String expandedAfter = refreshedLinks.isEmpty() ? null : refreshedLinks.get(0).getAttribute("aria-expanded");
        boolean navigationChanged = !Objects.equals(originalUrl, driver.getCurrentUrl());
        boolean submenuVisible = isDynamicCatalogSubmenuVisible();
        return new DynamicCatalogObservation(true, true, true, expandedBefore, expandedAfter,
                submenuVisible, navigationChanged);
    }

    private boolean isDynamicCatalogSubmenuVisible() {
        return driver.findElements(DYNAMIC_CATALOG_SPINNER_LINK).stream().anyMatch(WebElement::isDisplayed)
                || driver.findElements(DYNAMIC_CATALOG_SLIDER_LINK).stream().anyMatch(WebElement::isDisplayed);
    }

    public record DynamicCatalogObservation(boolean present, boolean clickable, boolean clickPerformed,
                                            String expandedBefore, String expandedAfter, boolean submenuVisible,
                                            boolean navigationChanged) {
        public boolean stateObserved() {
            return navigationChanged || submenuVisible || !Objects.equals(expandedBefore, expandedAfter);
        }
    }
}
