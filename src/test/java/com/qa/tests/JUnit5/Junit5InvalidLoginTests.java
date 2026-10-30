package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.qa.pages.SwagLabs.SwagLabsActionPage;
import io.testomat.core.annotation.TestId;

public class Junit5InvalidLoginTests extends AbstractChromeWebDriverTest {

    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";

    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final By MESSAGE_ERREUR = By.cssSelector("[data-test='error']");

    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    private static final String USERNAME_INVALIDE = "invalid_user";
    private static final String PASSWORD_INVALIDE = "wrong_password";

    private static final String ERREUR_USERNAME_REQUIS = "Epic sadface: Username is required";
    private static final String ERREUR_PASSWORD_REQUIS = "Epic sadface: Password is required";
    private static final String ERREUR_IDENTIFIANTS_INVALIDES = "Epic sadface: Username and password do not match any user in this service";

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
    }

    //TC01
    @TestId("449884f7")
    @Test
    public void testEmptyUsername() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, "");
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
        // Assertions
        boolean compareError = swagLabsActionPage.compareElement(MESSAGE_ERREUR, ERREUR_USERNAME_REQUIS);
        Assertions.assertTrue(compareError);
    }

    // TC02
    @TestId("7a9d9a09")
    @Test
    public void testEmptyPassword() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, "");
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
        // Assertions
        boolean compareError = swagLabsActionPage.compareElement(MESSAGE_ERREUR, ERREUR_PASSWORD_REQUIS);
        Assertions.assertTrue(compareError);
    }

    //TC03
    @TestId("1530fdf5")
    @Test
    public void testInvalidUsername() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_INVALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
        // Assertions
        boolean compareError = swagLabsActionPage.compareElement(MESSAGE_ERREUR, ERREUR_IDENTIFIANTS_INVALIDES);
        Assertions.assertTrue(compareError);
    }

    //TC04
    @TestId("cb066d91")
    @Test
    public void testInvalidPassword() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_INVALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
        // Assertions
        boolean compareError = swagLabsActionPage.compareElement(MESSAGE_ERREUR, ERREUR_IDENTIFIANTS_INVALIDES);
        Assertions.assertTrue(compareError);
    }

    //TC05
    @TestId("2d8da855")
    @Test
    public void testEmptyCredentials() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, "");
        swagLabsActionPage.enter(CHAMP_PASSWORD, "");
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
        // Assertions
        boolean compareError = swagLabsActionPage.compareElement(MESSAGE_ERREUR, ERREUR_USERNAME_REQUIS);
        Assertions.assertTrue(compareError);
    }

}
