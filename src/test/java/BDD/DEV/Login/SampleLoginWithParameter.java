package BDD.DEV.Login;

import BDD.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SampleLoginWithParameter {
    // declare the web driver for class step definitions
    WebDriver driver = Hooks.driver;
    // declare explicit wait for web elements
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

    @Given("Client access to connexion page")
    public void client_access_to_connexion_page() {
        driver.get("https://chaker-qa-playpro.playpro.fr/connexion");
        System.out.println("CUCUMBER STEP 1: client access to login page");
    }

    @When("Client type valid email {string}")
    public void client_type_valid_email(String email) {
        WebElement EmailInputV3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='Adresse email'])[1]")));
        String ActualPlaceholder = EmailInputV3.getAttribute("placeholder");
        Assert.assertEquals("Adresse email", ActualPlaceholder);
        EmailInputV3.clear();
        EmailInputV3.sendKeys(email);
        System.out.println("CUCUMBER STEP 2: client enter valid email");
    }

    @When("Client type valid password {string}")
    public void client_type_valid_password(String pwd) {
        WebElement PasswordInputV3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='Mot de passe'])[1]")));
        String ActualPlaceholder = PasswordInputV3.getAttribute("placeholder");
        Assert.assertEquals("Mot de passe", ActualPlaceholder);
        PasswordInputV3.clear();
        PasswordInputV3.sendKeys(pwd);
        System.out.println("CUCUMBER STEP 3: client enter valid password");
    }

    @When("Client clicks in login button")
    public void client_clicks_in_login_button() {
        WebElement LoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Me connecter'])[1]")));
        String ActualBtnText = LoginButton.getText();
        Assert.assertEquals("Me connecter", ActualBtnText);
        LoginButton.click();
        System.out.println("CUCUMBER STEP 4: client click in login button");
    }

    @Then("Client login successfully and go to home page")
    public void client_login_successfully_and_go_to_home_page() {
        try {
            // sleep for 5 s to load demo v3 home page
            Thread.sleep(5000);
            // verify the login passed or not
            String ActualUrlPage = driver.getCurrentUrl();
            String ActualTitle = driver.getTitle();
            Assert.assertEquals("https://chaker-qa-playpro.playpro.fr/", ActualUrlPage);
            Assert.assertEquals("Accueil", ActualTitle);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }
}
