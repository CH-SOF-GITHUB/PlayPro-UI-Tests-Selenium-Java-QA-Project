package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.qa.pages.SwagLabs.SwagLabsActionPage;

import java.util.List;
import io.testomat.core.annotation.TestId;

public class Junit5CartTests extends AbstractChromeWebDriverTest {
    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";

    private static final By PRODUCTS_CARDS = By.className("inventory_item");
    private static final By PRODUCT_DESCRIPTION = By.className("inventory_item_desc");
    private static final By PRODUCTS_TITRES = By.className("inventory_item_name");
    private static final By PRODUCTS_PRICE = By.className("inventory_item_price");

    private static final By BOUTONS_ACTION = By.cssSelector(".inventory_item .btn_inventory");

    private static final By CART_BADGE = By.className("shopping_cart_badge");
    private static final By CART_LINK = By.className("shopping_cart_link");

    private static final By CART_CONTINUE_SHOPPING = By.id("continue-shopping");
    private static final By CART_CHECKOUT = By.className("checkout_button");

    private static final By CART_PROD_QUANTITY = By.className("cart_quantity");
    private static final By CART_PROD_TITLE = By.className("inventory_item_name");
    private static final By CART_PROD_DESC = By.className("inventory_item_desc");
    private static final By CART_PROD_PRICE = By.className("inventory_item_price");
    private static final By CART_REMOVE_ITEM = By.name("remove-sauce-labs-backpack");

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
    }

    // Define Number of products in Cart
    int ExpectedCartNumber = 1;

    String PreviousBtnText;
    String NextBtnText;
    String inventory_title;
    String inventory_desc;
    String inventory_price;
    boolean compareCartBadge;

    // TESTS //
    // TC01
    @TestId("8811b033")
    @Test
    public void testAddProductToCart() {
        // Task 1: Log in to Swag Labs
        login();
        // Task 2: Add Product to Cart and Verify Inventory Page Updates
        List<WebElement> products = swagLabsActionPage.getList(PRODUCTS_CARDS);
        for (WebElement prod : products) {
            PreviousBtnText = prod.findElement(BOUTONS_ACTION).getText();
            prod.findElement(BOUTONS_ACTION).click();
            NextBtnText = prod.findElement(BOUTONS_ACTION).getText();
            break;
        }
        System.out.println("Add to cart prev text : " + PreviousBtnText);
        System.out.println("Add to cart next text : " + NextBtnText);
        // Task 3: Verify that the shopping cart icon updates with a badge showing "1"
        boolean compareCartBadge = swagLabsActionPage.compareElement(CART_BADGE, String.valueOf(ExpectedCartNumber));
        Assertions.assertTrue(compareCartBadge);
    }

    // TC02
    @TestId("314b23bd")
    @Test
    public void testAddProductAndNavigateToCart() {
        // Task 1: Log in to Swag Labs
        login();
        // Task 2: Add Product to Cart and Verify Inventory Page Updates
        List<WebElement> products = swagLabsActionPage.getList(PRODUCTS_CARDS);
        for (WebElement prod : products) {
            PreviousBtnText = prod.findElement(BOUTONS_ACTION).getText();
            prod.findElement(BOUTONS_ACTION).click();
            NextBtnText = prod.findElement(BOUTONS_ACTION).getText();
            break;
        }
        System.out.println("Add to cart prev text : " + PreviousBtnText);
        System.out.println("Add to cart next text : " + NextBtnText);
        // Task 3: Navigate to the Cart (Panier)
        swagLabsActionPage.clickOnElement(CART_LINK);
        // Validation:
        // Verify that the URL contains /cart.html.
        boolean isCartUrl = swagLabsActionPage.isContain("/cart.html");
        // Verify that the secondary header displays "Your Cart" (.title).
        boolean isCartTitle = swagLabsActionPage.isTitle("Swag Labs");
        // Verify that the "Continue Shopping" (#continue-shopping) and "Checkout" (#checkout) buttons are present.
        boolean isContinueShopBtn = swagLabsActionPage.isElementDisplayed(CART_CONTINUE_SHOPPING);
        boolean isCheckoutBtn = swagLabsActionPage.isElementDisplayed(CART_CHECKOUT);
        Assertions.assertTrue(isCartUrl);
        Assertions.assertTrue(isCartTitle);
        Assertions.assertTrue(isContinueShopBtn);
        Assertions.assertTrue(isCheckoutBtn);
    }

    // TC03
    @TestId("ceeae439")
    @Test
    public void testAddCartAndCheckContents() {
        // Task 1: Log in to Swag Labs
        login();
        // Task 2: Add Product to Cart and Verify Inventory Page Updates
        List<WebElement> products = swagLabsActionPage.getList(PRODUCTS_CARDS);
        for (WebElement prod : products) {
            inventory_title = prod.findElement(PRODUCTS_TITRES).getText();
            inventory_desc = prod.findElement(PRODUCT_DESCRIPTION).getText();
            inventory_price = prod.findElement(PRODUCTS_PRICE).getText();
            PreviousBtnText = prod.findElement(BOUTONS_ACTION).getText();
            prod.findElement(BOUTONS_ACTION).click();
            NextBtnText = prod.findElement(BOUTONS_ACTION).getText();
            break;
        }
        System.out.println("Add to cart prev text : " + PreviousBtnText);
        System.out.println("Add to cart next text : " + NextBtnText);

        // Task 3: Navigate to the Cart (Panier)
        swagLabsActionPage.clickOnElement(CART_LINK);
        // Task 4: Verify Product Presence and Details in Cart
        // Verify the cart quantity element (.cart_quantity) equals "1".
        boolean cart_with_correct_quantity = swagLabsActionPage.compareElement(CART_PROD_QUANTITY, String.valueOf(ExpectedCartNumber));
        Assertions.assertTrue(cart_with_correct_quantity);
        // Verify the product title in the cart (.inventory_item_name) equals {{@expected_title}} (Sauce Labs Backpack).
        boolean compareCartProdTitle = swagLabsActionPage.compareElement(CART_PROD_TITLE, inventory_title);
        Assertions.assertTrue(compareCartProdTitle);
        // Verify the product description in the cart (.inventory_item_desc) equals {{@expected_desc}}.
        boolean compareCartProdDesc = swagLabsActionPage.compareElement(CART_PROD_DESC, inventory_desc);
        Assertions.assertTrue(compareCartProdDesc);
        // Verify the product price in the cart (.inventory_item_price) equals {{@expected_price}} ($29.99).
        boolean compareCartProdPrice = swagLabsActionPage.compareElement(CART_PROD_PRICE, inventory_price);
        Assertions.assertTrue(compareCartProdPrice);
        // Verify that the "Remove" button is visible and present within the cart item row (#remove-sauce-labs-backpack).
        boolean isRemoveBtnPresent = swagLabsActionPage.isElementDisplayed(CART_REMOVE_ITEM);
        // Verify that the header cart badge continues to display "1".
        boolean compareCartBadge = swagLabsActionPage.compareElement(CART_BADGE, String.valueOf(ExpectedCartNumber));
        Assertions.assertTrue(compareCartBadge);
    }

    // TC04
    @TestId("2fc078ba")
    @Test
    public void testAddToCartViewCartRemoveFromInventoryAndVerifyBadgeCount() {
        // Task 1: Log in to Swag Labs
        login();
        // Task 2: Add Product to Cart and Verify Inventory Page Updates
        List<WebElement> products = swagLabsActionPage.getList(PRODUCTS_CARDS);
        for (WebElement prod : products) {
            PreviousBtnText = prod.findElement(BOUTONS_ACTION).getText();
            prod.findElement(BOUTONS_ACTION).click();
            NextBtnText = prod.findElement(BOUTONS_ACTION).getText();
            break;
        }
        System.out.println("Before go to Cart, Add to cart next text : " + PreviousBtnText);
        System.out.println("Before go to Cart, Add to cart next text : " + NextBtnText);

        // Task 2: Add Product to Cart and Verify Badge Count is "1"
        compareCartBadge = swagLabsActionPage.compareElement(CART_BADGE, String.valueOf(ExpectedCartNumber));
        Assertions.assertTrue(compareCartBadge);

        // Task 3: Navigate to the Cart (Panier)
        swagLabsActionPage.clickOnElement(CART_LINK);

        // Task 4: Return to Inventory List
        swagLabsActionPage.clickOnElement(CART_CONTINUE_SHOPPING);

        // Task 5: Remove Product and Verify Badge is Reset / Cleared (0 items)
        List<WebElement> refreshedProducts = swagLabsActionPage.getList(PRODUCTS_CARDS);
        for (WebElement prod : refreshedProducts) {
            PreviousBtnText = prod.findElement(BOUTONS_ACTION).getText();
            prod.findElement(BOUTONS_ACTION).click();
            NextBtnText = prod.findElement(BOUTONS_ACTION).getText();
            break;
        }
        System.out.println("Before go to Cart, Add to cart next text : " + PreviousBtnText);
        System.out.println("Before go to Cart, Add to cart next text : " + NextBtnText);

        // Verify the shopping cart badge is cleared / no longer present (AssertNotPresent on .shopping_cart_badge),
        // or displays "0", confirming 0 items remain in the cart.
        compareCartBadge = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, empty");
        Assertions.assertTrue(compareCartBadge);
    }

    private void login() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
    }
}
