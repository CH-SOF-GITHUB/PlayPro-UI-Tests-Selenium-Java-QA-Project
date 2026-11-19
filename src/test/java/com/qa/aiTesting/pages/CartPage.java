package com.qa.aiTesting.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Page object for the Swag Labs shopping cart. */
public class CartPage {
    private static final By CART_BADGE = By.className("shopping_cart_badge");
    private static final By CART_ITEMS = By.className("cart_item");
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CartPage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void open() {
        wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link"))).click();
        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

    public int getItemCount() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CART_ITEMS)).size();
    }

    public String getBadgeText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CART_BADGE)).getText();
    }

    public void waitForItemCount(int expectedCount) {
        wait.until(ExpectedConditions.numberOfElementsToBe(CART_ITEMS, expectedCount));
    }

    public void removeProduct(String productId, String expectedBadgeCount) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-" + productId))).click();
        wait.until(ExpectedConditions.textToBe(CART_BADGE, expectedBadgeCount));
    }

    public void checkout() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
    }
}
