package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.qa.pages.SwagLabs.SwagLabsActionPage;

import java.util.List;

import io.testomat.core.annotation.TestId;

public class Junit5ProductDetails1Tests extends AbstractChromeWebDriverTest {

    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    //
    private static final By PRODUIT_LIST = By.className("inventory_list");
    //
    private static final By _PRODUCT_ITEMS = By.className("inventory_item");
    private static final By INVENTORY_PRODUCT_NAME = By.className("inventory_item_name");
    private static final By INVENTORY_BOUTON_ACTION = By.cssSelector(".inventory_item .btn_inventory");
    //
    private static final By SauceLabsBackpack = By.xpath("//div[normalize-space()='Sauce Labs Backpack']");
    //
    private static final By BACK_TO_PRODUCTS = By.id("back-to-products");
    //
    private static final By PRODUCT_DETAILS_NAME = By.className("inventory_details_name");
    private static final By PRODUCT_DETAILS_DESC = By.className("inventory_details_desc");
    private static final By PRODUCT_DETAILS_PRICE = By.className("inventory_details_price");
    private static final By PRODUCT_DETAILS_IMAGE = By.className("inventory_details_img");
    private static final By PRODUCT_DETAILS_BUTTON = By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory");
    private static final By PRODUCT_DETAILS_ADD_TO_CART_BTN = By.id("add-to-cart");
    private static final By PRODUCT_DETAILS_REMOVE_BTN = By.id("remove");
    //
    private static final By CART_BADGE = By.className("shopping_cart_badge");
    private static final By CART_LINK = By.className("shopping_cart_link");
    //
    private static final By CART_ACTION_BUTTON = By.cssSelector(".btn.btn_secondary.btn_small.cart_button");
    //
    private static final By CART_QUANTITY = By.className("cart_quantity");
    private static final By CART_PRICE = By.className("inventory_item_price");
    //
    private static final By BURGER_MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By BURGER_MENU_ALL_ITEMS = By.id("inventory_sidebar_link");
    private static final By BURGER_MENU_ABOUT = By.id("about_sidebar_link");
    private static final By BURGER_MENU_LOGOUT = By.id("logout_sidebar_link");
    private static final By BURGER_MENU_RESET_APP_STATE = By.id("reset_sidebar_link");
    private static final By BURGER_X_BUTTON = By.id("react-burger-cross-btn");
    private static final By SidebarMenu = By.className("bm-menu-wrap");
    //
    private static final By MESSAGE_ERREUR = By.cssSelector("[data-test='error']");

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    // Store Button Text
    String buttonText = "";

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
        // 1. Log in to Swag Labs
        login();
    }

    // TC-PD-01: Navigate to product details via item title link
    @TestId("ef3b9929")
    @Test
    public void testNavigateToProductDetailsViaItemTitleLink() {
        // 1. Click on the product title link (e.g., "Sauce Labs Backpack").
        driver.findElement(SauceLabsBackpack).click();
        // 2. Verify URL updates to /inventory-item.html?id=4.
        boolean isProductDetailsPage = swagLabsActionPage.isContain("/inventory-item.html?id=4");
        // 3. Verify the product details view loads with matching title "Sauce Labs Backpack".
        String expectedTitle = "Sauce Labs Backpack";
        String expectedDesc = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
        String expectedPrice = "$29.99";

        Assertions.assertTrue(isProductDetailsPage, "Product details page URL is incorrect.");
        Assertions.assertTrue(swagLabsActionPage.compareElement(PRODUCT_DETAILS_NAME, expectedTitle), "Product title does not match.");
        Assertions.assertTrue(swagLabsActionPage.compareElement(PRODUCT_DETAILS_DESC, expectedDesc), "Product description does not match.");
        Assertions.assertTrue(swagLabsActionPage.compareElement(PRODUCT_DETAILS_PRICE, expectedPrice), "Product price does not match.");
    }

    // TC-PD-02: Navigate to product details via item image
    @TestId("7ba713d6")
    @Test
    public void testNavigateToProductDetailsViaItemImage() {
        // 1. Click on the image thumbnail for "Sauce Labs Bike Light".
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 1) {
                getProducts().get(i).findElement(INVENTORY_PRODUCT_NAME).click();
            }
        }
        // 2. Verify URL updates to /inventory-item.html?id=0.
        boolean isProductDetailsPage = swagLabsActionPage.isContain("/inventory-item.html?id=0");
        Assertions.assertTrue(isProductDetailsPage, "Product ID 0 details page URL is incorrect.");
        // 3. Verify the product details view displays "Sauce Labs Bike Light".
        String expectedTitle = "Sauce Labs Bike Light";
        String expectedDesc = "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.";
        String expectedPrice = "$9.99";

        Assertions.assertTrue(swagLabsActionPage.compareElement(PRODUCT_DETAILS_NAME, expectedTitle), "Product title does not match.");
        Assertions.assertTrue(swagLabsActionPage.compareElement(PRODUCT_DETAILS_DESC, expectedDesc), "Product description does not match.");
        Assertions.assertTrue(swagLabsActionPage.compareElement(PRODUCT_DETAILS_PRICE, expectedPrice), "Product price does not match.");
    }

    // TC-PD-03: Return to inventory using "Back to products"
    @TestId("82a013d8")
    @Test
    public void testReturnToInventoryUsingBackToProducts() {
        driver.findElement(SauceLabsBackpack).click();
        boolean isProductDetailsPage = swagLabsActionPage.isContain("/inventory-item.html?id=4");
        Assertions.assertTrue(isProductDetailsPage, "Product details page URL is incorrect.");
        // 1. Click the "Back to products" link.
        driver.findElement(BACK_TO_PRODUCTS).click();
        // 2. Verify browser URL returns to /inventory.html.
        boolean isInventoryPage = swagLabsActionPage.isContain("/inventory.html");
        Assertions.assertTrue(isInventoryPage, "Product Inventory page URL is incorrect.");
        // 3. Verify the product catalog list is visible.
        boolean productCatalogDisplayed = swagLabsActionPage.isElementDisplayed(PRODUIT_LIST);
        Assertions.assertTrue(productCatalogDisplayed, "Product catalog list is not visible.");
    }

    // TC-PD-04: Browser back button navigation
    @TestId("4954ca35")
    @Test
    public void testBrowserBackButtonNavigation() {
        driver.findElement(SauceLabsBackpack).click();
        boolean isProductDetailsPage = swagLabsActionPage.isContain("/inventory-item.html?id=4");
        Assertions.assertTrue(isProductDetailsPage, "Product details page URL is incorrect.");
        // 1. Click browser back button.
        swagLabsActionPage.backPage();
        // 2. Verify user lands on /inventory.html.
        boolean isInventoryPage = swagLabsActionPage.isContain("/inventory.html");
        Assertions.assertTrue(isInventoryPage, "Product Inventory page URL is incorrect.");
        // 3. Click browser forward button.
        swagLabsActionPage.forwardPage();
        // 4. Verify user returns to /inventory-item.html?id=4 with content intact.
        boolean isProductPage = swagLabsActionPage.isContain("/inventory-item.html?id=4");
        Assertions.assertTrue(isProductPage, "Product ID 0 details page URL is incorrect.");
    }

    // TC-PD-05: Product information verification (data consistency)
    @TestId("f449d600")
    @Test
    public void testProductInformationVerificationDataConsistency() {
        // 1. Open product details for "Sauce Labs Backpack" (id=4).
        driver.findElement(SauceLabsBackpack).click();
        boolean isProductDetailsPage = swagLabsActionPage.isContain("/inventory-item.html?id=4");
        Assertions.assertTrue(isProductDetailsPage, "Product details page URL is incorrect.");
        String expectedTitle = "Sauce Labs Backpack";
        String expectedDesc = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
        String expectedPrice = "$29.99";
        // 2. Assert title equals "Sauce Labs Backpack".
        Assertions.assertTrue(swagLabsActionPage.compareElement(PRODUCT_DETAILS_NAME, expectedTitle), "Product title does not match.");
        // 3. Assert description equals "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.".
        Assertions.assertTrue(swagLabsActionPage.compareElement(PRODUCT_DETAILS_DESC, expectedDesc), "Product description does not match.");
        // 4. Assert price equals "$29.99".
        Assertions.assertTrue(swagLabsActionPage.compareElement(PRODUCT_DETAILS_PRICE, expectedPrice), "Product price does not match.");
        // 5. Assert product image is visible and rendered.
        String src = driver.findElement(PRODUCT_DETAILS_IMAGE).getAttribute("src");
        Assertions.assertTrue(src != null && !src.trim().isEmpty());

        boolean isImageOfProductID4 = swagLabsActionPage.compareAttribute(PRODUCT_DETAILS_IMAGE, "alt", "Sauce Labs Backpack");
        Assertions.assertTrue(isImageOfProductID4);
    }

    // TC-PD-06: Currency and price formatting
    @TestId("c849330e")
    @Test
    public void testCurrencyAndPriceFormatting() {
        // 2. Inspect the first two products
        for (int i = 0; i < 2; i++) {
            // Re-fetch product list to avoid stale elements after navigation
            // Safety check
            Assertions.assertTrue(getProducts().size() > i, "Product at index " + i + " was not found.");
            // Open product details
            getProducts().get(i).findElement(INVENTORY_PRODUCT_NAME).click();
            // 3. Get and validate product price
            WebElement prodPrice = driver.findElement(PRODUCT_DETAILS_PRICE);
            String actualPrice = prodPrice.getText().trim();
            System.out.println("Product " + (i + 1) + " price: " + actualPrice);
            Assertions.assertTrue(actualPrice.matches("\\$\\d+\\.\\d{2}"), "Invalid price format for product " + (i + 1) + ": " + actualPrice);
            // 4. Return to inventory
            swagLabsActionPage.backPage();
        }
    }

    // TC-PD-07: Add item to cart from details page
    @TestId("009fddda")
    @Test
    public void testAddItemToCartFromDetailsPage() {
        driver.findElement(SauceLabsBackpack).click();
        boolean isProductDetailsPage = swagLabsActionPage.isContain("/inventory-item.html?id=4");
        Assertions.assertTrue(isProductDetailsPage, "Product details page URL is incorrect.");
        boolean isCartEmpty = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, empty");
        Assertions.assertTrue(isCartEmpty);
        // 1. Verify the action button displays "Add to cart".
        boolean isAddToCartDisplayed = swagLabsActionPage.isElementDisplayed(PRODUCT_DETAILS_ADD_TO_CART_BTN);
        Assertions.assertTrue(isAddToCartDisplayed, "Add to cart button not displayed in details product page");
        // 2. Click "Add to cart".
        driver.findElement(PRODUCT_DETAILS_ADD_TO_CART_BTN).click();
        // 3. Assert the button text changes to "Remove".
        boolean isRemoveDisplayed = swagLabsActionPage.compareElement(PRODUCT_DETAILS_REMOVE_BTN, "Remove");
        Assertions.assertTrue(isRemoveDisplayed, "Button text did not change to 'Remove' after); clicking 'Add to cart'.");
        // 4. Assert the shopping cart icon displays a badge with count 1.
        boolean isCartDisplayOneProduct = swagLabsActionPage.compareElement(CART_LINK, 1);
        Assertions.assertTrue(isCartDisplayOneProduct, "Shopping cart icon does not display a badge with count 1 after adding product to cart.");
    }

    // TC-PD-08: Remove item from cart from details page
    @TestId("d2cabecc")
    @Test
    public void testRemoveItemFromCartFromDetailsPage() {
        getProducts().get(0).findElement(INVENTORY_BOUTON_ACTION).click();
        driver.findElement(CART_LINK).click();
        boolean isCartPage = swagLabsActionPage.isUrlOK(BASE_URL, "/cart.html");
        Assertions.assertTrue(isCartPage, "Cart page URL is incorrect.");
        // 1. Click "Remove".
        driver.findElement(CART_ACTION_BUTTON).click();
        // 2. Assert button text reverts to "Add to cart".
        swagLabsActionPage.backPage();
        boolean isBtnAddToCart = swagLabsActionPage.compareElement(INVENTORY_BOUTON_ACTION, "Add to cart");
        Assertions.assertTrue(isBtnAddToCart, "Button text did not revert to 'Add");
    }

    // TC-PD-09: Cart state persistence when navigating between pages
    @TestId("e9d98e05")
    @Test
    public void testCartStatePersistenceWhenNavigatingBetweenPages() {
        // 1. Click "Add to cart" for "Sauce Labs Backpack" on /inventory.html.
        driver.findElement(INVENTORY_BOUTON_ACTION).click();
        // 2. Verify cart badge displays 1.
        boolean isCartBadgeOne = swagLabsActionPage.compareElement(CART_BADGE, 1);
        Assertions.assertTrue(isCartBadgeOne, "Cart badge does not display 1 after adding 'Sauce Labs Backpack' to cart.");
        // 3. Click on "Sauce Labs Backpack" to open /inventory-item.html?id=4.
        driver.findElement(SauceLabsBackpack).click();
        // 4. Assert button displays "Remove" on the details page.
        boolean isBtnRemoveText = swagLabsActionPage.compareElement(PRODUCT_DETAILS_REMOVE_BTN, "Remove");
        Assertions.assertTrue(isBtnRemoveText, "Button text does not display 'Remove' on");
        // 5. Click "Back to products".
        driver.findElement(BACK_TO_PRODUCTS).click();
        // 6. Assert button remains "Remove" on the inventory page.
        boolean isBtnAddToCartText = swagLabsActionPage.compareElement(getProducts().get(0).findElement(INVENTORY_BOUTON_ACTION), "Remove");
        Assertions.assertTrue(isBtnAddToCartText, "Button text does not display 'Remove' on");
    }

    // TC-PD-10: Proceed to cart from product details page
    @TestId("e99613ad")
    @Test
    public void testProceedToCartFromProductDetailsPage() throws InterruptedException {
        driver.findElement(SauceLabsBackpack).click();
        driver.findElement(PRODUCT_DETAILS_ADD_TO_CART_BTN).click();
        // 1. Click the shopping cart icon in the top header.
        driver.findElement(CART_LINK).click();
        // 2. Verify URL navigates to /cart.html.
        boolean isCartPage = swagLabsActionPage.isUrlOK(BASE_URL, "/cart.html");
        Assertions.assertTrue(isCartPage, "Cart page URL is incorrect.");
        // 3. Verify "Sauce Labs Backpack" is listed in the cart with quantity 1 and price $29.99.
        boolean isCartQuantityOne = swagLabsActionPage.compareElement(CART_QUANTITY, 1);
        Assertions.assertTrue(isCartQuantityOne, "Cart quantity for 'Sauce Labs Backpack' is not 1.");
        boolean isCartPriceCorrect = swagLabsActionPage.compareElement(CART_PRICE, "$29.99");
        Assertions.assertTrue(isCartPriceCorrect, "Cart price for 'Sauce Labs Backpack' is not $29.99.");
    }

    // TC-PD-11: Direct URL access with valid ID
    @TestId("73aff8b1")
    @Test
    public void testDirectURLAccessWithValidID() {
        // 1. Navigate directly to https://www.saucedemo.com/inventory-item.html?id=0.
        driver.get("https://www.saucedemo.com/inventory-item.html?id=0");
        // 2. Assert page loads successfully displaying "Sauce Labs Bike Light".
        boolean isProductNameID0 = swagLabsActionPage.compareElement(PRODUCT_DETAILS_NAME, "Sauce Labs Bike Light");
        Assertions.assertTrue(isProductNameID0, "Product name for ID 0 is not");
    }

    // TC-PD-12: Direct URL access with non-existent or invalid ID
    @TestId("1d7111a1")
    @Test
    public void testDirectURLAccessWithNonExistentOrInvalidID() {
        // 1. Navigate to /inventory-item.html?id=999 or /inventory-item.html?id=invalid.
        driver.get(BASE_URL + "/inventory-item.html?id=999");
        // 2. Observe application response: check if item not found indicator, empty details, or redirection occurs.
        boolean isNameIncorrect = swagLabsActionPage.compareElement(PRODUCT_DETAILS_NAME, "ITEM NOT FOUND");
        Assertions.assertTrue(isNameIncorrect, "Product name should not be displayed for non-existent ID.");
        boolean isDescriptionIncorrect = swagLabsActionPage.compareElement(PRODUCT_DETAILS_DESC, "We're sorry, but your call could not be completed as dialled. Please check your number, and try your call again. If you are in need of assistance, please dial 0 to be connected with an operator. This is a recording. 4 T 1.");
        Assertions.assertTrue(isDescriptionIncorrect, "Product description should not be displayed for non-existent ID.");
    }

    // TC-PD-13: Unauthenticated direct access to product details
    @TestId("1bd56c88")
    @Test
    public void testUnauthenticatedDirectAccessToProductDetails() {
        // 1. Logout from application
        logout();
        // 1. Attempt to navigate directly to https://www.saucedemo.com/inventory-item.html?id=4.
        driver.get("https://www.saucedemo.com/inventory-item.html?id=4");
        // 2. Verify user is redirected to / (login page).
        boolean isLoginPage = swagLabsActionPage.isUrlOK(BASE_URL, "/");
        Assertions.assertTrue(isLoginPage, "User is not redirected to login page when accessing product details without authentication.");
        // 3. Verify error message displays: "Epic sadface: You can only access '/inventory-item.html' when you are logged in.".
        String errorMsg = driver.findElement(MESSAGE_ERREUR).getText();
        Assertions.assertEquals("Epic sadface: You can only access '/inventory-item.html' when you are logged in.", errorMsg);
    }

    // TC-PD-14: Open side menu from product details
    @TestId("4b3ac51c")
    @Test
    public void testOpenSideMenuFromProductDetails() throws InterruptedException {
        // 1. Click the hamburger menu button in the top left.
        driver.findElement(BURGER_MENU_BUTTON).click();
        Thread.sleep(1000);
        // 2. Assert menu drawer opens displaying "All Items", "About", "Logout", and "Reset App State".
        boolean isMenuOpen = swagLabsActionPage.compareAttribute(SidebarMenu, "aria-hidden", "false");
        Assertions.assertTrue(isMenuOpen, "Side menu did not open.");
        boolean isAllItemsDisplayed = swagLabsActionPage.isElementDisplayed(BURGER_MENU_ALL_ITEMS);
        boolean isAboutDisplayed = swagLabsActionPage.isElementDisplayed(BURGER_MENU_ABOUT);
        boolean isLogoutDisplayed = swagLabsActionPage.isElementDisplayed(BURGER_MENU_LOGOUT);
        boolean isResetAppStateDisplayed = swagLabsActionPage.isElementDisplayed(BURGER_MENU_RESET_APP_STATE);
        Assertions.assertTrue(isAllItemsDisplayed && isAboutDisplayed && isLogoutDisplayed && isResetAppStateDisplayed);
        // 3. Click "All Items".
        driver.findElement(BURGER_MENU_ALL_ITEMS).click();
        // 4. Assert navigation directs back to /inventory.html.
        boolean isInventoryPage = swagLabsActionPage.isContain("/inventory.html");
        Assertions.assertTrue(isInventoryPage, "Navigation did not direct back to /inventory.html after clicking 'All Items' in side menu.");
    }

    // TC-PD-15: Reset app state from product details
    @TestId("23ab4f91")
    @Test
    public void testResetAppStateFromProductDetails() throws InterruptedException {
        driver.findElement(SauceLabsBackpack).click();
        driver.findElement(PRODUCT_DETAILS_ADD_TO_CART_BTN).click();
        buttonText = swagLabsActionPage.getElementText(PRODUCT_DETAILS_BUTTON);
        System.out.println("Button text before reset: " + buttonText);
        // 1. Open hamburger menu.
        driver.findElement(BURGER_MENU_BUTTON).click();
        Thread.sleep(1000);
        // 2. Click "Reset App State".
        driver.findElement(BURGER_MENU_RESET_APP_STATE).click();
        // 3. Close menu drawer.
        driver.findElement(BURGER_X_BUTTON).click();
        // 4. Assert cart badge disappears.
        boolean isCartEmpty = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, empty");
        Assertions.assertTrue(isCartEmpty);
        // 5. Assert button reverts to "Add to cart".
        buttonText = swagLabsActionPage.getElementText(PRODUCT_DETAILS_BUTTON);
        System.out.println("Button text after reset: " + buttonText);
        boolean isAddToCartButton = swagLabsActionPage.compareElement(PRODUCT_DETAILS_BUTTON, "Add to cart");
        Assertions.assertTrue(isAddToCartButton, "Button did not revert to 'Add to cart' after resetting app state.");
    }

    // TC-PD-16: Logout from product details
    @TestId("53916228")
    @Test
    public void testLogoutFromProductDetails() throws InterruptedException {
        driver.findElement(SauceLabsBackpack).click();
        Thread.sleep(1000);
        // 1. Open hamburger menu.
        driver.findElement(BURGER_MENU_BUTTON).click();
        // 2. Click "Logout".
        driver.findElement(BURGER_MENU_LOGOUT).click();
        // 3. Verify redirect to login page (/).
        boolean isLoginPage = swagLabsActionPage.isUrlOK(BASE_URL, "/");
        Assertions.assertTrue(isLoginPage, "User is not redirected to login page after logout from");
    }

    private List<WebElement> getProducts() {
        System.out.println("Fetching product list from the inventory page.");
        return swagLabsActionPage.getList(_PRODUCT_ITEMS);
    }

    // sample login method
    private void login() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
    }

    // sample logout method
    private void logout() {
        driver.findElement(BURGER_MENU_BUTTON).click();
        driver.findElement(BURGER_MENU_LOGOUT).click();
    }
}
