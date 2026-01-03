package BDD.ReservationsV3;

import BDD.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GoToReservation {

    // declare web driver to control the browser and execute the steps of the scenario
    WebDriver driver = Hooks.driver;
    // declare wait to use explicit waits
    WebDriverWait wait = Hooks.wait;

    @Given("Client opens the home page")
    public void client_opens_the_home_page() {
        driver.get("https://site.playpro.fr/");
        System.out.println("CUCUMBER BACKGROUND - STEP 1: Client opens the home page\n");
    }

    @And("Client accepts cookies")
    public void client_accepts_cookies() {
        WebElement AccepterBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='c15t-footerSubGroup-HbTp3'] button[class='c15t-button-YKOgW c15t-button-small-n5LJg c15t-button-primary-stroke-TWzjH']")));
        AccepterBtn.click();
        System.out.println("CUCUMBER BACKGROUND - STEP 2: Client accepts cookies\n");
    }

    @Given("Client clicks on the Reservations Button")
    public void client_clicks_on_the_reservations_button() {
        WebElement ReservationsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div[1]/ul/a[6]")));
        ReservationsLink.click();
        System.out.println("CUCUMBER SCENARIO 1 - STEP 1: Client clicks on the Reservations Button\n");
    }

    @Then("Client should be redirected to the reservation page")
    public void client_should_be_redirected_to_the_reservation_page() throws InterruptedException {
        // check the redirection to reservations page
        Thread.sleep(5000);
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://site.playpro.fr/discover/reservation", "Client is not navigated to Reservations page !");
        System.out.println("CUCUMBER SCENARIO 1 - STEP 2: Client redirect to the reservation page successfully\n");
    }

    @And("The reservation page title should be displayed")
    public void the_reservation_page_title_should_be_displayed() {
        String CurrentTitle = driver.getTitle();
        Assert.assertEquals(CurrentTitle, "Réservation", "Title of reservation page does not match !");
        System.out.println("CUCUMBER SCENARIO 1 - STEP 3: The reservation page title is correctly displayed\n");
    }

    @Given("Client enters the reservation page URL directly")
    public void client_enters_the_reservation_page_url_directly() {
        driver.get("https://site.playpro.fr/discover/reservation");
        System.out.println("CUCUMBER SCENARIO 2 - STEP 1: Client enters the reservation page URL directly\n");
    }

    @Then("Client should see the reservation page content")
    public void client_should_see_the_reservation_page_content() throws InterruptedException {
        driver.get("https://site.playpro.fr/");
        // Vérifier que le titre existe sur la home page
        By headerTitleLocator = By.xpath("(//h1[normalize-space()='Réserve ton Padel à Macon'])[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(headerTitleLocator));
        // Naviguer vers la page réservation
        driver.get("https://site.playpro.fr/discover/reservation");
        // wait for 5s to load reservations page
        Thread.sleep(5000);
        // Vérifier que le titre de la home N'EXISTE PLUS
        boolean isHeaderPresent = !driver.findElements(headerTitleLocator).isEmpty();
        Assert.assertFalse(isHeaderPresent, "Header title should NOT be present on reservation page");
        System.out.println("CUCUMBER SCENARIO 2 - STEP 2: Client see the reservation page content correctly\n");
    }

    @And("The home page title should not be displayed")
    public void the_home_page_title_should_not_be_displayed() {
        String CurrentTitle = driver.getTitle();
        Assert.assertEquals(CurrentTitle, "Réservation", "Title of reservation page does not match !");
        System.out.println("CUCUMBER SCENARIO 2 - STEP 3: The reservation page title is correctly displayed\n");
    }
}
