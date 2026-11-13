package com.qa.cucumber.stepdefs;

import com.qa.cucumber.Utility.Base;
import com.qa.cucumber.locators.SwagLabsLocators;
import com.qa.cucumber.pages.InventoryPage;
import com.qa.cucumber.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SwagLabsInventorySteps extends Base {
    LoginPage loginPage;
    InventoryPage inventoryPage;

    // call web driver object from Hooks
    // WebDriver driver = Hooks.driver;

    // create an object from POM pages
    // SwagLabsActionPage swagLabsActionPage = new SwagLabsActionPage(driver);

    @Given("The user is logged in as {string} and {string}")
    public void theUserIsLoggedInAsAnd(String user, String pass) {
        loginPage = new LoginPage();

        setText(loginPage.getTxtUserName(), user);
        setText(loginPage.getTextPassword(), pass);
        clickBtn(loginPage.getBtnLogin());
    }

    @Then("The inventory page should display {int} products")
    public void theInventoryPageShouldDisplayProducts(int nbrOfProds) {
        // Write code here that turns the phrase above into concrete actions
        inventoryPage = new InventoryPage();
        List<WebElement> products = getList(inventoryPage.getInventoryProductsItem());
        assert products != null;
        Assertions.assertEquals(products.size(), nbrOfProds, "The inventory page does not display the number exact of products !");
    }
}
