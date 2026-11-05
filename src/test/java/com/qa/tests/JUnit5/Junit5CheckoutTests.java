package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.qa.pages.SwagLabs.SwagLabsActionPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


public class Junit5CheckoutTests extends AbstractChromeWebDriverTest {

    // define financial calculations variables
    BigDecimal itemTotal = BigDecimal.ZERO;
    BigDecimal tax;
    BigDecimal total;

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
    private static final By INVENTORY_BOUTON_ACTION = By.cssSelector(".inventory_item .btn_inventory");
    //
    private static final By SauceLabsBackpack = By.xpath("//div[normalize-space()='Sauce Labs Backpack']");
    //
    private static final By CART_LINK = By.className("shopping_cart_link");
    private static final By CART_HEADER_SECONDARY_TITLE = By.className("header_secondary_container");
    private static final By CART_LIST = By.className("cart_list");
    private static final By CART_ITEM = By.className("cart_item");
    private static final By CART_CONTINUE_SHOPPING_BTN = By.id("continue-shopping");
    private static final By CART_CHECKOUT_BTN = By.id("checkout");
    //
    private static final By CART_QUANTITY = By.className("cart_quantity");
    private static final By CART_INVENTORY_ITEM_NAME = By.className("inventory_item_name");
    private static final By CART_INVENTORY_ITEM_DESC = By.className("inventory_item_desc");
    private static final By CART_INVENTORY_ITEM_PRICE = By.className("inventory_item_price");
    private static final By CART_INVENTORY_ITEM_BUTTON = By.cssSelector(".btn.btn_secondary.btn_small.cart_button");
    //
    private static final By CART_CHECKOUT_TITLE = By.className("title");
    private static final By CART_CHECKOUT_FIRST_NAME = By.id("first-name");
    private static final By CART_CHECKOUT_LAST_NAME = By.id("last-name");
    private static final By CART_CHECKOUT_POSTAL_CODE = By.id("postal-code");
    private static final By CART_CHECKOUT_CONTINUE_BTN = By.id("continue");
    private static final By CART_CHECKOUT_CANCEL_BTN = By.id("cancel");
    private static final By CART_CHECKOUT_ERROR_MESSAGE = By.cssSelector("h3[data-test='error']");
    //
    private static final By CART_CHECKOUT_OVERVIEW_ITEM = By.className("cart_item");
    private static final By CART_CHECKOUT_OVERVIEW_TITLE = By.className("title");
    private static final By CART_CHECKOUT_OVERVIEW_ITEM_QUANTITY = By.className("cart_quantity");
    private static final By CART_CHECKOUT_OVERVIEW_ITEM_NAME = By.className("inventory_item_name");
    private static final By CART_CHECKOUT_OVERVIEW_ITEM_DESC = By.className("inventory_item_desc");
    private static final By CART_CHECKOUT_OVERVIEW_ITEM_PRICE = By.className("inventory_item_price");
    private static final By CART_CHECKOUT_OVERVIEW_ITEM_BUTTON = By.cssSelector(".btn.btn_secondary.btn_small.cart_button");
    //
    private static final By CART_CHECKOUT_OVERVIEW_SUMMARY_SUBTOTAL_LABEL = By.className("summary_subtotal_label");
    private static final By CART_CHECKOUT_OVERVIEW_SUMMARY_TAX_LABEL = By.className("summary_tax_label");
    private static final By CART_CHECKOUT_OVERVIEW_SUMMARY_TOTAL_LABEL = By.className("summary_total_label");
    //
    private static final By CART_CHECKOUT_OVERVIEW_FINISH_BTN = By.id("finish");
    private static final By CART_CHECKOUT_OVERVIEW_CANCEL_BTN = By.id("cancel");
    //
    private static final By CART_CHECKOUT_COMPLETE_HEADER = By.className("complete-header");

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
        // 1. Log in to Swag Labs
        login();
    }

    /*
    Happy path (E2E)
    * */
    // TC-CHK-01: Complete checkout with a single product
    @Test
    public void testCompleteCheckoutWithSingleProduct() throws InterruptedException {
        // Add "Sauce Labs Backpack" ($29.99) to the cart
        driver.findElement(INVENTORY_BOUTON_ACTION).click();
        // Navigate to the cart page
        driver.findElement(CART_LINK).click();
        // Click "Checkout" button.
        driver.findElement(CART_CHECKOUT_BTN).click();
        // Verify navigation to checkout-step-one.html with title "Checkout: Your Information".
        boolean isCheckoutStep1Title = swagLabsActionPage.compareElement(CART_CHECKOUT_TITLE, "Checkout: Your Information");
        Assertions.assertTrue(isCheckoutStep1Title, "Checkout page should display the correct title 'Checkout: Your Information'.");
        boolean isCheckoutStep1Page = swagLabsActionPage.isUrlOK(BASE_URL, "/checkout-step-one.html");
        Assertions.assertTrue(isCheckoutStep1Page, "Clicking 'Checkout' should navigate to /checkout-step-one.html.");
        // Enter "Jane" into First Name.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "Jane");
        // Enter "Doe" into Last Name.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "Doe");
        // Enter "02138" into Zip/Postal Code.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "02138");
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        Thread.sleep(1000);
        // Verify navigation to checkout-step-two.html with title "Checkout: Overview".
        boolean isCheckoutStep2Title = swagLabsActionPage.compareElement(CART_CHECKOUT_OVERVIEW_TITLE, "Checkout: Overview");
        Assertions.assertTrue(isCheckoutStep2Title, "Checkout page should display the correct title 'Checkout: Your Information'.");
        boolean isCheckoutStep2Page = swagLabsActionPage.isUrlOK(BASE_URL, "/checkout-step-two.html");
        Assertions.assertTrue(isCheckoutStep2Page, "Clicking 'Checkout' should navigate to /checkout-step-two.html.");
        // Verify item summary: QTY = 1, Name = "Sauce Labs Backpack", Price = "$29.99".
        System.out.println("Checkout: Overview - Product Name  : " + driver.findElement(CART_CHECKOUT_OVERVIEW_ITEM_NAME).getText());
        System.out.println("Checkout: Overview - Product Desc  : " + driver.findElement(CART_CHECKOUT_OVERVIEW_ITEM_DESC).getText());
        System.out.println("Checkout: Overview - Product Price : " + driver.findElement(CART_CHECKOUT_OVERVIEW_ITEM_PRICE).getText());
        boolean itemCheckoutWithQty1 = swagLabsActionPage.compareElement(CART_CHECKOUT_OVERVIEW_ITEM_QUANTITY, "1");
        Assertions.assertTrue(itemCheckoutWithQty1, "The quantity of the item in the cart should be 1.");
        boolean itemCheckoutWithName = swagLabsActionPage.compareElement(CART_CHECKOUT_OVERVIEW_ITEM_NAME, "Sauce Labs Backpack");
        Assertions.assertTrue(itemCheckoutWithName, "The name of the item in the cart should be 'Sauce Labs Backpack'.");
        boolean itemCheckoutWithPrice = swagLabsActionPage.compareElement(CART_CHECKOUT_OVERVIEW_ITEM_PRICE, "$29.99");
        Assertions.assertTrue(itemCheckoutWithPrice, "The price of the item in the cart should be '$29.99'.");
        // Verify financial calculations: Item total = "$29.99", Tax = "$2.40", Total = "$32.39".
        for (int i = 0; i < getOverviewProducts().size(); i++) {
            itemTotal = itemTotal.add(new BigDecimal(driver.findElement(CART_CHECKOUT_OVERVIEW_ITEM_PRICE).getText().substring(1)));
        }
        System.out.println("Checkout: Overview - Item Total  : " + itemTotal);
        tax = itemTotal.multiply(new BigDecimal("0.08")).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Checkout: Overview - Tax         : " + tax);
        total = itemTotal.add(tax);
        System.out.println("Checkout: Overview - Total       : " + total);

        String expectedItemTotal = "$" + itemTotal.setScale(2, RoundingMode.HALF_UP).toPlainString();
        String actualItemTotal = driver.findElement(CART_CHECKOUT_OVERVIEW_SUMMARY_SUBTOTAL_LABEL).getText().substring(12);
        Assertions.assertEquals(expectedItemTotal, actualItemTotal, "The item total should match the expected");

        String expectedTax = "$" + tax.setScale(2, RoundingMode.HALF_UP).toPlainString();
        String actualItemTax = driver.findElement(CART_CHECKOUT_OVERVIEW_SUMMARY_TAX_LABEL).getText().substring(5);
        Assertions.assertEquals(expectedTax, actualItemTax, "The item tax should match the expected");

        String expectedTotal = "$" + total.setScale(2, RoundingMode.HALF_UP).toPlainString();
        String actualTotal = driver.findElement(CART_CHECKOUT_OVERVIEW_SUMMARY_TOTAL_LABEL).getText().substring(7);
        Assertions.assertEquals(expectedTotal, actualTotal, "The total with tax should match the expected");

        // Click "Finish".
        driver.findElement(CART_CHECKOUT_OVERVIEW_FINISH_BTN).click();
        // Verify navigation to checkout-complete.html.
        boolean isCheckoutComplete = swagLabsActionPage.isUrlOK(BASE_URL, "/checkout-complete.html");
        Assertions.assertTrue(isCheckoutComplete, "Url does not update to '/checkout-complete.html'.");
        // Verify header displays "Thank you for your order!" and cart badge is empty.
        boolean isThankYouForYourOrderMsg = swagLabsActionPage.compareElement(CART_CHECKOUT_COMPLETE_HEADER, "Thank you for your order!");
        Assertions.assertTrue(isThankYouForYourOrderMsg, "The Message 'Thank you for your order!' does not display");
    }

    // Methods of class tests
    // sample login method
    private void login() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
    }

    private List<WebElement> getOverviewProducts() {
        return swagLabsActionPage.getList(CART_CHECKOUT_OVERVIEW_ITEM);
    }

}
