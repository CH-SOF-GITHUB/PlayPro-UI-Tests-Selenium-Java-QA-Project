package BDD.DEV.Reservations.Subscriptions;

import PageObject.Abonnements.WebSubscriptionsPage;
import PageObject.WebReservationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static BDD.Hooks.driver;

public class SubscriptionTC01 {
    // define an instances of POM pages classes
    WebReservationPage webReservationPage = new WebReservationPage(driver);
    WebSubscriptionsPage webSubscriptionsPage = new WebSubscriptionsPage(driver);

    @Given("I click on Offre menu button")
    public void i_click_on_offre_menu_button() {
        try {
            // Write code here that turns the phrase above into concrete actions
            webReservationPage.ClickOffreMenuNavbar();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @When("I click on the Abonnements link")
    public void iClickOnTheAbonnementsLink() {
        try {
            // Write code here that turns the phrase above into concrete actions
            webSubscriptionsPage.clickSeriesLink();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @Then("I should be on the subscription reservation page")
    public void i_should_be_on_the_subscription_reservation_page() throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            Thread.sleep(7000);
            String ActualUrl = driver.getCurrentUrl();
            String ExpectedUrl = "https://demotenant.playpro.fr/discover/serie";
            Assert.assertEquals(ActualUrl, ExpectedUrl, "The user is not on the subscription reservation page");
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }
}
