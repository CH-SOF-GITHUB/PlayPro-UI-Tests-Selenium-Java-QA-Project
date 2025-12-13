package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.NoSuchElementException;

public class BackgroundLogin {
    // declare an instance of Web Driver
    WebDriver driver = Hooks.driver;

    // This class can be used to define step definitions related to background login steps
    @Given("Le client en page d'accueil")
    public void le_client_en_page_d_accueil() {
        try {
            // STEP 1: go to home page playpro
            driver.get("https://devsite.playpro.fr/");
            System.out.println("Step 1: le client ouvre la page home\n");
            // fermer les cookies
            // wait for 3 seconds to ensure the action is completed
            Thread.sleep(3000);
            // Example: accept cookies button (update locator as needed)
            WebElement Accepter = driver.findElement(By.xpath("//div[@class='c15t-footerSubGroup-HbTp3']//button[@class='c15t-button-YKOgW c15t-button-small-n5LJg c15t-button-primary-stroke-TWzjH'][normalize-space()='Accepter']"));
            Accepter.click();
            System.out.println("***********Cookies est fermé*************\n");
            // wait for 3 seconds to ensure the action is completed
            Thread.sleep(3000);
            // STEP 2: click in button "Se connecter en navbar"
            WebElement BtnSeConnecter1 = driver.findElement(By.xpath("//button[normalize-space()='Se connecter']"));
            BtnSeConnecter1.click();
            System.out.println("Step 2: le client clique sur le bouton 'Se connecter' en navbar\n");
            // wait for 5s
            Thread.sleep(5000);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @When("Le client saisit un email valide")
    public void le_client_saisit_un_email_valide() {
        try {
            // wait for 2s
            Thread.sleep(2000);
            // STEP 3: saisir email valid
            WebElement InputEmail = driver.findElement(By.xpath("//input[@placeholder='Adresse-email']"));
            InputEmail.sendKeys("bchaker3999@yopmail.com");
            System.out.println("Step 3: le client saisit un email valide\n");
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @When("Le client saisit un mot de passe valide")
    public void le_client_saisit_un_mot_de_passe_valide() {
        try {
            // wait for 2s
            Thread.sleep(2000);
            // STEP 4: saisir email valid
            WebElement InputPwd = driver.findElement(By.xpath("//input[@placeholder='Mot de passe']"));
            InputPwd.sendKeys("Admin123!");
            System.out.println("Step 4: le client saisit un email valide\n");
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @When("Le client clique sur le bouton {string}")
    public void le_client_clique_sur_le_bouton(String BtnSeConnecterText) {
        try {
            // wait for 2s
            Thread.sleep(2000);
            // STEP 5: click in button "Se connecter"
            WebElement BtnSeConnecter2 = driver.findElement(By.xpath("//button[@type='submit']"));
            BtnSeConnecter2.click();
            System.out.println("Step 5: le client clique sur le bouton " + BtnSeConnecterText);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @And("Le client attend que la page d'accueil se charge complètement")
    public void leClientAttendQueLaPageDAccueilSeChargeCompletement() throws InterruptedException {
        // wait for 10s to ensure the home page is fully loaded
        Thread.sleep(10000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://devsite.playpro.fr/", "Home page did not load successfully!");
    }
}
