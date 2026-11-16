package com.qa.cucumber.stepdefs;

import com.qa.cucumber.Utility.Base;
import com.qa.cucumber.Utility.ExcelReader;
import com.qa.cucumber.pages.InventoryPage;
import com.qa.cucumber.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SwagLabsLoginSteps extends Base {

    // define object of pom pages
    LoginPage loginPage = new LoginPage();
    InventoryPage inventoryPage = new InventoryPage();

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        String LoginPageTitle = getText(loginPage.getLoginContainerLogo());
        Assert.assertEquals(LoginPageTitle, "Swag Labs");
    }

    @When("The user enters username {string} and password {string}")
    public void theUserEntersUsernameAndPassword(String user, String pass) {
        // Write code here that turns the phrase above into concrete actions
        setText(loginPage.getTxtUserName(), user);
        setText(loginPage.getTextPassword(), pass);
    }

    @When("clicks the Login button")
    public void clicks_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        clickBtn(loginPage.getBtnLogin());
    }

    @Then("The inventory page is displayed")
    public void the_inventory_page_is_displayed() {
        // Write code here that turns the phrase above into concrete actions
        boolean isInventoryPage = Objects.requireNonNull(driver.getCurrentUrl()).contains("/inventory.html");
        Assert.assertTrue(isInventoryPage);
        String isInventoryPageTitle = getText(inventoryPage.getInventorySubHeaderTitle());
        Assert.assertEquals(isInventoryPageTitle, "Products", "The inventory page Title does not match !");
    }

    @Then("The error message {string} is shown")
    public void theErrorMessageIsShown(String errorMsg) {
        // Write code here that turns the phrase above into concrete actions
        String errorMessage = getText(loginPage.getErrorMsg());
        Assert.assertEquals(errorMessage, errorMsg, "Error message not shown yet !");
    }

    @Then("The inventory page displays {int} products")
    public void theInventoryPageDisplaysProducts(int nbrOfProds) {
        // Write code here that turns the phrase above into concrete actions
        List<WebElement> products = getList(inventoryPage.getInventoryProductsItem());
        assert products != null;
        Assert.assertEquals(products.size(), nbrOfProds, "The inventory page does not display the number exact of products !");
    }

    @When("User enters and click login button Table Method")
    public void userEntersAndClickLoginButtonTableMethod(DataTable data) {
        // cucumber version 7
        // DataTable concept
        List<List<String>> dataList = data.asLists(String.class);
        // Input the data based on index
        setText(loginPage.getTxtUserName(), dataList.get(0).get(0));
        setText(loginPage.getTextPassword(), dataList.get(0).get(1));
        clickBtn(loginPage.getBtnLogin());
    }

    @When("User enters and click login button Map Method")
    public void userEntersAndClickLoginButtonMapMethod(DataTable data) {
        List<Map<String, String>> dataMap = data.asMaps(String.class, String.class);
        setText(loginPage.getTxtUserName(), dataMap.get(4).get("userName"));
        setText(loginPage.getTextPassword(), dataMap.get(4).get("Password"));
        clickBtn(loginPage.getBtnLogin());
    }

    @When("User enters and click login button Random Alphanumeric")
    public void userEntersAndClickLoginButtonRandomAlphanumeric(DataTable data) {
        String s = RandomStringUtils.randomAlphabetic(4);

        List<Map<String, String>> dataMap = data.asMaps(String.class, String.class);
        setText(loginPage.getTxtUserName(), dataMap.get(4).get("userName") + s);
        setText(loginPage.getTextPassword(), dataMap.get(4).get("Password") + s);
        clickBtn(loginPage.getBtnLogin());
    }

    @When("The user enters sheetname {string} and rowNumber {int}")
    public void theUserEntersSheetnameAndRowNumber(String sheetName, int rowNumber) throws IOException {
        ExcelReader excelReader = new ExcelReader();

        String ExcelPath = "src/test/resources/testdata/SwagLabsLogin.xlsx";
        List<Map<String, String>> testdata = excelReader.getData(ExcelPath, sheetName);
        String username = testdata.get(rowNumber).get("username");
        String password = testdata.get(rowNumber).get("password");
        System.out.println("User Enter username as \"" + username + "\"and password as \"" + password + "\"");
        setText(loginPage.getTxtUserName(), username);
        setText(loginPage.getTextPassword(), password);
    }
}
