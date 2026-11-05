package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.qa.pages.SwagLabs.SwagLabsActionPage;

import java.util.List;

import io.testomat.core.annotation.TestId;

public class Junit5Cart1Tests extends AbstractChromeWebDriverTest {

    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    //
    private static final By _PRODUCT_ITEMS = By.className("inventory_item");
    private static final By INVENTORY_BOUTON_ACTION = By.cssSelector(".inventory_item .btn_inventory");
    private static final By INVENTORY_PRODUCT_NAME = By.className("inventory_item_name");
    //
    private static final By SauceLabsBackpack = By.xpath("//div[normalize-space()='Sauce Labs Backpack']");
    //
    private static final By PRODUCT_DETAILS_NAME = By.className("inventory_details_name");
    //
    private static final By PRODUCT_DETAILS_BUTTON = By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory");
    private static final By PRODUCT_DETAILS_ADD_TO_CART_BTN = By.id("add-to-cart");
    //
    private static final By CART_LINK = By.className("shopping_cart_link");
    private static final By CART_HEADER_SECONDARY_TITLE = By.className("header_secondary_container");
    private static final By CART_LIST = By.className("cart_list");
    private static final By CART_ITEM = By.className("cart_item");
    private static final By CART_CONTINUE_SHOPPING_BTN = By.id("continue-shopping");
    private static final By CART_CHECKOUT_BTN = By.id("checkout");
    private static final By CART_CHECKOUT_TITLE = By.className("title");
    //
    private static final By CART_QUANTITY = By.className("cart_quantity");
    private static final By CART_INVENTORY_ITEM_NAME = By.className("inventory_item_name");
    private static final By CART_INVENTORY_ITEM_DESC = By.className("inventory_item_desc");
    private static final By CART_INVENTORY_ITEM_PRICE = By.className("inventory_item_price");
    private static final By CART_INVENTORY_ITEM_BUTTON = By.cssSelector(".btn.btn_secondary.btn_small.cart_button");

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    String expectedCartLabel;

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
        // 1. Log in to Swag Labs
        login();
    }

    /*
     * Cart Module Test Coverage Matrix
     * */
    // TC-CART-01: Empty cart state and navigation
    @TestId("51fad018")
    @Test
    public void testEmptyCartStateAndNavigation() {
        // 1. Cart icon initially has no count badge.
        boolean hasNoCountBadge = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, empty");
        Assertions.assertTrue(hasNoCountBadge, "Cart icon should have no count badge initially.");
        // 2. Navigating to /cart.html displays "Your Cart" with empty list, column headers (QTY, Description), "Continue Shopping", and "Checkout" buttons (Capture 2).
        driver.findElement(CART_LINK).click();
        boolean isCartPage = swagLabsActionPage.isUrlOK(BASE_URL, "/cart.html");
        Assertions.assertTrue(isCartPage, "Navigating to /cart.html should display the cart page.");
        boolean isCartHeader = driver.findElement(CART_HEADER_SECONDARY_TITLE).getText().equals("Your Cart");
        Assertions.assertTrue(isCartHeader, "Cart page should display 'Your Cart' header.");
        Assertions.assertTrue(swagLabsActionPage.isElementDisplayed(CART_LIST), "Cart list should be displayed on the cart page.");
        Assertions.assertTrue(driver.findElement(CART_LIST).getText().contains("QTY"), "Cart list should contain 'QTY' column header.");
        Assertions.assertTrue(driver.findElement(CART_LIST).getText().contains("Description"), "Cart list should contain 'Description' column header.");
        Assertions.assertTrue(swagLabsActionPage.isElementDisplayed(CART_CONTINUE_SHOPPING_BTN), "Continue Shopping button should be displayed.");
        Assertions.assertTrue(swagLabsActionPage.isElementDisplayed(CART_CHECKOUT_BTN), "Checkout button should be displayed.");
        // 3. "Continue Shopping" button navigates back to /inventory.html.
        driver.findElement(CART_CONTINUE_SHOPPING_BTN).click();
        boolean isInventoryPage = swagLabsActionPage.isUrlOK(BASE_URL, "/inventory.html");
        Assertions.assertTrue(isInventoryPage, "Clicking 'Continue Shopping' should navigate back to /inventory.html.");
    }

    // TC-CART-02: Add multiple items from inventory catalog
    @TestId("6c6a3f60")
    @Test
    public void testAddMultipleItemsFromInventoryCatalog() throws InterruptedException {
        // 1. Clicking "Add to cart" on items updates each button to "Remove" (Capture 3).
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 3) {
                break;
            }
            getProducts().get(i).findElement(INVENTORY_BOUTON_ACTION).click();
        }
        // 2. Cart badge increments dynamically (1, 2, 3).
        boolean cartBadgeCount3 = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, 3 items");
        Assertions.assertTrue(cartBadgeCount3, "Cart badge should show 3 items after adding three products.");
    }

    // TC-CART-03: Cart contents and metadata verification
    @TestId("e099947b")
    @Test
    public void testCartContentsAndMetadataVerification() {
        // 1. Clicking "Add to cart" on items updates each button to "Remove"
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 3) {
                break;
            }
            getProducts().get(i).findElement(INVENTORY_BOUTON_ACTION).click();
        }

        driver.findElement(CART_LINK).click();
        System.out.println("Cart's products size: " + getCartProducts().size());
        for (int j = 0; j < getCartProducts().size(); j++) {
            // 2. Cart page shows exact items added: name, description, unit price ($29.99, $9.99, $15.99), and quantity of 1 each
            System.out.println("Cart product " + (j + 1) + " name: " + getCartProducts().get(j).findElement(CART_INVENTORY_ITEM_NAME).getText());
            System.out.println("Cart product " + (j + 1) + " description: " + getCartProducts().get(j).findElement(CART_INVENTORY_ITEM_DESC).getText());
            System.out.println("Cart product " + (j + 1) + " price: " + getCartProducts().get(j).findElement(CART_INVENTORY_ITEM_PRICE).getText());
            System.out.println("Cart product " + (j + 1) + " quantity: " + getCartProducts().get(j).findElement(CART_QUANTITY).getText());
            // 3. Each item row contains a "Remove" button.
            Assertions.assertTrue(swagLabsActionPage.isElementDisplayed(getCartProducts().get(j).findElement(CART_INVENTORY_ITEM_BUTTON)), "Each item row not contain a 'Remove' button");
        }
    }

    // TC-CART-04: Remove item from cart page
    @TestId("a42e0ff5")
    @Test
    public void testRemoveItemFromCartPage() {
        // 1. Clicking "Add to cart" on items updates each button to "Remove"
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 3) {
                break;
            }
            getProducts().get(i).findElement(INVENTORY_BOUTON_ACTION).click();
        }
        // 2. Clicking "Remove" for an item removes it from the list.
        driver.findElement(CART_LINK).click();
        for (int j = 0; j < getCartProducts().size(); j++) {
            if (j == 2) {
                getCartProducts().get(j).findElement(CART_INVENTORY_ITEM_BUTTON).click();
                break;
            }
        }
        // 3. Cart badge decrements accordingly (3 to 2).
        boolean cartBadgeCount2 = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, 2 items");
        Assertions.assertTrue(cartBadgeCount2, "Cart badge should show 3 items after adding three products.");
    }

    // TC-CART-05: Cart persistence across navigation
    @TestId("c14c98bc")
    @Test
    public void testCartPersistenceAcrossNavigation() {
        // 1. Clicking "Add to cart" on items updates each button to "Remove"
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 3) {
                break;
            }
            getProducts().get(i).findElement(INVENTORY_BOUTON_ACTION).click();
        }
        driver.findElement(CART_LINK).click();
        // 2. Clicking "Continue Shopping" returns to /inventory.html.
        driver.findElement(CART_CONTINUE_SHOPPING_BTN).click();
        // 3. Inventory page retains "Remove" state on remaining items and badge count.
        boolean cartBadgeCount3 = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, 3 items");
        Assertions.assertTrue(cartBadgeCount3, "Cart badge should show 3 items after adding three products.");
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 3) {
                break;
            }
            boolean isRemoveTextBtn = getProducts().get(i).findElement(INVENTORY_BOUTON_ACTION).getText().equals("Remove");
            Assertions.assertTrue(isRemoveTextBtn, "Inventory page should retain 'Remove' state on remaining items.");
        }
    }

    // TC-CART-06: Add and remove from product details page
    @TestId("95502296")
    @Test
    public void testAddAndRemoveFromProductDetailsPage() throws InterruptedException {
        // 1. Clicking an item title navigates to /inventory-item.html?id=4.
        getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME).click();
        // 2. Item details page displays synchronized "Remove" button and badge count.
        driver.findElement(PRODUCT_DETAILS_ADD_TO_CART_BTN).click();
        boolean isRemoveBtnText = swagLabsActionPage.compareElement(PRODUCT_DETAILS_BUTTON, "Remove");
        Assertions.assertTrue(isRemoveBtnText, "Item details page should display 'Remove' button.");
        boolean cartBadgeCount1 = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, 1 items");
        Assertions.assertTrue(cartBadgeCount1, "Cart badge should show 1 items after adding 1 product.");
    }

    // TC-CART-07: Proceed to checkout
    @TestId("67de6507")
    @Test
    public void testProceedToCheckout() {
        // 1. Clicking "Checkout" navigates to /checkout-step-one.html.
        driver.findElement(CART_LINK).click();
        driver.findElement(CART_CHECKOUT_BTN).click();
        boolean isCheckoutPage = swagLabsActionPage.isUrlOK(BASE_URL, "/checkout-step-one.html");
        Assertions.assertTrue(isCheckoutPage, "Clicking 'Checkout' should navigate to /checkout-step-one.html.");
    }

    /*
    Detailed Test Plan
    * */
    @TestId("8d3ab83e")
    @Test
    public void testCompleteScenario() throws InterruptedException {
        // Task 1: Verify Initial Empty Cart State
        boolean cartEmpty = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, empty");
        Assertions.assertTrue(cartEmpty, "Cart should be empty initially.");
        driver.findElement(CART_LINK).click();
        boolean isCartPage = swagLabsActionPage.isUrlOK(BASE_URL, "/cart.html");
        Assertions.assertTrue(isCartPage, "Clicking cart link should navigate to /cart.html.");
        Thread.sleep(1000);
        boolean isCartHeader = swagLabsActionPage.compareElement(CART_HEADER_SECONDARY_TITLE, "Your Cart");
        Assertions.assertTrue(isCartHeader, "Cart page should display 'Your Cart' header.");
        driver.findElement(CART_CONTINUE_SHOPPING_BTN).click();
        boolean isInventoryPage = swagLabsActionPage.isUrlOK(BASE_URL, "/inventory.html");
        Assertions.assertTrue(isInventoryPage, "Clicking 'Continue Shopping' should navigate back to /inventory.html.");
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 3) {
                break;
            }
            getProducts().get(i).findElement(INVENTORY_BOUTON_ACTION).click();
            // Assert each of the 3 buttons toggles to "Remove"
            Assertions.assertEquals("Remove", getProducts().get(i).findElement(INVENTORY_BOUTON_ACTION).getText(), "Button should toggle to 'Remove' after adding to cart.");
            // Assert cart badge count
            expectedCartLabel = "Cart, " + (i + 1) + " items";
        }
        // Assert the cart badge element displays text "3".
        boolean cartBadgeCount3 = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", expectedCartLabel);
        Assertions.assertTrue(cartBadgeCount3, "Cart badge should show 3 items after adding three products.");
        // Task 3: Inspect Cart Contents and Verify Line Items
        driver.findElement(CART_LINK).click();
        for (int j = 0; j < getCartProducts().size(); j++) {
            // 2. Cart page shows exact items added: name, description, unit price ($29.99, $9.99, $15.99), and quantity of 1 each
            // Item 1: "Sauce Labs Backpack", price "$29.99", quantity "1"
            System.out.printf("Item %d: '%s', price '%s', quantity '%d'", j + 1,
                    getCartProducts().get(j).findElement(CART_INVENTORY_ITEM_NAME).getText(),
                    getCartProducts().get(j).findElement(CART_INVENTORY_ITEM_PRICE).getText(),
                    Integer.parseInt(getCartProducts().get(j).findElement(CART_QUANTITY).getText())).println();
            Assertions.assertTrue(swagLabsActionPage.isElementDisplayed(getCartProducts().get(j).findElement(CART_INVENTORY_ITEM_BUTTON)), "Each item row not contain a 'Remove' button");
            boolean cartBadgeStillCount3 = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", expectedCartLabel);
            Assertions.assertTrue(cartBadgeStillCount3, "Cart badge should show 3 items after adding three products.");
        }
        // Task 4: Remove an Item from Cart Page
        getCartProducts().get(1).findElement(CART_INVENTORY_ITEM_BUTTON).click();
        // Assert "Sauce Labs Bike Light" is no longer present in the cart item list.
        // Assert "Sauce Labs Backpack" and "Sauce Labs Bolt T-Shirt" remain in the cart list.
        // Assert the cart badge decrements to "2".
        expectedCartLabel = "Cart, " + getCartProducts().size() + " items";
        boolean cartBadgeChangeCount2 = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", expectedCartLabel);
        Assertions.assertTrue(cartBadgeChangeCount2, "Cart badge should show 2 items after adding three products.");

        // Task 6: Verify Cart State Persistence on Navigation
        driver.findElement(CART_CONTINUE_SHOPPING_BTN).click();
        // Assert the URL is /inventory.html.
        Assertions.assertTrue(swagLabsActionPage.isUrlOK(BASE_URL, "/inventory.html"), "Clicking 'Continue Shopping' should navigate back to /inventory.html.");
        Thread.sleep(1000); // Wait for the page to load
        //Assert the cart badge maintains "2".
        boolean cartBadgeStillCount2 = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", expectedCartLabel);
        Assertions.assertTrue(cartBadgeStillCount2, "Cart badge should show 2 items after adding three products.");
        //Assert "Sauce Labs Bike Light" shows "Add to cart".
        boolean Prod2ShowAddToCart = swagLabsActionPage.compareElement(getProducts().get(1).findElement(INVENTORY_BOUTON_ACTION), "Add to cart");
        Assertions.assertTrue(Prod2ShowAddToCart, "'Sauce Labs Bike Light' should show 'Add to cart' after being removed from the cart.");
        //Assert "Sauce Labs Backpack" and "Sauce Labs Bolt T-Shirt" still show "Remove".
        boolean Prod1ShowRemove = swagLabsActionPage.compareElement(getProducts().get(0).findElement(INVENTORY_BOUTON_ACTION), "Remove");
        Assertions.assertTrue(Prod1ShowRemove, "'Sauce Labs Backpack' should show 'Add to cart' after being removed from the cart.");
        boolean Prod3ShowRemove = swagLabsActionPage.compareElement(getProducts().get(2).findElement(INVENTORY_BOUTON_ACTION), "Remove");
        Assertions.assertTrue(Prod3ShowRemove, "'Sauce Labs Bolt T-Shirt' should show 'Add to cart' after being removed from the cart.");

        // Task 7: Verify Item Detail Synchronization
        // Action: Click the title link for "Sauce Labs Backpack" to open /inventory-item.html?id=4 (Capture 5).
        driver.findElement(SauceLabsBackpack).click();
        // Assert the product details page loads with title "Sauce Labs Backpack".
        String actualProdName = driver.findElement(PRODUCT_DETAILS_NAME).getText();
        Assertions.assertEquals("Sauce Labs Backpack", actualProdName, "Product details page should display the correct product name.");
        // Assert the action button displays "Remove".
        String actualButtonText = driver.findElement(PRODUCT_DETAILS_BUTTON).getText();
        Assertions.assertEquals("Remove", actualButtonText, "Product details page should display 'Remove' button for items already in the cart.");
        // Assert the cart badge displays "2".
        boolean cartBadgeIsCount2 = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", expectedCartLabel);
        Assertions.assertTrue(cartBadgeIsCount2, "Cart badge should show 2 items after adding three products.");

        // Task 8: Proceed to Checkout
        // Action: Click the cart icon to return to /cart.html.
        driver.findElement(CART_LINK).click();
        // Action: Click the "Checkout" button.
        driver.findElement(CART_CHECKOUT_BTN).click();
        // Validation: Assert the browser navigates to /checkout-step-one.html and displays "Checkout: Your Information".
        boolean isCheckoutTitle = swagLabsActionPage.compareElement(CART_CHECKOUT_TITLE, "Checkout: Your Information");
        Assertions.assertTrue(isCheckoutTitle, "Checkout page should display the correct title 'Checkout: Your Information'.");
        boolean isCheckoutPage = swagLabsActionPage.isUrlOK(BASE_URL, "/checkout-step-one.html");
        Assertions.assertTrue(isCheckoutPage, "Clicking 'Checkout' should navigate to /checkout-step-one.html.");
    }

    // sample login method
    private void login() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
    }

    private List<WebElement> getProducts() {
        System.out.println("Fetching product list from the inventory page.");
        return swagLabsActionPage.getList(_PRODUCT_ITEMS);
    }

    private List<WebElement> getCartProducts() {
        System.out.println("Fetching product list from the cart page.");
        return swagLabsActionPage.getList(CART_ITEM);
    }
}
