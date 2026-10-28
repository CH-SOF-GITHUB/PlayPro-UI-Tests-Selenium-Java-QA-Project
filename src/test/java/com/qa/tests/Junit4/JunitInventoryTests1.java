package com.qa.tests.Junit4;

import com.qa.factory.PCloudyDriverFactory;
import com.qa.factory.TestableDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qa.utilities.TakeCapture;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import io.testomat.core.annotation.TestId;


public class JunitInventoryTests1 {
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
    private static final String TEXTE_BOUTON_ACTION = "Add to cart";
    private static final By BOUTONS_ACTION = By.cssSelector(".inventory_item .btn_inventory");

    // Expected Nombre Of Products
    private static final int EXPECTED_PRODUCTS_NUMBER = 6;

    // Define Web driver object With PCloudy Driver Factory
    private WebDriver driver;
    PCloudyDriverFactory pCloudyDriverFactory;

    // Add an instance of TakeCapture class
    TakeCapture capture;

    @Rule
    public TestName testName = new TestName();

    //private boolean testPassed = true;

    //@Rule
    //public TestWatcher watcher = new TestWatcher() {
    //    @Override
    //    protected void failed(Throwable e, Description description) {
    //        testPassed = false;
    //    }
    //};

    boolean pcloudy = false;
    boolean LT = false;
    boolean testable = false;

    // Setup Tests
    @Before
    public void setUp() {
        System.out.println("Starting Test Automation Pcloudy...");
        try {
            WebDriverManager.getInstance().setup();
            if (pcloudy) {
                pCloudyDriverFactory = new PCloudyDriverFactory();
                driver = pCloudyDriverFactory.createPcloudyDriver();
            } else if (testable) {
                TestableDriverFactory testableDriverFactory = new TestableDriverFactory();
                driver = testableDriverFactory.createTestableDriver();
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
    //TC07
    @Test
    public void testEachProductHasButtonAddToCart() {
        // @T043d1f7a

        List<WebElement> products = driver.findElements(PRODUCTS_CARDS);
        Assert.assertFalse(products.isEmpty(), "No Products are displayed ! ");
        for (WebElement product : products) {
            String buttonText = product.findElement(BOUTONS_ACTION).getText();
            Assert.assertEquals(buttonText, TEXTE_BOUTON_ACTION, "Action Product Button should display exactly : " + TEXTE_BOUTON_ACTION);
        }
    }

    // TearDown Tests //
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
