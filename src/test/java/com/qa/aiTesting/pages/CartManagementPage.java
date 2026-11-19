package com.qa.aiTesting.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

/** Backward-compatible facade composed from Swag Labs domain page objects. */
public class CartManagementPage {
    private final LoginPage loginPage;
    private final InventoryPage inventoryPage;
    private final CartPage cartPage;
    private final MenuComponent menu;

    public CartManagementPage(WebDriver driver, Duration timeout) {
        loginPage = new LoginPage(driver, timeout);
        inventoryPage = new InventoryPage(driver, timeout);
        cartPage = new CartPage(driver, timeout);
        menu = new MenuComponent(driver, timeout);
    }

    public void openLoginPage() { loginPage.open(); }
    public void login(String username, String password) { loginPage.login(username, password); inventoryPage.waitUntilLoaded(); }
    public void addProduct(String productId, String count) { inventoryPage.addProduct(productId, count); }
    public String getCartBadgeText() { return inventoryPage.getCartBadgeText(); }
    public void removeProductFromInventory(String productId, String count) { inventoryPage.removeProduct(productId, count); }
    public void openCart() { cartPage.open(); }
    public void removeProductFromCart(String productId, String count) { cartPage.removeProduct(productId, count); }
    public void resetAppState() { menu.open(); menu.resetAppState(); inventoryPage.waitForCartBadgeToDisappear(); }
    public boolean isCartBadgeAbsentFromDom() { return inventoryPage.isCartBadgeAbsentFromDom(); }
}
