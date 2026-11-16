package com.qa.cucumber.stepdefs;

import com.qa.cucumber.Utility.Base;
import com.qa.cucumber.pages.InventoryPage;
import com.qa.cucumber.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class SwagLabsInventorySteps extends Base {
    LoginPage loginPage = new LoginPage();
    ;
    InventoryPage inventoryPage = new InventoryPage();

    @Given("The user is logged in as {string} and {string}")
    public void theUserIsLoggedInAsAnd(String user, String pass) {
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

    @Then("The page header displays burger menu button and shopping cart icon and app logo {string}")
    public void thePageHeaderDisplaysBurgerMenuButtonAndShoppingCartIconAndAppLogo(String name) {
        // Write code here that turns the phrase above into concrete actions
        boolean burgerMenuBtn = elementFound(inventoryPage.getInventoryBurgerMenuBtn());
        boolean shoppingCart = elementFound(inventoryPage.getInventoryShoppingCart());
        boolean appLogoTitle = elementFound(inventoryPage.getInventoryHeaderAppLogo());
        String appLogoText = getText(inventoryPage.getInventoryHeaderAppLogo());
        Assert.assertTrue(burgerMenuBtn && shoppingCart && appLogoTitle);
        Assert.assertEquals(appLogoText, name);
    }

    @And("The page sub header displays {string} label and Products Sort")
    public void thePageSubHeaderDisplaysLabelAndProductsSort(String title) {
        String subHeaderLabel = getText(inventoryPage.getInventorySubHeaderTitle());
        boolean productsSorter = elementFound(inventoryPage.getInventoryProductsSort());
        Assert.assertEquals(subHeaderLabel, title);
        Assert.assertTrue(productsSorter);
    }

    @And("A footer exists at bottom of page")
    public void aFooterExistsAtBottomOfPage() {
        boolean footerInInventory = elementFound(inventoryPage.getInventoryFooter());
        Assert.assertTrue(footerInInventory);
    }

    @Then("Each product has a non-empty name")
    public void eachProductHasANonEmptyName() {
        List<WebElement> products = getList(inventoryPage.getInventoryProductsItem());
        assert products != null;
        for (WebElement prod : products) {
            String name = prod.findElement(By.className("inventory_item_name")).getText();
            Assert.assertNotNull(name);
            System.out.print(name + ",");
        }
    }

    @And("Each product has a non-empty description")
    public void eachProductHasANonEmptyDescription() {
        List<WebElement> products = getList(inventoryPage.getInventoryProductsItem());
        assert products != null;
        for (WebElement prod : products) {
            String desc = prod.findElement(By.className("inventory_item_desc")).getText();
            Assert.assertNotNull(desc);
            System.out.print(desc + ",");
        }
    }

    @And("Each product has a price")
    public void eachProductHasAPrice() {
        List<WebElement> products = getList(inventoryPage.getInventoryProductsItem());
        assert products != null;
        for (WebElement prod : products) {
            String price = prod.findElement(By.className("inventory_item_price")).getText();
            Assert.assertNotNull(price);
            System.out.print(price + ",");
        }
    }

    @And("Each product has a valid non-broken image asset")
    public void eachProductHasAValidNonBrokenImageAsset() {
        List<WebElement> products = getList(inventoryPage.getInventoryProductsItem());
        assert products != null;
        for (WebElement prod : products) {
            String src = prod.findElement(By.cssSelector(".inventory_item_img img")).getAttribute("src");
            Assert.assertNotNull(src);
        }
    }

    @And("Each product has a {string} button")
    public void eachProductHasAButton(String btnText) {
        List<WebElement> products = getList(inventoryPage.getInventoryProductsItem());
        assert products != null;
        for (WebElement prod : products) {
            String btnActionText = prod.findElement(By.cssSelector(".inventory_item .btn_inventory")).getText();
            Assert.assertEquals(btnActionText, btnText);
        }
    }

    @When("Select {string} from the sort dropdown")
    public void selectFromTheSortDropdown(String dropdownOption) {
        Select sortSelect = new Select(inventoryPage.getInventoryProductsSort());
        sortSelect.selectByVisibleText(dropdownOption);
    }

    @Then("The First Product in List is {string}")
    public void theFirstProductInListIs(String prod) {
        List<WebElement> products = getList(inventoryPage.getInventoryProductsItem());
        assert products != null;
        String prodName = products.get(0).findElement(By.className("inventory_item_name")).getText();
        Assert.assertEquals(prodName, prod);
    }

    @And("The Last Product in List is {string}")
    public void theLastProductInListIs(String prod) {
        List<WebElement> products = getList(inventoryPage.getInventoryProductsItem());
        assert products != null;
        String prodName = products.get(products.size() - 1).findElement(By.className("inventory_item_name")).getText();
        Assert.assertEquals(prodName, prod);
    }

    @When("The user clicks on a product {string}")
    public void theUserClicksOnAProduct(String prodName) throws InterruptedException {
        List<WebElement> products = getList(inventoryPage.getInventoryProductsItem());
        assert products != null;
        for (WebElement prod : products) {
            if (prod.findElement(By.className("inventory_item_name")).getText().equals(prodName)) {
                prod.findElement(By.cssSelector(".inventory_item .btn_inventory")).click();
                break;
            }
        }
        Thread.sleep(1000);
    }

    @Then("The shopping cart counter equal to {int}")
    public void theShoppingCartCounterEqualTo(int count) {
        String cartIconText = getText(inventoryPage.getInventoryShoppingCart());
        int cartIconCount = Integer.parseInt(cartIconText);
        Assert.assertEquals(cartIconCount, count);
    }
}
