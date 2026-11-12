package com.qa.cucumber.stepdefs;

import com.qa.cucumber.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Objects;

public class SwagLabsLoginSteps {

    WebDriver driver = Hooks.driver;

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://www.saucedemo.com/");
    }

    @When("The user enters username {string} and password {string}")
    public void theUserEntersUsernameAndPassword(String user, String pass) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pass);
    }

    @When("clicks the Login button")
    public void clicks_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    }

    @Then("The inventory page is displayed")
    public void the_inventory_page_is_displayed() {
        // Write code here that turns the phrase above into concrete actions
        boolean isInventoryPage = Objects.requireNonNull(driver.getCurrentUrl()).contains("/inventory.html");
        Assert.assertTrue(isInventoryPage);
    }
}
