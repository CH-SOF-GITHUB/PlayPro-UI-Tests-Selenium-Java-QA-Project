package com.qa.tests.JUnit5;
import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.qa.pages.SwagLabs.SwagLabsActionPage;

import java.util.List;

public class Junit5InventoryProductSortingTests extends AbstractChromeWebDriverTest {

    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    //
    private static final By _PRODUCT_ITEMS = By.className("inventory_item");
    private static final By INVENTORY_PRODUCT_NAME = By.className("inventory_item_name");
    private static final By INVENTORY_PRODUCT_PRICE = By.className("inventory_item_price");
    //
    private static final By INVENTORY_PRODUCT_SORT = By.className("product_sort_container");

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
        // 1. Log in to Swag Labs
        login();
    }

    // TESTS
    /*
    Goal: Verify that the product sorting dropdown on the Swag Labs inventory page (saucedemo.com/inventory.html) functions accurately for all four available filter options:
    Name (A to Z)
    Name (Z to A)
    Price (low to high)
    Price (high to low)
    * */

    // TC-SORT-01: Name (A to Z) - Default Alphabetical Order
    @Test
    public void testATOZDefaultAlphabeticalOrder() throws InterruptedException {
        // Active dropdown label displays "Name (A to Z)".
        WebElement sort = driver.findElement(INVENTORY_PRODUCT_SORT);
        Select sortSelect = new Select(sort);
        sortSelect.selectByValue("az");
        Thread.sleep(500);
        // First product is "Sauce Labs Backpack" ($29.99).
        boolean isFirstProductByName = getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME).getText().equals("Sauce Labs Backpack");
        boolean isFirstProductByPrice = getProducts().get(0).findElement(INVENTORY_PRODUCT_PRICE).getText().equals("$29.99");
        //System.out.println(getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME).getText());
        Assertions.assertTrue(isFirstProductByName && isFirstProductByPrice);
        // Last product is "Test.allTheThings() T-Shirt (Red)" ($15.99).
        boolean isLastProductByName = getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_NAME).getText().equals("Test.allTheThings() T-Shirt (Red)");
        boolean isLastProductByPrice = getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_PRICE).getText().equals("$15.99");
        Assertions.assertTrue(isLastProductByName && isLastProductByPrice);
    }

    // TC-SORT-02: Name (Z to A) - Reverse Alphabetical Order
    @Test
    public void testZTOANameReverseAlphabeticalOrder() throws InterruptedException {
        // Active dropdown label displays "Name (A to Z)".
        WebElement sort = driver.findElement(INVENTORY_PRODUCT_SORT);
        Select sortSelect = new Select(sort);
        sortSelect.selectByValue("za");
        Thread.sleep(500);
        // First product is "Sauce Labs Backpack" ($29.99).
        boolean isFirstProductByName = getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME).getText().equals("Test.allTheThings() T-Shirt (Red)");
        boolean isFirstProductByPrice = getProducts().get(0).findElement(INVENTORY_PRODUCT_PRICE).getText().equals("$15.99");
        //System.out.println(getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME).getText());
        Assertions.assertTrue(isFirstProductByName && isFirstProductByPrice);
        // Last product is "Test.allTheThings() T-Shirt (Red)" ($15.99).
        boolean isLastProductByName = getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_NAME).getText().equals("Sauce Labs Backpack");
        boolean isLastProductByPrice = getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_PRICE).getText().equals("$29.99");
        Assertions.assertTrue(isLastProductByName && isLastProductByPrice);
    }

    // TC-SORT-03: Price (low to high) - Ascending Price Order
    @Test
    public void testPriceLowToHighAscendingPriceOrder() throws InterruptedException {
        // Active dropdown label displays "Name (A to Z)".
        WebElement sort = driver.findElement(INVENTORY_PRODUCT_SORT);
        Select sortSelect = new Select(sort);
        sortSelect.selectByValue("lohi");
        Thread.sleep(500);
        // First product is "Sauce Labs Backpack" ($29.99).
        boolean isFirstProductByName = getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME).getText().equals("Sauce Labs Onesie");
        boolean isFirstProductByPrice = getProducts().get(0).findElement(INVENTORY_PRODUCT_PRICE).getText().equals("$7.99");
        //System.out.println(getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME).getText());
        Assertions.assertTrue(isFirstProductByName && isFirstProductByPrice);
        // Last product is "Test.allTheThings() T-Shirt (Red)" ($15.99).
        boolean isLastProductByName = getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_NAME).getText().equals("Sauce Labs Fleece Jacket");
        boolean isLastProductByPrice = getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_PRICE).getText().equals("$49.99");
        Assertions.assertTrue(isLastProductByName && isLastProductByPrice);
    }

    // TC-SORT-04: Price (high to low) - Descending Price Order
    @Test
    public void testPriceHighToLowDescendingPriceOrder() throws InterruptedException {
        // Active dropdown label displays "Name (Z to A)".
        WebElement sort = driver.findElement(INVENTORY_PRODUCT_SORT);
        Select sortSelect = new Select(sort);
        sortSelect.selectByValue("hilo");
        Thread.sleep(500);
        // First product is "Sauce Labs Backpack" ($29.99).
        boolean isFirstProductByName = getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME).getText().equals("Sauce Labs Fleece Jacket");
        boolean isFirstProductByPrice = getProducts().get(0).findElement(INVENTORY_PRODUCT_PRICE).getText().equals("$49.99");
        //System.out.println(getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME).getText());
        Assertions.assertTrue(isFirstProductByName && isFirstProductByPrice);
        // Last product is "Test.allTheThings() T-Shirt (Red)" ($15.99).
        boolean isLastProductByName = getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_NAME).getText().equals("Sauce Labs Onesie");
        boolean isLastProductByPrice = getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_PRICE).getText().equals("$7.99");
        Assertions.assertTrue(isLastProductByName && isLastProductByPrice);
    }

    // Actions
    // sample login method
    private void login() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
    }

    private List<WebElement> getProducts() {
        return swagLabsActionPage.getList(_PRODUCT_ITEMS);
    }
}
