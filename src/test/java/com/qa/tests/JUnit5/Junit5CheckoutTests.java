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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;


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
    private static final By CART_CHECKOUT_ERROR_XMARK = By.cssSelector(".svg-inline--fa.fa-circle-xmark.error_icon");
    private static final By CART_CHECKOUT_ERROR_BUTTON = By.className("error-button");
    private static final By CART_CHECKOUT_ERROR_CONTAINER = By.className("error-message-container");
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
    @Tag("Critical")
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

    // TC-CHK-02: Complete checkout with a multiple product
    @Tag("Critical")
    @Test
    public void testCompleteCheckoutWithMultipleProduct() throws InterruptedException {
        // Add "Sauce Labs Backpack" ($29.99), "Sauce Labs Bike Light" ($9.99), "Sauce Labs Bolt T-Shirt" ($15.99) to the cart
        for (int i = 0; i < 2; i++) {
            getProducts().get(i).findElement(INVENTORY_BOUTON_ACTION).click();
            Thread.sleep(500);
        }
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
        Thread.sleep(500);
        // Verify navigation to checkout-step-two.html with title "Checkout: Overview".
        boolean isCheckoutStep2Title = swagLabsActionPage.compareElement(CART_CHECKOUT_OVERVIEW_TITLE, "Checkout: Overview");
        Assertions.assertTrue(isCheckoutStep2Title, "Checkout page should display the correct title 'Checkout: Your Information'.");
        boolean isCheckoutStep2Page = swagLabsActionPage.isUrlOK(BASE_URL, "/checkout-step-two.html");
        Assertions.assertTrue(isCheckoutStep2Page, "Clicking 'Checkout' should navigate to /checkout-step-two.html.");
        // Verify item summary: QTY, Name, Price of each product.
        for (int j = 0; j < getOverviewProducts().size(); j++) {
            System.out.println("Checkout: Overview - Product " + (j + 1) + " Name  : " + getOverviewProducts().get(j).findElement(CART_CHECKOUT_OVERVIEW_ITEM_NAME).getText());
            System.out.println("Checkout: Overview - Product " + (j + 1) + " Desc  : " + getOverviewProducts().get(j).findElement(CART_CHECKOUT_OVERVIEW_ITEM_DESC).getText());
            System.out.println("Checkout: Overview - Product " + (j + 1) + " Price : " + getOverviewProducts().get(j).findElement(CART_CHECKOUT_OVERVIEW_ITEM_PRICE).getText());
            System.out.println("*********************************************************");
            boolean itemCheckoutWithQty1 = swagLabsActionPage.compareElement(CART_CHECKOUT_OVERVIEW_ITEM_QUANTITY, "1");
            Assertions.assertTrue(itemCheckoutWithQty1, "The quantity of the item in the cart should be 1.");

            String currentPrice = getOverviewProducts().get(j).findElement(CART_CHECKOUT_OVERVIEW_ITEM_PRICE).getText().substring(1);
            itemTotal = itemTotal.add(new BigDecimal(currentPrice));
        }
        // Verify financial calculations: Item total = "$29.99", Tax = "$2.40", Total = "$32.39".
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
    }

    // TC-CHK-03: Attempt to continue with empty first name
    @Tag("Critical")
    @Test
    public void testAttemptToContinueWithEmptyFirstName() {
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
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // Leave "First Name" field empty.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "");
        // Enter "Smith" in "Last Name".
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "Smith");
        // Enter "90210" in "Zip/Postal Code".
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "90210");
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // Error banner displays text: "Error: First Name is required".
        boolean compareErrorMsg = swagLabsActionPage.compareElement(CART_CHECKOUT_ERROR_MESSAGE, "Error: First Name is required");
        Assertions.assertTrue(compareErrorMsg, "Error message does not display !");
        boolean errorXMarkDisplayed = swagLabsActionPage.isElementDisplayed(CART_CHECKOUT_ERROR_XMARK);
        Assertions.assertTrue(errorXMarkDisplayed);
    }

    // TC-CHK-04: Attempt to continue with empty last name
    @Tag("Critical")
    @Test
    public void testAttemptToContinueWithEmptyLastName() {
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
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // Enter "John" in "First Name".
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "John");
        // Leave "Last Name" field empty.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "");
        // Enter "90210" in "Zip/Postal Code".
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "90210");
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // Error banner displays text: "Error: First Name is required".
        boolean compareErrorMsg = swagLabsActionPage.compareElement(CART_CHECKOUT_ERROR_MESSAGE, "Error: Last Name is required");
        Assertions.assertTrue(compareErrorMsg, "Error message does not display !");
        boolean errorXMarkDisplayed = swagLabsActionPage.isElementDisplayed(CART_CHECKOUT_ERROR_XMARK);
        Assertions.assertTrue(errorXMarkDisplayed);
    }

    // TC-CHK-05: Attempt to continue with empty postal code
    @Tag("Critical")
    @Test
    public void testAttemptToContinueWithEmptyPostalCode() {
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
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // Enter "John" in "First Name".
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "John");
        // Enter "Doe" in "Last Name".
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "Doe");
        // Leave "Zip/Postal Code" field empty.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "");
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // Error banner displays text: "Error: First Name is required".
        boolean compareErrorMsg = swagLabsActionPage.compareElement(CART_CHECKOUT_ERROR_MESSAGE, "Error: Postal Code is required");
        Assertions.assertTrue(compareErrorMsg, "Error message does not display !");
        boolean errorXMarkDisplayed = swagLabsActionPage.isElementDisplayed(CART_CHECKOUT_ERROR_XMARK);
        Assertions.assertTrue(errorXMarkDisplayed);
    }

    // TC-CHK-06: Attempt to continue with all fields empty
    @Tag("Functional")
    @Test
    public void testAttemptToContinueWithAllFieldsEmpty() {
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
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // Leave "First Name" field empty.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "");
        // Leave "Last Name" field empty.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "");
        // Leave "Zip/Postal Code" field empty.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "");
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // Error banner displays text: "Error: First Name is required".
        boolean compareErrorMsg = swagLabsActionPage.compareElement(CART_CHECKOUT_ERROR_MESSAGE, "Error: First Name is required");
        Assertions.assertTrue(compareErrorMsg, "Error message does not display !");
        boolean errorXMarkDisplayed = swagLabsActionPage.isElementDisplayed(CART_CHECKOUT_ERROR_XMARK);
        Assertions.assertTrue(errorXMarkDisplayed);
    }

    // TC-CHK-07: Error banner dismissal
    @Tag("Edge")
    @Test
    public void testErrorBannerDismissal() throws InterruptedException {
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
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // Leave "First Name" field empty.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "");
        // Leave "Last Name" field empty.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "");
        // Leave "Zip/Postal Code" field empty.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "");
        // Click the "x" close button located on the right side of the error banner.
        driver.findElement(CART_CHECKOUT_ERROR_BUTTON).click();
        // Wait for 1s
        Thread.sleep(1000);
        // Error banner disappears from view.
        // Vérifie que l'élément n'est plus affiché à l'écran.
        boolean isErrorBannerVisible = swagLabsActionPage.isElementDisplayed(CART_CHECKOUT_ERROR_CONTAINER);
        Assertions.assertTrue(isErrorBannerVisible, "The error banner should disappear from view after clicking the close button.");
    }

    // TC-CHK-08: Cancel checkout from step one
    @Tag("Functional")
    @Test
    public void testCancelCheckoutFromStepOne() {
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
        // Click "Cancel".
        driver.findElement(CART_CHECKOUT_CANCEL_BTN).click();
        // User is navigated back to cart.html.
        boolean pageStillCart = swagLabsActionPage.isUrlOK(BASE_URL, "/cart.html");
        Assertions.assertTrue(pageStillCart);
        // Cart contents and item quantities are preserved.
        Assertions.assertTrue(swagLabsActionPage.compareElement(CART_QUANTITY, 1));
        Assertions.assertTrue(swagLabsActionPage.compareElement(CART_INVENTORY_ITEM_NAME, "Sauce Labs Backpack"));
        Assertions.assertTrue(swagLabsActionPage.compareElement(CART_INVENTORY_ITEM_DESC, "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."));
        Assertions.assertTrue(swagLabsActionPage.compareElement(CART_INVENTORY_ITEM_PRICE, "$29.99"));
    }

    // TC-CHK-09: Cancel checkout from step two overview
    @Tag("Functional")
    @Test
    public void testCancelCheckoutFromStepTwoOverview() throws InterruptedException {
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
        Thread.sleep(500);
        // Verify navigation to checkout-step-two.html with title "Checkout: Overview".
        boolean isCheckoutStep2Title = swagLabsActionPage.compareElement(CART_CHECKOUT_OVERVIEW_TITLE, "Checkout: Overview");
        Assertions.assertTrue(isCheckoutStep2Title, "Checkout page should display the correct title 'Checkout: Your Information'.");
        boolean isCheckoutStep2Page = swagLabsActionPage.isUrlOK(BASE_URL, "/checkout-step-two.html");
        Assertions.assertTrue(isCheckoutStep2Page, "Clicking 'Checkout' should navigate to /checkout-step-two.html.");
        // Click "Cancel" button.
        driver.findElement(CART_CHECKOUT_OVERVIEW_CANCEL_BTN).click();
        // User is redirected to inventory.html.
        boolean isNowInventoryPage = swagLabsActionPage.isUrlOK(BASE_URL, "/inventory.html");
        Assertions.assertTrue(isNowInventoryPage);
        //Cart retains previously selected items (cart badge persists).
        boolean cartPersistsOne = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, 1 items");
        Assertions.assertTrue(cartPersistsOne);
    }

    // TC-CHK-10: Item subtotal, tax rate, and grand total accuracy
    @Tag("Critical")
    @Test
    public void testItemSubtotalTaxRateAndGrandTotalAccuracy() throws InterruptedException {
        // Add "Sauce Labs Backpack" ($29.99), "Sauce Labs Bike Light" ($9.99), "Sauce Labs Bolt T-Shirt" ($15.99) to the cart
        for (int i = 0; i < 1; i++) {
            getProducts().get(i).findElement(INVENTORY_BOUTON_ACTION).click();
            Thread.sleep(500);
        }
        // Assert cart badge count of 3
        boolean cartNowCount3 = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, 1 items");
        Assertions.assertTrue(cartNowCount3);
        // Navigate to the cart page
        driver.findElement(CART_LINK).click();
        // Click "Checkout" button.
        driver.findElement(CART_CHECKOUT_BTN).click();
        // Enter "Jane" into First Name.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "Jane");
        // Enter "Doe" into Last Name.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "Doe");
        // Enter "02138" into Zip/Postal Code.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "02138");
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        Thread.sleep(500);
        // Read individual product prices from line items.
        // mathematical calculations variables
        double sumTotal = 0.00;
        for (int i = 0; i < getOverviewProductPrices().size(); i++) {
            double price = Double.parseDouble(getOverviewProductPrices().get(i).getText().substring(1));
            sumTotal += price;
            System.out.println("Product " + (i + 1) + "'s Price $ : " + price);
        }
        // Calculate expected item total: sum(prices).
        System.out.println("Total sum : " + sumTotal);
        // Calculate expected tax: round(sum(prices) * 0.08, 2).
        double expTax = Math.round((sumTotal * 0.08) * 100.0) / 100.0;
        System.out.println("Tax : " + expTax);
        // Calculate expected total: Item total + Tax.
        double expTotal = sumTotal + expTax;
        System.out.println("Total : " + expTotal);
        /* Compare calculated values against displayed values on page */
        //Item total: $<amount>
        String actual_Item_total = driver.findElement(CART_CHECKOUT_OVERVIEW_SUMMARY_SUBTOTAL_LABEL).getText().substring(12);
        String expected_Item_total = String.format(Locale.US, "$%.2f", sumTotal);
        Assertions.assertEquals(actual_Item_total, expected_Item_total);
        //Tax: $<amount>
        String actual_TAX = driver.findElement(CART_CHECKOUT_OVERVIEW_SUMMARY_TAX_LABEL).getText().substring(5);
        String expected_TAX = String.format(Locale.US, "$%.2f", expTax);
        Assertions.assertEquals(actual_TAX, expected_TAX);
        //Total: $<amount>
        String actual_Total = driver.findElement(CART_CHECKOUT_OVERVIEW_SUMMARY_TOTAL_LABEL).getText().substring(7);
        String expected_Total = String.format(Locale.US, "$%.2f", expTotal);
        Assertions.assertEquals(actual_Total, expected_Total);
        //Expected result: Displayed tax and total match expected mathematical calculations exactly without rounding discrepancies.
        System.out.println("Checkout - Overview - Actual Item Total   : " + actual_Item_total);
        System.out.println("Checkout - Overview - Actual Tax          : " + actual_TAX);
        System.out.println("Checkout - Overview - Actual Total        : " + actual_Total);
        System.out.println("Checkout - Overview - Expected Item Total : " + expected_Item_total);
        System.out.println("Checkout - Overview - Expected Tax        : " + expected_TAX);
        System.out.println("Checkout - Overview - Expected Total      : " + expected_Total);
    }

    // TC-CHK-11: Customer information persistence across browser back / forward
    @Tag("Functional")
    @Test
    public void testCustomerInformationPersistenceAcrossBrowserBackForward() throws InterruptedException {
        // Add "Sauce Labs Backpack" ($29.99), "Sauce Labs Bike Light" ($9.99), "Sauce Labs Bolt T-Shirt" ($15.99) to the cart
        for (int i = 0; i < 3; i++) {
            getProducts().get(i).findElement(INVENTORY_BOUTON_ACTION).click();
            Thread.sleep(500);
        }
        // Assert cart badge count of 3
        boolean cartNowCount3 = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, 3 items");
        Assertions.assertTrue(cartNowCount3);
        // Navigate to the cart page
        driver.findElement(CART_LINK).click();
        // Click "Checkout" button.
        driver.findElement(CART_CHECKOUT_BTN).click();
        // Enter "Jane" into First Name.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "Chaker");
        // Enter "Doe" into Last Name.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "Ben Said");
        // Enter "02138" into Zip/Postal Code.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "02138");
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        Thread.sleep(500);
        // Click the browser Back button to return to Checkout: Your Information (checkout-step-one.html).
        swagLabsActionPage.backPage();
        //Verify whether the previously entered First Name, Last Name and Postal Code are retained or reset.
        String actualFirstName = driver.findElement(CART_CHECKOUT_FIRST_NAME).getDomAttribute("value");
        Assertions.assertEquals("", actualFirstName);
        String actualLastName = driver.findElement(CART_CHECKOUT_LAST_NAME).getDomAttribute("value");
        Assertions.assertEquals("", actualLastName);
        String actualPostCode = driver.findElement(CART_CHECKOUT_POSTAL_CODE).getDomAttribute("value");
        Assertions.assertEquals("", actualPostCode);
        System.out.println("the previously entered First Name, Last Name and Postal Code are reset");
        //If the fields are reset, enter valid customer information again. Otherwise, keep the existing values.
        // Enter again into First Name.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "Smith");
        // Enter again into Last Name.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "Wesson");
        // Enter again into Zip/Postal Code.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "056478");
        //Click Continue to submit the customer information.
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        //Verify that the Checkout: Overview page (checkout-step-two.html) loads successfully.
        boolean isCheckoutStep2Page = swagLabsActionPage.isUrlOK(BASE_URL, "/checkout-step-two.html");
        Assertions.assertTrue(isCheckoutStep2Page, "Clicking 'Checkout' should navigate to /checkout-step-two.html.");
        //Verify that the previously selected products are still displayed in the overview with their correct names, quantities and prices.
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(0).findElement(CART_CHECKOUT_OVERVIEW_ITEM_NAME), "Sauce Labs Backpack"));
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(0).findElement(CART_CHECKOUT_OVERVIEW_ITEM_PRICE), "$29.99"));
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(0).findElement(CART_CHECKOUT_OVERVIEW_ITEM_DESC), "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."));
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(0).findElement(CART_CHECKOUT_OVERVIEW_ITEM_QUANTITY), "1"));
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(1).findElement(CART_CHECKOUT_OVERVIEW_ITEM_NAME), "Sauce Labs Bike Light"));
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(1).findElement(CART_CHECKOUT_OVERVIEW_ITEM_PRICE), "$9.99"));
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(1).findElement(CART_CHECKOUT_OVERVIEW_ITEM_DESC), "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."));
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(1).findElement(CART_CHECKOUT_OVERVIEW_ITEM_QUANTITY), "1"));
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(2).findElement(CART_CHECKOUT_OVERVIEW_ITEM_NAME), "Sauce Labs Bolt T-Shirt"));
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(2).findElement(CART_CHECKOUT_OVERVIEW_ITEM_PRICE), "$15.99"));
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(2).findElement(CART_CHECKOUT_OVERVIEW_ITEM_DESC), "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt."));
        Assertions.assertTrue(swagLabsActionPage.compareElement(getOverviewProducts().get(2).findElement(CART_CHECKOUT_OVERVIEW_ITEM_QUANTITY), "1"));
        //Verify that the Item Total, Tax and Total are displayed correctly.
        double sumTotal = 0.00;
        for (int i = 0; i < getOverviewProductPrices().size(); i++) {
            double price = Double.parseDouble(getOverviewProductPrices().get(i).getText().substring(1));
            sumTotal += price;
            System.out.println("Product " + (i + 1) + "'s Price $ : " + price);
        }
        // Calculate expected item total: sum(prices).
        // Calculate expected tax: round(sum(prices) * 0.08, 2).
        double expTax = Math.round((sumTotal * 0.08) * 100.0) / 100.0;
        // Calculate expected total: Item total + Tax.
        double expTotal = sumTotal + expTax;
        /* Compare calculated values against displayed values on page */
        //Item total: $<amount>
        String actual_Item_total = driver.findElement(CART_CHECKOUT_OVERVIEW_SUMMARY_SUBTOTAL_LABEL).getText().substring(12);
        String expected_Item_total = String.format(Locale.US, "$%.2f", sumTotal);
        Assertions.assertEquals(actual_Item_total, expected_Item_total);
        //Tax: $<amount>
        String actual_TAX = driver.findElement(CART_CHECKOUT_OVERVIEW_SUMMARY_TAX_LABEL).getText().substring(5);
        String expected_TAX = String.format(Locale.US, "$%.2f", expTax);
        Assertions.assertEquals(actual_TAX, expected_TAX);
        //Total: $<amount>
        String actual_Total = driver.findElement(CART_CHECKOUT_OVERVIEW_SUMMARY_TOTAL_LABEL).getText().substring(7);
        String expected_Total = String.format(Locale.US, "$%.2f", expTotal);
        Assertions.assertEquals(actual_Total, expected_Total);
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

    private List<WebElement> getProducts() {
        return swagLabsActionPage.getList(_PRODUCT_ITEMS);
    }

    private List<WebElement> getOverviewProductPrices() {
        return swagLabsActionPage.getList(CART_CHECKOUT_OVERVIEW_ITEM_PRICE);
    }

    private double sumAll(double... nums) { //var-args to let the caller pass an arbitrary number of int
        double sum = 0.00; //start with 0
        for (double n : nums) { //this won't execute if no argument is passed
            sum += n; // this will repeat for all the arguments
        }
        return sum; //return the sum
    }

}
