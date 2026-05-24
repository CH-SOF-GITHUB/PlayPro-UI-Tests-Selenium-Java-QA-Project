package Selenide.cucumber.StepsDefinitions;

import Selenide.UI.PlayPro.Web.Front.Pages.CookiePage;
import Selenide.UI.PlayPro.Web.Front.Pages.LoginPage;
import Selenide.UI.PlayPro.Web.Front.Pages.LogoutPage;
import Selenide.UI.PlayPro.Web.Front.Pages.UserProfilePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginScenario {
    private static final Log log = LogFactory.getLog(LoginScenario.class);
    // define an instances of page objects
    LoginPage loginPage = new LoginPage();
    CookiePage cookiePage = new CookiePage();


    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.openLoginPage();
    }

    @When("I accept cookie")
    public void i_accept_cookie() {
        // Write code here that turns the phrase above into concrete actions
        cookiePage.clickAcceptButton();
    }

    @When("I enter valid email and password")
    public void i_enter_valid_email_and_password() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.setEmail("demotenant3@yopmail.com");
        loginPage.setPassword("Admin1234!");
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        webdriver().shouldHave(url("https://demotenant.playpro.fr/"));
    }
}
