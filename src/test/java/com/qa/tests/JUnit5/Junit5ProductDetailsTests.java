package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.qa.pages.SwagLabs.SwagLabsActionPage;

import java.util.List;
import io.testomat.core.annotation.TestId;

public class Junit5ProductDetailsTests extends AbstractChromeWebDriverTest {

    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";

    private static final By LISTE_PRODUITS = By.className("inventory_list");
    private static final By PRODUCTS_CARDS = By.className("inventory_item");
    private static final By PRODUCT_DESCRIPTION = By.className("inventory_item_desc");
    private static final By PRODUCTS_TITRES = By.className("inventory_item_name");
    private static final By PRODUCTS_PRICE = By.className("inventory_item_price");

    private static final By PRODUCT_DETAILS_NAME = By.className("inventory_details_name");
    private static final By PRODUCT_DETAILS_DESC = By.className("inventory_details_desc");
    private static final By PRODUCT_DETAILS_PRICE = By.className("inventory_details_price");

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
    }

    String inventory_title;
    String inventory_desc;
    String inventory_price;

    // TESTS //
    @TestId("e98f8a9c")
    @Test
    public void testForProductDetailsAndComparison() {
        // 1. Log in to Swag Labs
        login();
        // 2. Verify Inventory Page is Reached
        boolean IsInventoryContainer = swagLabsActionPage.isElementDisplayed(LISTE_PRODUITS);
        Assertions.assertTrue(IsInventoryContainer);
        // 3. Capture Product Details from the Inventory List
        List<WebElement> products = swagLabsActionPage.getList(PRODUCTS_CARDS);
        for (WebElement prod : products) {
            // Utiliser des sous-éléments précis ou s'assurer que les locators sont stricts
            inventory_title = prod.findElement(PRODUCTS_TITRES).getText();
            inventory_desc = prod.findElement(PRODUCT_DESCRIPTION).getText();
            inventory_price = prod.findElement(PRODUCTS_PRICE).getText();

            // 4: Navigate to the Product Details Page
            prod.findElement(PRODUCTS_TITRES).click();
            break;
        }
        // 5. Verify navigation to the product details view (URL contains /inventory-item.html).
        boolean IsProdDetailsUrl = swagLabsActionPage.isContain("/inventory-item.html?id=4");
        Assertions.assertTrue(IsProdDetailsUrl);

        // 6. Compare Product Details on the Detail Page
        boolean compareProdTitle = swagLabsActionPage.compareElement(PRODUCT_DETAILS_NAME, inventory_title);
        boolean compareProdDesc = swagLabsActionPage.compareElement(PRODUCT_DETAILS_DESC, inventory_desc);
        boolean compareProdPrice = swagLabsActionPage.compareElement(PRODUCT_DETAILS_PRICE, inventory_price);
        Assertions.assertTrue(compareProdTitle);
        Assertions.assertTrue(compareProdDesc);
        Assertions.assertTrue(compareProdPrice);
    }

    private void login() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
    }

}
