package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.qa.pages.SwagLabs.SwagLabsActionPage;

import java.util.List;
import io.testomat.core.annotation.TestId;

public class Junit5InventoryTests extends AbstractChromeWebDriverTest {

    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    private static final By LISTE_PRODUITS = By.className("inventory_list");
    private static final By PRODUCTS_TITRES = By.className("inventory_item_name");
    private static final By PRODUCT_DETAILS_NAME = By.className("inventory_details_name");
    private static final By BACK_TO_PRODUCTS = By.id("back-to-products");

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
    }

    // TESTS //
    @TestId("a76316c8")
    @Test
    public void testInventoryReachedAndProductSelection() {
        // 1. Log in to Swag Labs
        login();
        // 2. Verify Inventory Page is Reached
        boolean IsInventoryUrl = swagLabsActionPage.isContain("/inventory.html");
        Assertions.assertTrue(IsInventoryUrl);
        boolean IsInventoryTitle = swagLabsActionPage.isTitle("Swag Labs");
        Assertions.assertTrue(IsInventoryTitle);
        boolean IsInventoryContainer = swagLabsActionPage.isElementDisplayed(LISTE_PRODUITS);
        Assertions.assertTrue(IsInventoryContainer);
        // 3. Interact with Product Filter / Item Selection
        List<WebElement> products = swagLabsActionPage.getList(PRODUCTS_TITRES);
        for (WebElement prod : products) {
            swagLabsActionPage.clickOnElement(prod);
            break;
        }
        // Assert the product name title displays "Sauce Labs Backpack".
        String ProductName = swagLabsActionPage.getElementText(PRODUCT_DETAILS_NAME);
        Assertions.assertEquals("Sauce Labs Backpack", ProductName);
        boolean IsProductUrl = swagLabsActionPage.isContain("/inventory-item.html?id=4");
        Assertions.assertTrue(IsProductUrl);

        // 4. Verify Inventory State and Return Navigation
        swagLabsActionPage.clickOnElement(BACK_TO_PRODUCTS);
    }

    private void login() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
    }
}
