package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import io.qameta.allure.Param;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.qa.pages.SwagLabs.SwagLabsActionPage;

import java.util.List;
import java.util.Locale;

public class Junit5Checkout2Tests extends AbstractChromeWebDriverTest {

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
    private static final By CART_CHECKOUT_BTN = By.id("checkout");
    //
    private static final By CART_LINK = By.className("shopping_cart_link");
    private static final By SHOPPING_CART_BADGE = By.className("shopping_cart_badge");
    //
    private static final By CART_CHECKOUT_FIRST_NAME = By.id("first-name");
    private static final By CART_CHECKOUT_LAST_NAME = By.id("last-name");
    private static final By CART_CHECKOUT_POSTAL_CODE = By.id("postal-code");
    private static final By CART_CHECKOUT_CONTINUE_BTN = By.id("continue");
    //
    private static final By CART_CHECKOUT_OVERVIEW_TITLE = By.className("title");
    private static final By CART_CHECKOUT_OVERVIEW_ITEM_PRICE = By.className("inventory_item_price");
    //
    //
    private static final By CART_CHECKOUT_OVERVIEW_SUMMARY_SUBTOTAL_LABEL = By.className("summary_subtotal_label");
    private static final By CART_CHECKOUT_OVERVIEW_SUMMARY_TAX_LABEL = By.className("summary_tax_label");
    private static final By CART_CHECKOUT_OVERVIEW_SUMMARY_TOTAL_LABEL = By.className("summary_total_label");

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
        // 1. Log in to Swag Labs
        login();
    }

    // Exemple of parameterized using Junit5
   /* @ParameterizedTest
    @ValueSource(strings = {"radar", "kayak", "rotor"})
    public void testIsPalindrome(String word) {
        boolean condition = word.length() > 0;
        Assertions.assertTrue(condition);
    }*/

    /*@ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "0, 0, 0",
            "-1, 1, 0"
    })
    public void testAddition(int a, int b, int expected) {
        Assertions.assertEquals(expected, (a + b));
    }*/

    // TC01: Verify product Name: reusable flow parameterizing product name
    @ParameterizedTest
    @CsvSource({
            "0, Sauce Labs Backpack",
            "1, Sauce Labs Bike Light",
            "2, Sauce Labs Bolt T-Shirt",
            "3, Sauce Labs Fleece Jacket",
            "4, Sauce Labs Onesie",
            "5, Test.allTheThings() T-Shirt (Red)"
    })
    public void testCheckProductNameFlow(int index, String expectedProdName) {
        String actualProdName = getProducts().get(index).findElement(INVENTORY_PRODUCT_NAME).getText();
        Assertions.assertEquals(expectedProdName, actualProdName, "The name of product at index " + index + " not match !");
    }

    // TC02: Add product to cart: reusable flow parameterizing product name
    @ParameterizedTest
    @CsvSource({
            "0, Sauce Labs Backpack",
            "1, Sauce Labs Bike Light",
            "2, Sauce Labs Bolt T-Shirt",
            "3, Sauce Labs Fleece Jacket",
            "4, Sauce Labs Onesie",
            "5, Test.allTheThings() T-Shirt (Red)"
    })
    public void testAddProductToCartFlow(int index, String expectedProdName) {
        getProducts().get(index).findElement(INVENTORY_BOUTON_ACTION).click();
        String actualProdName = getProducts().get(index).findElement(INVENTORY_PRODUCT_NAME).getText();
        Assertions.assertEquals(expectedProdName, actualProdName, "The name of product at index " + index + " not match !");
        boolean cartIconBadgeCounter = swagLabsActionPage.compareElement(SHOPPING_CART_BADGE, 1);
        Assertions.assertTrue(cartIconBadgeCounter);
    }

    // TC03: Checkout step one: reusable flow parameterizing: First Name, Last Name, Zip/Postal Code.
    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, 02138",
            "John, Smith, 10001",
            "Alice, Wonderland, 90210",
            "Test, QA, AB-1234"
    })
    public void testCheckoutStepOneFormSubmissionFlow(String firstname, String lastname, String zipcode) {
        // Add "Sauce Labs Backpack" ($29.99) to the cart
        driver.findElement(INVENTORY_BOUTON_ACTION).click();
        // Navigate to Shopping Cart
        driver.findElement(CART_LINK).click();
        driver.findElement(CART_CHECKOUT_BTN).click();
        // Validate and enter personal information (First Name, Last Name, Postal Code) into step-one.html
        // Enter "Jane" into First Name.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, firstname);
        // Enter "Doe" into Last Name.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, lastname);
        // Enter "02138" into Zip/Postal Code.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, zipcode);
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // Verify navigation to checkout-step-two.html with title "Checkout: Overview".
        boolean isCheckoutStep2Title = swagLabsActionPage.compareElement(CART_CHECKOUT_OVERVIEW_TITLE, "Checkout: Overview");
        Assertions.assertTrue(isCheckoutStep2Title, "Checkout page should display the correct title 'Checkout: Your Information'.");
        boolean isCheckoutStep2Page = swagLabsActionPage.isUrlOK(BASE_URL, "/checkout-step-two.html");
        Assertions.assertTrue(isCheckoutStep2Page);
    }

    // TC04: Checkout step two: validation flow verifying item total, tax, and order total of single product.
    @ParameterizedTest
    @CsvSource({
            "0, Sauce Labs Backpack, $29.99, $2.40, $32.39",
            "1, Sauce Labs Bike Light, $9.99, $0.80, $10.79",
            "2, Sauce Labs Bolt T-Shirt, $15.99, $1.28, $17.27",
            "3, Sauce Labs Fleece Jacket, $49.99, $4.00, $53.99"
    })
    public void testCheckoutStepTwoTotalsAndTaxWithSingleProduct(int index, String expectedProdName, String expectedTotalItem, String expectedTax, String expectedTotal) {
        // Add "Sauce Labs Backpack" ($29.99) to the cart
        getProducts().get(index).findElement(INVENTORY_BOUTON_ACTION).click();
        String actualProdName = getProducts().get(index).findElement(INVENTORY_PRODUCT_NAME).getText();
        Assertions.assertEquals(expectedProdName, actualProdName, "The name of product at index " + index + " not match !");
        // Navigate to Shopping Cart
        driver.findElement(CART_LINK).click();
        driver.findElement(CART_CHECKOUT_BTN).click();
        // Validate and enter personal information (First Name, Last Name, Postal Code) into step-one.html
        // Enter "Jane" into First Name.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "Chaker");
        // Enter "Doe" into Last Name.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "Ben Said");
        // Enter "02138" into Zip/Postal Code.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "983736");
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // calcul of item total, tax, and order total
        double sum = 0.00;
        for (int i = 0; i < getOverviewProductPrices().size(); i++) {
            double price = Double.parseDouble(getOverviewProductPrices().get(i).getText().substring(1));
            // Calculate expected item total: sum(prices).
            sum += price;
            System.out.println("Product " + (i + 1) + "'s Price $ : " + price);
        }
        // Calculate expected tax: round(sum(prices) * 0.08, 2).
        double Tax = Math.round((sum * 0.08) * 100.0) / 100.0;
        // Calculate expected total: Item total + Tax.
        double Total = sum + Tax;
        /* // Flow: Verify item total, tax and order total */
        String Item_total = String.format(Locale.US, "$%.2f", sum);
        Assertions.assertEquals(expectedTotalItem, Item_total);
        //
        String TAX = String.format(Locale.US, "$%.2f", Tax);
        Assertions.assertEquals(expectedTax, TAX);
        //
        String OrderTotal = String.format(Locale.US, "$%.2f", Total);
        Assertions.assertEquals(expectedTotal, OrderTotal);
    }

    // TC05: Checkout step two: validation flow verifying item total, tax, and order total of multiple products
    @ParameterizedTest
    @CsvSource({
            "1, $29.99, $2.40, $32.39",
            "2, $39.98, $3.20, $43.18",
            "3, $55.97, $4.48, $60.45",
            "4, $105.96, $8.48, $114.44",
            "5, $113.95, $9.12, $123.07",
            "6, $129.94, $10.40, $140.34"
    })
    public void testCheckoutStepTwoTotalsAndTaxWithMultipleProducts(int nbOfProd, String expectedTotalItem, String expectedTax, String expectedTotal) {
        // Add multiple products
        for (int k = 0; k < nbOfProd; k++) {
            getProducts().get(k).findElement(INVENTORY_BOUTON_ACTION).click();
        }
        // Navigate to Shopping Cart
        driver.findElement(CART_LINK).click();
        driver.findElement(CART_CHECKOUT_BTN).click();
        // Validate and enter personal information (First Name, Last Name, Postal Code) into step-one.html
        // Enter "Jane" into First Name.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "Chaker");
        // Enter "Doe" into Last Name.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "Ben Said");
        // Enter "02138" into Zip/Postal Code.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "983736");
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // calcul of item total, tax, and order total
        double sum = 0.00;
        for (int i = 0; i < getOverviewProductPrices().size(); i++) {
            double price = Double.parseDouble(getOverviewProductPrices().get(i).getText().substring(1));
            // Calculate expected item total: sum(prices).
            sum += price;
            System.out.println("Product " + (i + 1) + "'s Price $ : " + price);
        }
        // Calculate expected tax: round(sum(prices) * 0.08, 2).
        double Tax = Math.round((sum * 0.08) * 100.0) / 100.0;
        // Calculate expected total: Item total + Tax.
        double Total = sum + Tax;
        /* // Flow: Verify item total, tax and order total */
        String Item_total = String.format(Locale.US, "$%.2f", sum);
        Assertions.assertEquals(expectedTotalItem, Item_total);
        //
        String TAX = String.format(Locale.US, "$%.2f", Tax);
        Assertions.assertEquals(expectedTax, TAX);
        //
        String OrderTotal = String.format(Locale.US, "$%.2f", Total);
        Assertions.assertEquals(expectedTotal, OrderTotal);
    }

    /* Parameterized tests using csv files */
    // TC07: Verify product Name: reusable flow parameterizing product name using csv file
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/SwagLabs_Prd_Names.csv", numLinesToSkip = 1)
    public void testVerifyProductNameCsvFlow(int index, String expectedProdName) {
        int k = index - 1;
        String actualProdName = getProducts().get(k).findElement(INVENTORY_PRODUCT_NAME).getText();
        Assertions.assertEquals(expectedProdName, actualProdName, "The name of product at index " + index + " not match !");
    }

    // TC08: Add product to cart: reusable flow parameterizing product name using csv file
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/SwagLabs_Prd_Names.csv", numLinesToSkip = 1)
    public void testAddProductToCartCsvFlow(int index, String expectedProdName) {
        int k = index - 1;
        getProducts().get(k).findElement(INVENTORY_BOUTON_ACTION).click();
        String actualProdName = getProducts().get(k).findElement(INVENTORY_PRODUCT_NAME).getText();
        Assertions.assertEquals(expectedProdName, actualProdName, "The name of product at index " + index + " not match !");
        boolean cartIconBadgeCounter = swagLabsActionPage.compareElement(SHOPPING_CART_BADGE, 1);
        Assertions.assertTrue(cartIconBadgeCounter);
    }

    // TC09: Checkout step one: reusable flow parameterizing: First Name, Last Name, Zip/Postal Code using csv file.
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/SwagLabs_checkout_customer_data.csv", numLinesToSkip = 1)
    public void testCheckoutStepOneFormSubmissionCsvFlow(String firstname, String lastname, String zipcode) {
        // Add "Sauce Labs Backpack" ($29.99) to the cart
        driver.findElement(INVENTORY_BOUTON_ACTION).click();
        // Navigate to Shopping Cart
        driver.findElement(CART_LINK).click();
        driver.findElement(CART_CHECKOUT_BTN).click();
        // Validate and enter personal information (First Name, Last Name, Postal Code) into step-one.html
        // Enter "Jane" into First Name.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, firstname);
        // Enter "Doe" into Last Name.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, lastname);
        // Enter "02138" into Zip/Postal Code.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, zipcode);
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // Verify navigation to checkout-step-two.html with title "Checkout: Overview".
        boolean isCheckoutStep2Title = swagLabsActionPage.compareElement(CART_CHECKOUT_OVERVIEW_TITLE, "Checkout: Overview");
        Assertions.assertTrue(isCheckoutStep2Title, "Checkout page should display the correct title 'Checkout: Your Information'.");
        boolean isCheckoutStep2Page = swagLabsActionPage.isUrlOK(BASE_URL, "/checkout-step-two.html");
        Assertions.assertTrue(isCheckoutStep2Page);
    }

    // TC10: Checkout step two: validation flow verifying item total, tax, and order total of single product using csv.
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/swaglabs_product_totals.csv", numLinesToSkip = 1)
    public void testCheckoutCsvStepTwoTotalsAndTaxWithSingleProduct(int index, String expectedProdName, String expectedTotalItem, String expectedTax, String expectedTotal) {
        // Add "Sauce Labs Backpack" ($29.99) to the cart
        getProducts().get(index).findElement(INVENTORY_BOUTON_ACTION).click();
        String actualProdName = getProducts().get(index).findElement(INVENTORY_PRODUCT_NAME).getText();
        Assertions.assertEquals(expectedProdName, actualProdName, "The name of product at index " + index + " not match !");
        // Navigate to Shopping Cart
        driver.findElement(CART_LINK).click();
        driver.findElement(CART_CHECKOUT_BTN).click();
        // Validate and enter personal information (First Name, Last Name, Postal Code) into step-one.html
        // Enter "Jane" into First Name.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "Chaker");
        // Enter "Doe" into Last Name.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "Ben Said");
        // Enter "02138" into Zip/Postal Code.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "983736");
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // calcul of item total, tax, and order total
        double sum = 0.00;
        for (int i = 0; i < getOverviewProductPrices().size(); i++) {
            double price = Double.parseDouble(getOverviewProductPrices().get(i).getText().substring(1));
            // Calculate expected item total: sum(prices).
            sum += price;
            System.out.println("Product " + (i + 1) + "'s Price $ : " + price);
        }
        // Calculate expected tax: round(sum(prices) * 0.08, 2).
        double Tax = Math.round((sum * 0.08) * 100.0) / 100.0;
        // Calculate expected total: Item total + Tax.
        double Total = sum + Tax;
        /* // Flow: Verify item total, tax and order total */
        String Item_total = String.format(Locale.US, "$%.2f", sum);
        Assertions.assertEquals(expectedTotalItem, Item_total);
        //
        String TAX = String.format(Locale.US, "$%.2f", Tax);
        Assertions.assertEquals(expectedTax, TAX);
        //
        String OrderTotal = String.format(Locale.US, "$%.2f", Total);
        Assertions.assertEquals(expectedTotal, OrderTotal);
    }

    // TC11: Checkout step two: validation flow verifying item total, tax, and order total of multiple products
    @ParameterizedTest
    @CsvFileSource(resources = "/testdata/multiple_products_totals.csv", numLinesToSkip = 1)
    public void testCheckoutCsvStepTwoTotalsAndTaxWithMultipleProducts(int nbOfProd, String expectedTotalItem, String expectedTax, String expectedTotal) {
        // Add multiple products
        for (int k = 0; k < nbOfProd; k++) {
            getProducts().get(k).findElement(INVENTORY_BOUTON_ACTION).click();
        }
        // Navigate to Shopping Cart
        driver.findElement(CART_LINK).click();
        driver.findElement(CART_CHECKOUT_BTN).click();
        // Validate and enter personal information (First Name, Last Name, Postal Code) into step-one.html
        // Enter "Jane" into First Name.
        swagLabsActionPage.enter(CART_CHECKOUT_FIRST_NAME, "John");
        // Enter "Doe" into Last Name.
        swagLabsActionPage.enter(CART_CHECKOUT_LAST_NAME, "Smith");
        // Enter "02138" into Zip/Postal Code.
        swagLabsActionPage.enter(CART_CHECKOUT_POSTAL_CODE, "099473");
        // Click "Continue".
        driver.findElement(CART_CHECKOUT_CONTINUE_BTN).click();
        // calcul of item total, tax, and order total
        double sum = 0.00;
        for (int i = 0; i < getOverviewProductPrices().size(); i++) {
            double price = Double.parseDouble(getOverviewProductPrices().get(i).getText().substring(1));
            // Calculate expected item total: sum(prices).
            sum += price;
            System.out.println("Product " + (i + 1) + "'s Price $ : " + price);
        }
        // Calculate expected tax: round(sum(prices) * 0.08, 2).
        double Tax = Math.round((sum * 0.08) * 100.0) / 100.0;
        // Calculate expected total: Item total + Tax.
        double Total = sum + Tax;
        /* // Flow: Verify item total, tax and order total */
        String Item_total = String.format(Locale.US, "$%.2f", sum);
        Assertions.assertEquals(expectedTotalItem, Item_total);
        //
        String TAX = String.format(Locale.US, "$%.2f", Tax);
        Assertions.assertEquals(expectedTax, TAX);
        //
        String OrderTotal = String.format(Locale.US, "$%.2f", Total);
        Assertions.assertEquals(expectedTotal, OrderTotal);
    }

    // TC0xxx:
    /*@Test
    public void testCompleteEndToEndCheckoutFlow() {
        // Scénario global combinant tous les flux modulaires
        // Enchaîne tous les flux modulaires (Connexion ➔ Ajout au panier ➔ Tunnel d'achat étape 1 & 2 ➔ Validation de la commande réussie / CHECKOUT: COMPLETE!).
    }*/


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

    private List<WebElement> getOverviewProductPrices() {
        return swagLabsActionPage.getList(CART_CHECKOUT_OVERVIEW_ITEM_PRICE);
    }
}
