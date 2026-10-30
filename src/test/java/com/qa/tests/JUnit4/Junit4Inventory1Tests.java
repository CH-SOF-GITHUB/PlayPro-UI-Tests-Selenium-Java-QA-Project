package com.qa.tests.JUnit4;
import com.qa.tests.AbstractJunit4ChromeWebDriverTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.pages.SwagLabs.SwagLabsActionPage;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import static org.junit.Assert.assertTrue;
import io.testomat.core.annotation.TestId;

public class Junit4Inventory1Tests extends AbstractJunit4ChromeWebDriverTest {
    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    //  Login Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    // Inventory Locators
    private static final By PRODUCTS_CARDS = By.className("inventory_item");
    private static final By PRODUCTS_TITRES = By.className("inventory_item_name");
    private static final By PRODUCTS_DESCRIPTIONS = By.className("inventory_item_desc");
    private static final By PRODUCTS_IMAGES = By.cssSelector(".inventory_item_img img");
    private static final By PRODUCTS_PRICE = By.className("inventory_item_price");

    // Une image est valide si elle est completement chargee et a une largeur naturelle > 0
    private static final String SCRIPT_IMAGE_CHARGEE =
            "return arguments[0].complete && typeof arguments[0].naturalWidth !== 'undefined'"
                    + " && arguments[0].naturalWidth > 0;";

    // Expected Nombre Of Products
    private static final int EXPECTED_PRODUCTS_NUMBER = 6;

    // Format monetaire : symbole $ suivi d'un nombre avec exactement deux decimales
    private static final Pattern FORMAT_PRIX = Pattern.compile("^\\$\\d+\\.\\d{2}$");

    // create an instance of POM pages
    private SwagLabsActionPage swagLabsActionPage;

    @Before
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
    }

    @Rule
    public TestName testName = new TestName();

    // ---------- Scenario 1 : affichage global et integrite des elements ----------
    // TC01
    @TestId("4d87e452")
    @Test
    public void testPageInventoryReachedAfterLogin() {
        sauceDemoLogin();
        // Assertions
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
    }

    // TC02
    @TestId("df2fb3c4")
    @Test
    public void testCatalogDisplayTheSixProducts() {
        sauceDemoLogin();
        Assert.assertEquals(returnProductsNumber(), EXPECTED_PRODUCTS_NUMBER);
    }

    // TC03
    @TestId("448dd6d3")
    @Test
    public void testEachProductHasNonEmptyTitle() {
        sauceDemoLogin();
        List<WebElement> products = driver.findElements(PRODUCTS_CARDS);
        Assert.assertFalse(products.isEmpty(), "No Products are displayed ! ");
        int index = 0;
        for (WebElement product : products) {
            String title = product.findElement(PRODUCTS_TITRES).getText();
            System.out.printf("Product %d's Title : %s", index + 1, title).println();
            Assert.assertFalse(title.trim().isEmpty(), "A product's title cannot be empty !");
            index++;
        }
    }

    // TC04
    @TestId("e0d8d597")
    @Test
    public void testEachProductHasDescriptionNonEmpty() {
        sauceDemoLogin();
        List<WebElement> products = driver.findElements(PRODUCTS_CARDS);
        Assert.assertFalse(products.isEmpty(), "No Products are displayed ! ");
        int index = 0;
        for (WebElement product : products) {
            String description = product.findElement(PRODUCTS_DESCRIPTIONS).getText();
            System.out.printf("Product %d's Description : %s", index + 1, description).println();
            Assert.assertFalse(description.trim().isEmpty(), "A product's description cannot be empty !");
            index++;
        }

    }

    // TC05
    @TestId("a2aa0573")
    @Test
    public void testEachProductHasImage() {
        sauceDemoLogin();
        List<Boolean> isImages = getValidateImages();
        Assert.assertEquals(isImages.size(), EXPECTED_PRODUCTS_NUMBER);
        int index = 0;
        for (Boolean isImage : isImages) {
            System.out.printf("Product %d has Image : %s", index + 1, isImage).println();
            assertTrue("L'image du produit " + (index + 1) + " est absente ou cassee", isImages.get(index));
            index++;
        }
    }

    //TC06
    @TestId("3162b7a9")
    @Test
    public void testEachProductHasPriceInMonetaryFormat() {
        sauceDemoLogin();
        List<WebElement> products = driver.findElements(PRODUCTS_CARDS);
        Assert.assertFalse(products.isEmpty(), "No Products are displayed ! ");
        int index = 0;
        for (WebElement product : products) {
            String price = product.findElement(PRODUCTS_PRICE).getText();
            System.out.printf("Product %d 's Price : %s", index + 1, price).println();
            Assert.assertTrue(FORMAT_PRIX.matcher(price).matches(), "Format Price invalide : " + price);
            index++;
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

    public int returnProductsNumber() {
        List<WebElement> products = driver.findElements(PRODUCTS_CARDS);
        return products.size();
    }

    /**
     * Pour chaque produit : true si l'attribut src est renseigne
     * et si l'image est reellement chargee (pas d'image cassee / 404).
     */
    public List<Boolean> getValidateImages() {
        List<WebElement> prods = driver.findElements(PRODUCTS_IMAGES);
        List<Boolean> valeurs = new ArrayList<>();
        for (WebElement image : prods) {
            String src = image.getAttribute("src");
            boolean isImage = (src != null && !src.trim().isEmpty());
            // On passe directement le WebElement sans cast !
            valeurs.add(isImage && isImageDisplayed(image));
        }
        return valeurs;
    }

    // Surcharge ou modification de la méthode pour accepter directement le WebElement
    public boolean isImageDisplayed(WebElement image) {
        try {
            // Attendre jusqu'à 5 secondes que l'image soit complètement chargée (naturalWidth > 0)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(d -> {
                Object result = ((JavascriptExecutor) d).executeScript(SCRIPT_IMAGE_CHARGEE, image);
                return Boolean.TRUE.equals(result);
            });
        } catch (Exception e) {
            return false; // Retourne false si le délai est dépassé ou si une erreur survient
        }
    }
}
