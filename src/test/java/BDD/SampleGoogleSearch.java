package BDD;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SampleGoogleSearch {
    WebDriver driver = Hooks.driver;

    @Given("I open Google")
    public void i_open_google() {
        driver.get("https://www.google.com");
    }

    @When("I search for {string}")
    public void i_search_for(String query) {
        driver.findElement(By.name("q")).sendKeys(query);
        driver.findElement(By.name("btnK")).submit();
    }

    @Then("I should see results")
    public void i_should_see_results() {
        Assert.assertTrue(driver.getTitle().contains("Google"));
        driver.quit();
    }
}
