package com.qa.aiTesting.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Page object for an individual Swag Labs product detail view. */
public class ProductDetailsPage {
    private static final By ADD_TO_CART_BUTTON = By.id("add-to-cart");
    private static final By PRODUCT_ACTION_BUTTON = By.cssSelector(
            "#add-to-cart, .btn.btn_secondary.btn_small.btn_inventory");
    private static final By CART_BADGE = By.className("shopping_cart_badge");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ProductDetailsPage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public String getName() { return visible(By.className("inventory_details_name")).getText(); }
    public String getDescription() { return visible(By.className("inventory_details_desc")).getText(); }
    public String getPrice() { return visible(By.className("inventory_details_price")).getText(); }

    public boolean isDetailedImageLoaded() {
        WebElement image = visible(By.className("inventory_details_img"));
        return Boolean.TRUE.equals(((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && arguments[0].naturalWidth > 0", image));
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(ADD_TO_CART_BUTTON)).click();
        wait.until(ExpectedConditions.textToBe(PRODUCT_ACTION_BUTTON, "Remove"));
        wait.until(ExpectedConditions.textToBe(CART_BADGE, "1"));
    }

    public String getCartBadgeText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CART_BADGE)).getText();
    }

    public void removeFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(PRODUCT_ACTION_BUTTON)).click();
        wait.until(currentDriver -> currentDriver.findElements(CART_BADGE).isEmpty());
        wait.until(ExpectedConditions.textToBe(PRODUCT_ACTION_BUTTON, "Add to cart"));
    }

    public boolean isCartBadgeAbsentFromDom() {
        return driver.findElements(CART_BADGE).isEmpty();
    }

    public String getProductActionText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_ACTION_BUTTON)).getText();
    }

    public void backToProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products"))).click();
        wait.until(ExpectedConditions.urlContains("inventory.html"));
    }

    private WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
