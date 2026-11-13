package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.qa.pages.SwagLabs.SwagLabsActionPage;

import java.util.List;

public class Junit5DynamicCatalogSpinnerTests extends AbstractChromeWebDriverTest {

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
    private static final By INVENTORY_BOUTON_ACTION = By.cssSelector(".inventory_item .btn_inventory");
    //
    private static final By INVENTORY_PRODUCT_SORT = By.className("product_sort_container");
    //
    // burger menu button
    private static final By BURGER_MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By BURGER_DYNAMIC_CATALOG = By.id("dynamic_catalog_sidebar_link");
    private static final By BURGER_DYNAMIC_CATALOG_SPINNER = By.id("dynamic_catalog_spinner_link");
    private static final By BURGER_SPINNER = By.className("dynamic_catalog_spinner");
    private static final By BURGER_PRODUCT_CARDS_CONTAINER = By.className("dynamic_catalog_spinner_container");
    private static final By BURGER_DYNAMIC_CATALOG_CARD = By.className("dynamic_catalog_card");

    private static final By BURGER_DYNAMIC_CATALOG_CARD_NAME = By.className("dynamic_catalog_card_name");
    private static final By BURGER_DYNAMIC_CATALOG_CARD_PRICE = By.className("dynamic_catalog_card_price");
    //
    private static final By BURGER_MENU_ALL_ITEMS = By.id("inventory_sidebar_link");
    private static final By BURGER_MENU_ABOUT = By.id("about_sidebar_link");
    private static final By BURGER_MENU_LOGOUT = By.id("logout_sidebar_link");
    private static final By BURGER_MENU_RESET_APP_STATE = By.id("reset_sidebar_link");
    private static final By BURGER_X_BUTTON = By.id("react-burger-cross-btn");
    private static final By BM_MENU_WRAP = By.className("bm-menu-wrap");

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
    Feature Analysis:
    Loading Behavior: A spinner/loader appears upon initial page load while catalog items are fetched or prepared.
    Loaded State: Spinner disappears completely.
    A catalog grid displays 6 product cards showing image, title, and price (without description or "Add to cart" buttons found on the standard inventory page).
    *  */

    // TC-SPIN-01: Menu Navigation and Spinner Lifecycle (Primary Happy Path)
    @Tag("swaglabs")
    @Tag("spinner")
    @Tag("smoke")
    @Test
    public void testMenuNavigationAndSpinnerLifecyclePrimaryHappyPath() throws InterruptedException {
        // Click the burger menu icon (#react-burger-menu-btn).
        driver.findElement(BURGER_MENU_BUTTON).click();
        // Click Dynamic Catalog to expand the sub-menu (if not expanded).
        driver.findElement(BURGER_DYNAMIC_CATALOG).click();
        // Click Spinner.
        driver.findElement(BURGER_DYNAMIC_CATALOG_SPINNER).click();
        // Assert URL transitions to /dynamic-catalog-spinner.html.
        boolean isDynamicCatalogSpinnerPage = swagLabsActionPage.isUrlOK(BASE_URL, "/dynamic-catalog-spinner.html");
        Assertions.assertTrue(isDynamicCatalogSpinnerPage);
        // Assert the loading spinner element is present immediately upon navigation.
        boolean spinnerIsPresent = swagLabsActionPage.isElementDisplayed(BURGER_SPINNER);
        Assertions.assertTrue(spinnerIsPresent);
        // Wait until the spinner is not present (timeout: 3s).
        Thread.sleep(3000);
        // Assert product cards container is visible.
        boolean productCardsContainerIsVisible = driver.findElement(BURGER_PRODUCT_CARDS_CONTAINER).isEnabled();
        Assertions.assertTrue(productCardsContainerIsVisible);
        // Assert at least one product card (e.g., "Sauce Labs Bike Light") is present.
        boolean oneProductCardName = getDynamicCatalogProducts().get(0).findElement(BURGER_DYNAMIC_CATALOG_CARD_NAME).getText().equals("Sauce Labs Bike Light");
        boolean oneProductCardPrice = getDynamicCatalogProducts().get(0).findElement(BURGER_DYNAMIC_CATALOG_CARD_PRICE).getText().equals("$9.99");
        Assertions.assertTrue(oneProductCardName && oneProductCardPrice);
    }

