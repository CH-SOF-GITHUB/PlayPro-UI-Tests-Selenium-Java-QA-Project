package com.qa.tests.PlayPro.DEV;

import com.qa.pages.WebCookiesPage;
import com.qa.pages.WebLoginPage;
import org.testng.annotations.BeforeMethod;

/*
abstract empêche l'instanciation directe et signale l'intention « uniquement pour l'héritage ».
TestNG ignore les classes abstraites pour la découverte d'exécutions, donc on évite que la classe soit traitée comme une suite de tests autonome.
Les classes concrètes (vos tests) étendent cette classe pour bénéficier du @BeforeMethod d'auto-login sans dupliquer le code.
Si vous préférez ne pas utiliser abstract, on peut aussi :
rendre le constructeur protected pour limiter l'instanciation externe, ou
harder la classe non-abstraite mais ne pas lui ajouter de @Test (TestNG n'exécutera rien tant qu'il n'y a pas de méthodes @Test dedans).
La solution abstract reste la plus claire et sûre pour une classe utilitaire de setup partagé.
 */

public abstract class LoggedBaseTest extends BaseTest {
    // Define email and password for login
    protected final String user = "chakerqa-client@yopmail.com";
    protected final String pass = "Admin1234!";
    // define an instance of a specific page object
    protected WebCookiesPage webCookiesPage = null;
    protected WebLoginPage webLoginPage = null;


    @BeforeMethod
    public void loginBeforeEachTest() throws InterruptedException {
        webCookiesPage = new WebCookiesPage(driver);
        webLoginPage = new WebLoginPage(driver);
        webCookiesPage.clickAcceptCookiesButton();
        webLoginPage.EnterEmail(user);
        webLoginPage.EnterPassword(pass);
        webLoginPage.ClickLoginButton();

        // small wait to ensure the login process is completed before proceeding with the tests
        Thread.sleep(1500);
    }
}
