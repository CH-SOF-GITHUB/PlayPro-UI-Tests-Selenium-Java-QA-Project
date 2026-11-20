package com.qa.aiTesting.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Page object for the Swag Labs inventory. */
public class InventoryPage {
    private static final By CONTAINER = By.id("inventory_container");
    private static final By PRODUCT_NAME = By.className("inventory_item_name");
    private static final By PRODUCT_PRICE = By.className("inventory_item_price");
    private static final By SORT = By.className("product_sort_container");
    private static final By CART_BADGE = By.className("shopping_cart_badge");
    private final WebDriver driver;
    private final WebDriverWait wait;

    public InventoryPage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void waitUntilLoaded() { wait.until(ExpectedConditions.visibilityOfElementLocated(CONTAINER)); }

    public boolean isLoaded() { return wait.until(ExpectedConditions.visibilityOfElementLocated(CONTAINER)).isDisplayed(); }

    public boolean isInventoryUrl() { return driver.getCurrentUrl().contains("inventory.html"); }

    public List<String> getProductNames() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_NAME));
        return driver.findElements(PRODUCT_NAME).stream().map(WebElement::getText).toList();
    }

    public List<Double> getProductPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_PRICE));
        return driver.findElements(PRODUCT_PRICE).stream().map(WebElement::getText)
                .map(value -> Double.valueOf(value.replace("$", "").trim())).toList();
    }

    public double getProductPrice(String productId) {
        WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-" + productId)));
        WebElement productCard = addButton.findElement(By.xpath("./ancestor::div[contains(@class, 'inventory_item')]"));
        String priceText = productCard.findElement(PRODUCT_PRICE).getText();
        return Double.parseDouble(priceText.replace("$", "").trim());
    }

    public void sortProducts(String sortValue) {
        new Select(wait.until(ExpectedConditions.elementToBeClickable(SORT))).selectByValue(sortValue);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_NAME));
    }

    public void openProduct(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(productName))).click();
    }

    public void addProduct(String productId, String expectedBadgeCount) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-" + productId))).click();
        wait.until(ExpectedConditions.textToBe(CART_BADGE, expectedBadgeCount));
    }

    public void removeProduct(String productId, String expectedBadgeCount) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-" + productId))).click();
        wait.until(ExpectedConditions.textToBe(CART_BADGE, expectedBadgeCount));
    }

    public String getCartBadgeText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CART_BADGE)).getText();
    }

    public boolean isCartBadgeAbsentFromDom() {
        return driver.findElements(CART_BADGE).isEmpty();
    }

    public void waitForCartBadgeToDisappear() {
        wait.until(driver -> driver.findElements(CART_BADGE).isEmpty());
    }

    /**
     * Recherche le premier bouton d'action visible ("Add to cart" ou "Remove") pour un identifiant produit donné.
     */
    public String getProductActionText(String productId) {
        // Cibler l'un OU l'autre ID dans le DOM:
        By productActionButton = By.cssSelector("#add-to-cart-" + productId + ", #remove-" + productId);
        return wait.until(currentDriver -> {
            return currentDriver.findElements(productActionButton).stream()
                    .filter(WebElement::isDisplayed)
                    .map(WebElement::getText)
                    .findFirst()
                    .orElse(null);
        });
    }

    public void openDirectly() {
        driver.get("https://www.saucedemo.com/inventory.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
    }

    public void navigateBackToInventory() {
        driver.navigate().back();
        waitUntilLoaded();
    }
}
