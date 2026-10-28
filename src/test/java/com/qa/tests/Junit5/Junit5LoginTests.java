package com.qa.tests.Junit5;

import io.github.bonigarcia.seljup.Browser;
import io.github.bonigarcia.seljup.EnabledIfBrowserAvailable;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qa.utilities.TakeCapture;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;


public class Junit5LoginTests {
    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final By BOUTON_LOGIN = By.id("login-button");
    private static final By MESSAGE_ERREUR = By.cssSelector("[data-test='error']");
    private static final By LISTE_PRODUITS = By.className("inventory_list");

    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    private static final String USERNAME_INVALIDE = "invalid_user";
    private static final String PASSWORD_INVALIDE = "wrong_password";

    private static final String ERREUR_USERNAME_REQUIS = "Epic sadface: Username is required";
    private static final String ERREUR_PASSWORD_REQUIS = "Epic sadface: Password is required";
    private static final String ERREUR_IDENTIFIANTS_INVALIDES = "Epic sadface: Username and password do not match any user in this service";

    // Define Web driver object With PCloudy Driver Factory
    private WebDriver driver;

    // Add an instance of TakeCapture class
    TakeCapture capture;

    // SETUP
    @BeforeEach
    public void setUp() {
        System.out.println("Starting Test Automation ...");
        System.out.println("=======Running @BeforeEach for JUnit 5=======");
        try {
            WebDriverManager.getInstance().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().window().maximize();
            capture = new TakeCapture(driver);
        } catch (WebDriverException e) {
            throw new WebDriverException("Web Driver not Initialized: " + e.getMessage());
        }
    }

    // TESTS //
    // Test Case 01 - successful login
    @Test
    public void testValidLogin() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys(USERNAME_VALIDE);
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys(PASSWORD_VALIDE);
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // Assertion 1
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertNotNull(actualUrl);
        boolean isUrl = actualUrl.contains("/inventory.html");
        assertThat(isUrl).isTrue();
        //assertTrue(isUrl);
        // Assertion 2
        boolean isProdsList = isElementDisplayed(LISTE_PRODUITS);
        //assertTrue(isProdsList);
        assertThat(isProdsList).isTrue();
    }

    // TEARDOWN
    @AfterEach
    public void tearDown(TestInfo testInfo) throws IOException {
        System.out.println("=======Running @AfterEach for JUnit 5=======");
        if (driver != null) {
            try {
                System.out.println("I'm Executed");
                capture.takeScreenshot(testInfo.getDisplayName());
            } finally {
                driver.quit();
            }
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

    public String getElementText(By by) {
        try {
            return driver.findElement(by).getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("cannot get Text of this element:  " + e.getMessage());
        }
    }
}
