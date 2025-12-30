package BDD.LoginV3;

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

public class SampleLogin {
    // declare the web driver for class step definitions
    WebDriver driver = Hooks.driver;
    // declare explicit wait for web elements
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

    @Given("Client access to login page")
    public void client_access_to_login_page() {
        driver.get("https://site.playpro.fr/connexion");
        System.out.println("CUCUMBER STEP 1: client access to login page");
    }

    @When("Client enter valid email")
    public void client_enter_valid_email() {
        WebElement EmailInputV3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='Adresse email'])[1]")));
        String ActualPlaceholder = EmailInputV3.getAttribute("placeholder");
        Assert.assertEquals("Adresse email", ActualPlaceholder);
        EmailInputV3.clear();
        EmailInputV3.sendKeys("chaker.nehos@yopmail.com");
        System.out.println("CUCUMBER STEP 2: client enter valid email");
    }

    @When("Client enter valid password")
    public void client_enter_valid_password() {
        WebElement PasswordInputV3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='Mot de passe'])[1]")));
        String ActualPlaceholder = PasswordInputV3.getAttribute("placeholder");
        Assert.assertEquals("Mot de passe", ActualPlaceholder);
        PasswordInputV3.clear();
        PasswordInputV3.sendKeys("Admin1234!");
        System.out.println("CUCUMBER STEP 3: client enter valid password");
    }

    @When("Client click in login button")
    public void client_click_in_login_button() {
        WebElement LoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Me connecter'])[1]")));
        String ActualBtnText = LoginButton.getText();
        Assert.assertEquals("Me connecter", ActualBtnText);
        LoginButton.click();
        System.out.println("CUCUMBER STEP 4: client click in login button");
    }

    @Then("Client login successfully and redirects automatically to home page")
    public void client_login_successfully_and_redirects_automatically_to_home_page() {
        try {
            // sleep for 5 s to load demo v3 home page
            Thread.sleep(5000);
            // verify the login passed or not
            String ActualUrlPage = driver.getCurrentUrl();
            Assert.assertEquals("https://site.playpro.fr/", ActualUrlPage);
            Assert.assertFalse(ActualUrlPage.contains("/connexion"));
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }
}
