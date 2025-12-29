package StepDefinitions.Navigation;

import StepDefinitions.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class NavigateToResto {
    // declare an instance of Web Driver
    WebDriver driver = Hooks.driver;

    @Given("Le client clique sur le bouton {string} de menu liste")
    public void le_client_clique_sur_le_bouton_de_menu_liste(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("il clique sur le lien {string}")
    public void il_clique_sur_le_lien(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("il est redirigé vers la page Resto avec succès")
    public void il_est_redirigé_vers_la_page_resto_avec_succès() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
