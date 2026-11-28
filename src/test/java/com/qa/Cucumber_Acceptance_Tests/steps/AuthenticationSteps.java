package com.qa.Cucumber_Acceptance_Tests.steps;

import com.qa.Cucumber_Acceptance_Tests.context.BrowserSession;
import com.qa.Cucumber_Acceptance_Tests.pages.InventoryPage;
import com.qa.Cucumber_Acceptance_Tests.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AuthenticationSteps {
    private WebDriver driver() {
        return BrowserSession.getDriver();
    }

    @Given("the standard shopper is on the Swag Labs sign-in page")
    public void standardShopperIsOnSignInPage() {
        Assert.assertTrue(new LoginPage(driver()).isDisplayed(), "The Swag Labs sign-in page should be visible.");
    }

    @When("they sign in to their account")
    public void shopperSignsIn() {
        new LoginPage(driver()).signIn("standard_user", "secret_sauce");
    }

    @Then("the product catalog is displayed")
    public void productCatalogIsDisplayed() {
        Assert.assertTrue(new InventoryPage(driver()).isDisplayed(), "The product catalog should be visible after sign-in.");
    }

    @When("they sign in with password {string}")
    public void shopperSignsInWithPassword(String password) {
        new LoginPage(driver()).signIn("standard_user", password);
    }

    @Then("the sign-in error {string} is displayed")
    public void signInErrorIsDisplayed(String expectedMessage) {
        Assert.assertEquals(new LoginPage(driver()).getErrorMessage(), expectedMessage, "The expected sign-in error should be displayed.");
    }

    @When("they sign in with username {string}")
    public void theySignInWithUsername(String username) {
        new LoginPage(driver()).signIn(username, "secret_sauce");
    }
}
