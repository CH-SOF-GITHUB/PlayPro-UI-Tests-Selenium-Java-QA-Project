package com.qa.cucumber.bdd.EVENTHUB;

import com.qa.cucumber.bdd.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.qa.utilities.ExtentManager;

public class LoginSteps {
    // Define the WebDriver instance and the page objects
    WebDriver driver = Hooks.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);


    @Given("I am on the EventHub login page")
    public void i_am_on_the_event_hub_login_page() {
        // Write code here that turns the phrase above into concrete actions
        ExtentManager.logStep("EventHUB - CUCUMBER - Open the login page");
        loginPage.openLoginPage();
    }

    @When("I enter valid login credentials")
    public void i_enter_valid_login_credentials() {
        ExtentManager.logStep("EventHUB - CUCUMBER - Enter login credentials");
        loginPage.enterCredentials("bchaker28@yahoo.com", "Q5n@j!i!QnZQmYm");
    }

    @When("I click the Sign In button")
    public void i_click_the_sign_in_button() {
        ExtentManager.logStep("EventHUB - CUCUMBER - Click the Sign In button");
        loginPage.clickSignIn();
    }

    @Then("I should be successfully logged in")
    public void i_should_be_successfully_logged_in() {
        ExtentManager.logStep("EventHUB - CUCUMBER - Check if user is logged in");
        // Write code here that turns the phrase above into concrete actions
        // homePage.checkHomePageTitleByText("Discover & Book Amazing Events");
        homePage.checkHomePageSubTitleByText("From tech conferences to live concerts, sports events to cultural festivals — find experiences that inspire you.");
        homePage.checkFeaturedEventsSectionTitle("Featured Events");
    }
}
