package com.qa.tests.JUnit4;

import com.qa.tests.AbstractJunit4ChromeWebDriverTest;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.qa.pages.SwagLabs.SwagLabsActionPage;
import io.testomat.core.annotation.TestId;


public class Junit4LoginTests extends AbstractJunit4ChromeWebDriverTest {
    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final By MESSAGE_ERREUR = By.cssSelector("[data-test='error']");
    private static final By LISTE_PRODUITS = By.className("inventory_list");

    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    private static final String USERNAME_INVALIDE = "invalid_user";
    private static final String PASSWORD_INVALIDE = "wrong_password";

    private static final String ERREUR_USERNAME_REQUIS = "Epic sadface: Username is required";
    private static final String ERREUR_PASSWORD_REQUIS = "Epic sadface: Password is required";
    private static final String ERREUR_IDENTIFIANTS_INVALIDES = "Epic sadface: Username and password do not match any user in this service";

    // create an instance of POM pages
    private SwagLabsActionPage swagLabsActionPage;

    @Before
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
    }

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

    // Test Case 01 - successful login
    @TestId("8b2a32fe")
    @Test
    public void testWithValidLogin() throws InterruptedException {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys(USERNAME_VALIDE);
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys(PASSWORD_VALIDE);
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // sleep 3s to load page
        Thread.sleep(3000);
        // Assertion 1
        String actualUrl = driver.getCurrentUrl();
        boolean isUrl = actualUrl.contains("/inventory.html");
        Assert.assertTrue(isUrl);
        // Assertion 2
        boolean isProdsList = swagLabsActionPage.isElementDisplayed(LISTE_PRODUITS);
        Assert.assertTrue(isProdsList);
    }

    // Test Case 02 - Empty Username
    @TestId("53dfe69d")
    @Test
    public void testEmptyUsername() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys("");
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys(PASSWORD_VALIDE);
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // Assertions
        String errorMsg = swagLabsActionPage.getElementText(MESSAGE_ERREUR);
        boolean isErrorMsg = errorMsg.equals(ERREUR_USERNAME_REQUIS);
        Assert.assertTrue(isErrorMsg);
    }

    // Test Case 03 - Empty Password
    @TestId("e937d375")
    @Test
    public void testEmptyPassword() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys(USERNAME_VALIDE);
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys("");
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // Assertions
        String errorMsg = swagLabsActionPage.getElementText(MESSAGE_ERREUR);
        boolean isErrorMsg = errorMsg.equals(ERREUR_PASSWORD_REQUIS);
        Assert.assertTrue(isErrorMsg);
    }

    // Test Case 04 - Username invalide
    @TestId("e760af30")
    @Test
    public void testInvalidUsername() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys(USERNAME_INVALIDE);
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys(PASSWORD_VALIDE);
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // Assertions
        String errorMsg = swagLabsActionPage.getElementText(MESSAGE_ERREUR);
        boolean isErrorMsg = errorMsg.equals(ERREUR_IDENTIFIANTS_INVALIDES);
        Assert.assertTrue(isErrorMsg);
    }

    // Test Case 05 - Password invalide
    @TestId("b864769e")
    @Test
    public void testInvalidPassword() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys(USERNAME_VALIDE);
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys(PASSWORD_INVALIDE);
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // Assertions
        String errorMsg = swagLabsActionPage.getElementText(MESSAGE_ERREUR);
        boolean isErrorMsg = errorMsg.equals(ERREUR_IDENTIFIANTS_INVALIDES);
        Assert.assertTrue(isErrorMsg);
    }

    // Test Case 06 - Empty Username and Password
    @TestId("c29c0b5f")
    @Test
    public void testEmptyCredentials() {
        driver.get(URL_LOGIN);
        driver.findElement(CHAMP_USERNAME).click();
        driver.findElement(CHAMP_USERNAME).clear();
        driver.findElement(CHAMP_USERNAME).sendKeys("");
        driver.findElement(CHAMP_PASSWORD).clear();
        driver.findElement(CHAMP_PASSWORD).sendKeys("");
        driver.findElement(CHAMP_PASSWORD).sendKeys(Keys.ENTER);
        // Assertions
        String errorMsg = swagLabsActionPage.getElementText(MESSAGE_ERREUR);
        boolean isErrorMsg = errorMsg.equals(ERREUR_USERNAME_REQUIS);
        Assert.assertTrue(isErrorMsg);
    }

}
