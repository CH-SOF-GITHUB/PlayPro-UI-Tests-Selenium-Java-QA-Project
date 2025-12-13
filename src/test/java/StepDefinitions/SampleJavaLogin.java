package StepDefinitions;

import config.ConfigTBCucumber;
import config.ConfigTestingBot;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;

public class SampleJavaLogin extends ConfigTestingBot {
    // declare an instance of WebDriver
    WebDriver driver = Hooks.driver;

    // Les méthodes Java qui sont liées aux étapes des sénarios écrits en Gherkin
    @Given("Je suis sur le site playpro dev")
    public void je_suis_sur_le_site_playpro_dev() {
        // Open the playpro dev site web
        driver.get("https://devsite.playpro.fr/");
    }

    @When("Je clique sur le bouton Se connecter en navbar")
    public void jeCliqueSurLeBoutonSeConnecterEnNavbar() throws InterruptedException {
        // wait for 5s to load page login
        Thread.sleep(5000);
        // click in button "Se connecter en navbar"
        WebElement BtnSeConnecter1 = driver.findElement(By.xpath("//button[normalize-space()='Se connecter']"));
        BtnSeConnecter1.click();
    }

    @Then("Je suis redirigé vers la page de connexion")
    public void je_suis_redirigé_vers_la_page_de_connexion() throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://devsite.playpro.fr/connexion");
    }

    @When("Je saisis un email {string}")
    public void je_saisis_un_email(String email) {
        WebElement InputEmail = driver.findElement(By.xpath("//input[@placeholder='Adresse-email']"));
        InputEmail.sendKeys("bchaker390@yopmail.com");
    }

    @When("Je saisis  un mot de passe {string}")
    public void je_saisis_un_mot_de_passe(String pwd) {
        WebElement InputPwd = driver.findElement(By.xpath("//input[@placeholder='Mot de passe']"));
        InputPwd.sendKeys("Admin123!");
    }

    @When("Je clique sur le bouton Se connecter")
    public void je_clique_sur_le_bouton_se_connecter() {
        // click in button "Se connecter"
        WebElement BtnSeConnecter2 = driver.findElement(By.xpath("//button[@type='submit']"));
        BtnSeConnecter2.click();
    }

    @Then("Je suis redirigé vers la page d'accueil")
    public void je_suis_redirigé_vers_la_page_d_accueil() throws InterruptedException {
        // wait for 5s to load the homepage
        Thread.sleep(5000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://devsite.playpro.fr/");
    }

    @Then("Je vois le message {string} sur le bouton de profile")
    public void je_vois_le_message_sur_le_bouton_de_profile(String ExpectTextBienvenue) {
        WebElement btnProfile = driver.findElement(By.cssSelector("button[class='xl:px-4 px-2 py-2 bg-secondary rounded-xl font-medium xl:text-base lg:text-xs xl:leading-[1.171rem] leading-[0.875rem] text-primary capitalize']"));
        // Vérifier le texte
        String LabelBtnProfile = btnProfile.getText();
        Assert.assertEquals(LabelBtnProfile, ExpectTextBienvenue, "Le bouton 'Bienvenue' n'est pas affiché ou le texte est incorrect");
    }

    @And("je prends une image d'écran nommée pour le test réussi")
    public void jePrendsUneImageDEcranNommeePourLeTestReussi() throws IOException {
        File srcFile = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcFile, new File("src/test/java/StepDefinitions/Screenshots/BDDValidLogin.png"));
    }
}
