package com.qa.Cucumber_Acceptance_Tests.steps;

import com.qa.Cucumber_Acceptance_Tests.context.BrowserSession;
import com.qa.Cucumber_Acceptance_Tests.pages.InventoryPage;
import com.qa.Cucumber_Acceptance_Tests.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class InventorySteps {
    private WebDriver driver() {
        return BrowserSession.getDriver();
    }

    @Given("the standard shopper signs in with {string} and password {string}")
    public void shopperSignsInWithCredentials(String username, String password) {
        new LoginPage(driver()).signIn(username, password);
    }

    @Then("the catalog contains {int} products")
    public void catalogContainsProducts(int expectedCount) {
        InventoryPage inventoryPage = new InventoryPage(driver());
        Assert.assertTrue(inventoryPage.isDisplayed(), "The product catalog should be displayed.");
        Assert.assertEquals(inventoryPage.getProductCount(), expectedCount, "The catalog should contain the expected number of products.");
    }
}
