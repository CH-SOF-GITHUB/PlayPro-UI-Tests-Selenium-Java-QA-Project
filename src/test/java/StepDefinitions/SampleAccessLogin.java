package StepDefinitions;


import config.ConfigTBCucumber;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;


public class SampleAccessLogin {
    // declare an instance of WebDriver
    WebDriver driver = Hooks.driver;

    // Les méthodes java des étapes Gherkin
    @Given("client est dans la page d'accueil")
    public void client_est_dans_la_page_d_accueil() throws InterruptedException {
        // Open the playpro dev site web
        driver.get("https://devsite.playpro.fr/");
        Thread.sleep(5000);
    }

    @When("client clique sur le bouton Se connecter en navbar")
    public void client_clique_sur_le_bouton_se_connecter_en_navbar() {
        // click in button "Se connecter en navbar"
        WebElement BtnSeConnecter1 = driver.findElement(By.xpath("//button[normalize-space()='Se connecter']"));
        BtnSeConnecter1.click();
    }

    @And("client attend {int}s pour le chargement de la page de connexion")
    public void clientAttendSPourLeChargementDeLaPageDeConnexion(int time) throws InterruptedException {
        Thread.sleep(time);
    }

    @Then("client est redirigé vers la page de connexion")
    public void client_est_redirigé_vers_la_page_de_connexion() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://devsite.playpro.fr/connexion");
    }
}
