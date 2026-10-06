package com.qa.cucumber.bdd.EVENTHUB;

import com.qa.cucumber.bdd.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class LoginInvalidFormatEmailSteps {

    // Initialize the WebDriver and Page Objects
    WebDriver driver = Hooks.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("User is on EventHUB login page")
    public void user_is_on_event_hub_login_page() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.openLoginPage();
    }

    @When("User enters a invalid email {string}")
    public void user_enters_a_invalid_email(String email) {
        // Write code here that turns the phrase above into concrete actions
        loginPage.enterCredentials(email, null);
    }

    @When("User enters a valid password {string}")
    public void user_enters_a_valid_password(String password) {
        // Write code here that turns the phrase above into concrete actions
        loginPage.enterCredentials(null, password);
    }

    @When("User clicks on SignIn Button")
    public void user_clicks_on_sign_in_button() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.clickSignIn();
    }

    @Then("Email Validation error message should display below Email field")
    public void email_validation_error_message_should_display_below_email_field() {
        loginPage.checkInvalidFormatEmailErrorMessageByText("Enter a valid email");
    }
}
