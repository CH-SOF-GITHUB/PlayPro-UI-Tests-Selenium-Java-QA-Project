package com.qa.tests.Junit4;

import com.qa.factory.PCloudyDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.utilities.TakeCapture;
import org.testng.Assert;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import static org.junit.Assert.assertTrue;
import io.testomat.core.annotation.TestId;

public class JunitInventoryTests {
    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    //  Login Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final By BOUTON_LOGIN = By.id("login-button");
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

    // Define Web driver object With PCloudy Driver Factory
    private WebDriver driver;
    PCloudyDriverFactory pCloudyDriverFactory;

    // Add an instance of TakeCapture class
    TakeCapture capture;

    @Rule
    public TestName testName = new TestName();

    private boolean testPassed = true;

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            testPassed = false;
        }
    };

    boolean pcloudy = false;
    boolean LT = false;

    // Setup Tests
    @Before
    public void setUp() {
        System.out.println("Starting Test Automation Pcloudy...");
        try {
            WebDriverManager.getInstance().setup();
            if (pcloudy) {
                pCloudyDriverFactory = new PCloudyDriverFactory();
                driver = pCloudyDriverFactory.createPcloudyDriver();
            } else {
                driver = new ChromeDriver();
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().window().maximize();
            capture = new TakeCapture(driver);
            sauceDemoLogin();
        } catch (WebDriverException e) {
            throw new WebDriverException("Web Driver not Initialized: " + e.getMessage());
        }
    }

    // ---------- Scenario 1 : affichage global et integrite des elements ----------
    // TC01
    @TestId("b0a99a12")
    @Test
    public void testPageInventoryReachedAfterLogin() {
        // Assertions
        Assert.assertTrue(getCurrentURL().contains("/inventory.html"));
    }
    // TC02
    @TestId("df2fb3c4")
    @Test
    public void testCatalogDisplayTheSixProducts() {
        Assert.assertEquals(returnProductsNumber(), EXPECTED_PRODUCTS_NUMBER);
    }
    // TC03
    @TestId("6570f6d6")
    @Test
    public void testEachProductHasNonEmptyTitle() {
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
    @TestId("36270dfa")
    @Test
    public void testEachProductHasDescriptionNonEmpty() {
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
    @TestId("daabdb6a")
    @Test
    public void testEachProductHasImage() {
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
    @TestId("d46dffb7")
    @Test
    public void testEachProductHasPriceInMonetaryFormat() {
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

    // tearDown Tests //
    @After
    public void tearDown() throws IOException {
        if (driver != null) {
            try {
                System.out.println("I'm Executed");
                //((JavascriptExecutor) driver).executeScript("pCloudy_setResult", testPassed ? "pass" : "fail");
                capture.takeScreenshot(testName.getMethodName());
            } finally {
                driver.quit();
            }
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

    // Actions //
    public boolean isElementDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No such element : " + e.getMessage());
        }
    }

    public String getCurrentURL() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            throw new NoSuchSessionException("Cannot retrieve current URL : " + e.getMessage());
        }
    }

    public String getElementText(By by) {
        try {
            return driver.findElement(by).getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("cannot get Text of this element:  " + e.getMessage());
        }
    }
}
