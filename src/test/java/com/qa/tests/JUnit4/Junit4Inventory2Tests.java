package com.qa.tests.JUnit4;
import com.qa.tests.AbstractJunit4ChromeWebDriverTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.qa.pages.SwagLabs.SwagLabsActionPage;
import org.testng.Assert;
import java.util.List;
import io.testomat.core.annotation.TestId;


public class Junit4Inventory2Tests extends AbstractJunit4ChromeWebDriverTest {
    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    //  Login Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    // Inventory Locators
    private static final By PRODUCTS_CARDS = By.className("inventory_item");
    private static final String TEXTE_BOUTON_ACTION = "Add to cart";
    private static final By BOUTONS_ACTION = By.cssSelector(".inventory_item .btn_inventory");

    // Expected Nombre Of Products
    private static final int EXPECTED_PRODUCTS_NUMBER = 6;

    // create an instance of POM pages
    private SwagLabsActionPage swagLabsActionPage;

    @Before
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
    }

    @Rule
    public TestName testName = new TestName();

    // ---------- Scenario 1 : affichage global et integrite des elements ----------
    //TC07
    @TestId("49fc47e9")
    @Test
    public void testEachProductHasButtonAddToCart() {
        // @T043d1f7a
        sauceDemoLogin();
        List<WebElement> products = driver.findElements(PRODUCTS_CARDS);
        Assert.assertFalse(products.isEmpty(), "No Products are displayed ! ");
        for (WebElement product : products) {
            String buttonText = product.findElement(BOUTONS_ACTION).getText();
            Assert.assertEquals(buttonText, TEXTE_BOUTON_ACTION, "Action Product Button should display exactly : " + TEXTE_BOUTON_ACTION);
        }
    }

    // Tests Methods
    private void sauceDemoLogin() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys(USERNAME_VALIDE);
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys(PASSWORD_VALIDE);
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
    }
}
