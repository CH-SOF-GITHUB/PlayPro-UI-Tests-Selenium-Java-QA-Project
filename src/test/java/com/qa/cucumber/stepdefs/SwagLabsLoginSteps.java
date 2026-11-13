package com.qa.cucumber.stepdefs;

import com.qa.cucumber.hooks.Hooks;
import com.qa.cucumber.locators.SwagLabsLocators;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.qa.pages.SwagLabs.SwagLabsActionPage;
import org.testng.Assert;

import java.util.List;
import java.util.Objects;

public class SwagLabsLoginSteps {

    // call web driver object from Hooks
    WebDriver driver = Hooks.driver;

    // create an object from POM pages
    SwagLabsActionPage swagLabsActionPage = new SwagLabsActionPage(driver);

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://www.saucedemo.com/");
    }

    @When("The user enters username {string} and password {string}")
    public void theUserEntersUsernameAndPassword(String user, String pass) {
        // Write code here that turns the phrase above into concrete actions
        swagLabsActionPage.enter(SwagLabsLocators.CHAMP_USERNAME, user);
        swagLabsActionPage.enter(SwagLabsLocators.CHAMP_PASSWORD, pass);
    }

    @When("clicks the Login button")
    public void clicks_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        swagLabsActionPage.enter(SwagLabsLocators.CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
    }

    @Then("The inventory page is displayed")
    public void the_inventory_page_is_displayed() {
        // Write code here that turns the phrase above into concrete actions
        boolean isInventoryPage = Objects.requireNonNull(driver.getCurrentUrl()).contains("/inventory.html");
        Assert.assertTrue(isInventoryPage);
        String isInventoryPageTitle = swagLabsActionPage.getElementText(SwagLabsLocators.INVENTORY_PRODUCTS_TITLE);
        Assertions.assertEquals("Products", isInventoryPageTitle, "The inventory page Title does not match !");
    }

    @Then("The error message {string} is shown")
    public void theErrorMessageIsShown(String errorMsg) {
        // Write code here that turns the phrase above into concrete actions
        String errorMessage = driver.findElement(SwagLabsLocators.ERROR_FIELD).getText();
        Assert.assertEquals(errorMessage, errorMsg, "Error message not shown yet !");
    }

    @Then("The inventory page displays {int} products")
    public void theInventoryPageDisplaysProducts(int nbrOfProds) {
        // Write code here that turns the phrase above into concrete actions
        List<WebElement> products = swagLabsActionPage.getList(SwagLabsLocators._PRODUCT_ITEMS);
        Assertions.assertEquals(products.size(), nbrOfProds, "The inventory page does not display the number exact of products !");
    }
}
