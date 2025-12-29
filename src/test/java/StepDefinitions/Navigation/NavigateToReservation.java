package StepDefinitions.Navigation;

import StepDefinitions.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class NavigateToReservation {
    // declare an instance of driver
    WebDriver driver = Hooks.driver;

    @When("il clique sur le bouton {string}")
    public void il_clique_sur_le_bouton(String BtnText) {
        try {
            // Le client clique sur le bouton "Réserver"
            WebElement BtnReserver = driver.findElement(By.linkText(BtnText));
            BtnReserver.click();
            System.out.println("Step: le client clique sur le bouton " + BtnText);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Then("il est redirigé vers la page de réservation avec succès")
    public void il_est_redirigé_vers_la_page_de_réservation_avec_succès() {
        try {
            // wait for 5s
            Thread.sleep(5000);
            // check the redirection to reservation page
            Assert.assertEquals(driver.getCurrentUrl(), "https://devsite.playpro.fr/discover/reservation", "Redirection to reservation page failed!");
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

}