    // TC-SPIN-02: Direct URL Access
    @Test
    public void testDirectURLAccess() {
        // Directly visit /dynamic-catalog-spinner.html.
        driver.get(BASE_URL + "/dynamic-catalog-spinner.html");
        // Assert spinner appears, then wait until it disappears.
        boolean spinnerIsPresent = swagLabsActionPage.isElementDisplayed(BURGER_SPINNER);
        Assertions.assertTrue(spinnerIsPresent);
        // Create a table of 2 rows
        String[][] catalogProducts = {
                {"Sauce Labs Bike Light", "$9.99"},
                {"Sauce Labs Bolt T-Shirt", "$15.99"},
                {"Sauce Labs Onesie", "$7.99"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99"},
                {"Sauce Labs Backpack", "$29.99"},
                {"Sauce Labs Fleece Jacket", "$49.99"}
        };
        for (int i = 0; i < catalogProducts.length; i++) {
            String actualCatalogProdName = getDynamicCatalogProducts().get(i).findElement(BURGER_DYNAMIC_CATALOG_CARD_NAME).getText();
            String actualCatalogProdPrice = getDynamicCatalogProducts().get(i).findElement(BURGER_DYNAMIC_CATALOG_CARD_PRICE).getText();

            String expCatalogProdName = catalogProducts[i][0];
            String expCatalogProdPrice = catalogProducts[i][1];

            // Assert catalog products load successfully.
            Assertions.assertEquals(actualCatalogProdName, expCatalogProdName);
            Assertions.assertEquals(actualCatalogProdPrice, expCatalogProdPrice);
        }
    }

    // TC-SPIN-03: Data and Display Integrity Post-Loading
    @Test
    public void testDataAndDisplayIntegrityPostLoading() {
        // Click the burger menu icon (#react-burger-menu-btn).
        driver.findElement(BURGER_MENU_BUTTON).click();
        // Click Dynamic Catalog to expand the sub-menu (if not expanded).
        driver.findElement(BURGER_DYNAMIC_CATALOG).click();
        // Click Spinner.
        driver.findElement(BURGER_DYNAMIC_CATALOG_SPINNER).click();
        // Create a table of 2 rows
        String[][] catalogProducts = {
                {"Sauce Labs Bike Light", "$9.99"},
                {"Sauce Labs Bolt T-Shirt", "$15.99"},
                {"Sauce Labs Onesie", "$7.99"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99"},
                {"Sauce Labs Backpack", "$29.99"},
                {"Sauce Labs Fleece Jacket", "$49.99"}
        };
        for (int i = 0; i < catalogProducts.length; i++) {
            String actualCatalogProdName = getDynamicCatalogProducts().get(i).findElement(BURGER_DYNAMIC_CATALOG_CARD_NAME).getText();
            String actualCatalogProdPrice = getDynamicCatalogProducts().get(i).findElement(BURGER_DYNAMIC_CATALOG_CARD_PRICE).getText();

            String expCatalogProdName = catalogProducts[i][0];
            String expCatalogProdPrice = catalogProducts[i][1];

            // Assert catalog products load successfully.
            Assertions.assertEquals(actualCatalogProdName, expCatalogProdName);
            Assertions.assertEquals(actualCatalogProdPrice, expCatalogProdPrice);
        }
    }

    // TC-SPIN-04: Page Refresh & Reload Stability
    @Test
    public void testPageRefreshAndReloadStability() throws InterruptedException {
        // Click the burger menu icon (#react-burger-menu-btn).
        driver.findElement(BURGER_MENU_BUTTON).click();
        // Click Dynamic Catalog to expand the sub-menu (if not expanded).
        driver.findElement(BURGER_DYNAMIC_CATALOG).click();
        // Click Spinner.
        driver.findElement(BURGER_DYNAMIC_CATALOG_SPINNER).click();
        Thread.sleep(2000);
        // Reload page
        swagLabsActionPage.reloadPage();
        // Verify all 6 products re-render correctly.
        // Create a table of 2 rows
        String[][] catalogProducts = {
                {"Sauce Labs Bike Light", "$9.99"},
                {"Sauce Labs Bolt T-Shirt", "$15.99"},
                {"Sauce Labs Onesie", "$7.99"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99"},
                {"Sauce Labs Backpack", "$29.99"},
                {"Sauce Labs Fleece Jacket", "$49.99"}
        };
        for (int i = 0; i < catalogProducts.length; i++) {
            String actualCatalogProdName = getDynamicCatalogProducts().get(i).findElement(BURGER_DYNAMIC_CATALOG_CARD_NAME).getText();
            String actualCatalogProdPrice = getDynamicCatalogProducts().get(i).findElement(BURGER_DYNAMIC_CATALOG_CARD_PRICE).getText();

            String expCatalogProdName = catalogProducts[i][0];
            String expCatalogProdPrice = catalogProducts[i][1];

            // Assert catalog products load successfully.
            Assertions.assertEquals(actualCatalogProdName, expCatalogProdName);
            Assertions.assertEquals(actualCatalogProdPrice, expCatalogProdPrice);
        }
    }

    // TC-SPIN-05: Navigation Back to Standard Inventory
    @Test
    public void testNavigationBackToStandardInventory() throws InterruptedException {
        // Click the burger menu icon (#react-burger-menu-btn).
        driver.findElement(BURGER_MENU_BUTTON).click();
        // Click Dynamic Catalog to expand the sub-menu (if not expanded).
        driver.findElement(BURGER_DYNAMIC_CATALOG).click();
        // Click Spinner.
        driver.findElement(BURGER_DYNAMIC_CATALOG_SPINNER).click();
        // Wait until the spinner is not present (timeout: 3s).
        Thread.sleep(500);
        // From /dynamic-catalog-spinner.html, open sidebar menu.
        driver.findElement(BURGER_MENU_BUTTON).click();
        // Click All Items.
        driver.findElement(BURGER_MENU_ALL_ITEMS).click();
        // Verify URL changes to /inventory.html.
        boolean isInventoryPage = swagLabsActionPage.isUrlOK(BASE_URL, "/inventory.html");
        Assertions.assertTrue(isInventoryPage);
        // Verify standard inventory page features (e.g., sort dropdown .product_sort_container and "Add to cart" buttons) are restored.
        boolean isProductSort = swagLabsActionPage.isElementDisplayed(INVENTORY_PRODUCT_SORT);
        boolean isAddToCartButtons = swagLabsActionPage.isElementDisplayed(INVENTORY_BOUTON_ACTION);
        Assertions.assertTrue(isProductSort && isAddToCartButtons);
    }

    // TC-SPIN-07: Performance & Timeout SLA (Resilience)
    @Test
    public void testPerformanceTimeoutSLAResilience() throws InterruptedException {
        // Spinner must disappear within maximum 0.5 seconds.
        // Click the burger menu icon (#react-burger-menu-btn).
        driver.findElement(BURGER_MENU_BUTTON).click();
        // Click Dynamic Catalog to expand the sub-menu (if not expanded).
        driver.findElement(BURGER_DYNAMIC_CATALOG).click();
        // Click Spinner.
        driver.findElement(BURGER_DYNAMIC_CATALOG_SPINNER).click();
        // Wait until the spinner is not present (timeout: 10s).
        //If spinner exceeds 10 seconds, fail the step with an informative timeout error.
        boolean spinnerIsNotPresent = swagLabsActionPage.isElementNotVisible(BURGER_SPINNER);
        Assertions.assertTrue(spinnerIsNotPresent);
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

    private List<WebElement> getDynamicCatalogProducts() {
        return swagLabsActionPage.getList(BURGER_DYNAMIC_CATALOG_CARD);
    }
}
