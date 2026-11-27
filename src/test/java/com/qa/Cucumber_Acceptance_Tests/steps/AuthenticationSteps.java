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
}
