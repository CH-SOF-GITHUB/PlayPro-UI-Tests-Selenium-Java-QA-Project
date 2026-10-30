package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.qa.pages.SwagLabs.SwagLabsActionPage;
import io.testomat.core.annotation.TestId;

public class Junit5LoginTests extends AbstractChromeWebDriverTest {
    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final By LISTE_PRODUITS = By.className("inventory_list");
    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    private static final By INVENTORY_PRODUCTS_TITLE = By.className("title");

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
    }

    // TESTS - successful login //
    @TestId("9d1e80c7")
    @Test
    public void testValidLogin() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
        // Assertions
        Assertions.assertTrue(swagLabsActionPage.isContain("/inventory.html"));
        Assertions.assertTrue(swagLabsActionPage.isElementDisplayed(LISTE_PRODUITS));
        Assertions.assertTrue(swagLabsActionPage.compareElement(INVENTORY_PRODUCTS_TITLE, "Products"));
    }
}
