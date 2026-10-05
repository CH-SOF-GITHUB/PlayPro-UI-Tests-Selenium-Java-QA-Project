package com.qa.cucumber.bdd.EVENTHUB;

import com.qa.cucumber.bdd.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    WebDriver driver = Hooks.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);


    @Given("I am on the EventHub login page")
    public void i_am_on_the_event_hub_login_page() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.openLoginPage();
    }

    @When("I enter valid login credentials")
    public void i_enter_valid_login_credentials() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.enterCredentials("bchaker28@yahoo.com", "Q5n@j!i!QnZQmYm");
    }

    @When("I click the Sign In button")
    public void i_click_the_sign_in_button() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.clickSignIn();
    }

    @Then("I should be successfully logged in")
    public void i_should_be_successfully_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        // homePage.checkHomePageTitleByText("Discover & Book Amazing Events");
        homePage.checkHomePageSubTitleByText("From tech conferences to live concerts, sports events to cultural festivals — find experiences that inspire you.");
        homePage.checkFeaturedEventsSectionTitle("Featured Events");
    }
}
