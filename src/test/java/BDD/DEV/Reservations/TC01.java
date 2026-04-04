package BDD.DEV.Reservations;

import BDD.Hooks;
import PageObject.WebCookiesPage;
import PageObject.WebReservationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class TC01 {
    private static final Log log = LogFactory.getLog(TC01.class);
    // define the web driver for class step definitions
    WebDriver driver = Hooks.driver;
    WebCookiesPage webCookiesPage = new WebCookiesPage(driver);
    WebReservationPage webReservationPage = new WebReservationPage(driver);

    @Given("Le client est sur la page d'accueil")
    public void le_client_est_sur_la_page_d_accueil() {
        try {
            driver.get("https://chakertestqa.playpro.fr/");
            Thread.sleep(5000);
            log.info("Le client est sur la page d'accueil");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Given("Le client accepte les cookies")
    public void le_client_accepte_les_cookies() {
        try {
            webCookiesPage.clickAcceptCookiesButton();
            log.info("Le client accepte les cookies");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @When("Le client clique sur le bouton Réserver de la section Nos activités")
    public void leClientCliqueSurLeBoutonReserverDeLaSectionNosActivites() {
        try {
            webReservationPage.clickRéserverLink();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @When("Le client clique sur le bouton Réservations dans la barre de navigation")
    public void leClientCliqueSurLeBoutonReservationsDansLaBarreDeNavigation() {
        try {
            webReservationPage.clickRéservationsLink();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Then("La page Réservations s'affiche")
    public void la_page_réservations_s_affiche() {
        try {
            Thread.sleep(5000);
            String ExpectedUrl = "https://chakertestqa.playpro.fr/discover/reservation";
            String ActualUrl = driver.getCurrentUrl();
            Assert.assertEquals("Client not redirected to reservations page", ExpectedUrl, ActualUrl);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
