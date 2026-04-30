package BDD.DEV.Reservations.Foods;

import PageObject.WebFoodPage;
import PageObject.WebReservationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static BDD.Hooks.driver;

public class TC01 {
    // create an instance of WebReservationPage
    WebReservationPage webReservationPage = new WebReservationPage(driver);
    // create an instance of WebFoodPage
    WebFoodPage webFoodPage = new WebFoodPage(driver);

    @Given("je clique sur offre lien en navbar")
    public void jeCliqueSurOffreLienEnNavabr() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        webReservationPage.ClickOffreMenuNavbar();
    }

    @When("je clique sur Resto lien")
    public void jeCliqueSurRestoLien() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        webFoodPage.clickFoodLink();
    }

    @Then("un message d'alerte s'affiche {string}")
    public void un_message_d_alerte_s_affiche(String ExpectedMsg) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        webFoodPage.isRestaurantClosedAlertDisplayed();
        String ActualMsg = webFoodPage.getRestaurantClosedAlertText();
        System.out.println("Restaurant closed alert text: " + ActualMsg);
        Assert.assertEquals(ExpectedMsg, ActualMsg);
    }
}
