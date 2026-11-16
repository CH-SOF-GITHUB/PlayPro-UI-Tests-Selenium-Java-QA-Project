package com.qa.cucumber.pages;
import com.qa.cucumber.Utility.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends Base {

    // Locators
    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryProductItem;

    @FindBy(className = "inventory_list")
    private WebElement productsList;

    @FindBy(className = "inventory_item_name")
    private WebElement inventoryProductName;

    @FindBy(className = "inventory_item_desc")
    private WebElement inventoryProductDesc;

    @FindBy(className = "inventory_item_price")
    private WebElement inventoryProductPrice;

    @FindBy(css = ".inventory_item_img img")
    private WebElement inventoryProductImage;

    @FindBy(css = ".inventory_item .btn_inventory")
    private WebElement inventoryProductBtn;

    @FindBy(className = "active_option")
    private WebElement sortActiveOption;

    @FindBy(className = "product_sort_container")
    private WebElement inventoryProductsSort;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement inventoryBurgerMenuBtn;

    @FindBy(className = "shopping_cart_link")
    private WebElement inventoryShoppingCart;

    @FindBy(className = "app_logo")
    private WebElement inventoryHeaderAppLogo;

    @FindBy(className = "title")
    private WebElement inventorySubHeaderTitle;

    @FindBy(className = "footer")
    private WebElement inventoryFooter;

    // Constructor
    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    // Getter and setter Methods
    public List<WebElement> getInventoryProductsItem() {
        return inventoryProductItem;
    }

    public void setInventoryProductsItem(List<WebElement> inventoryProductItem) {
        this.inventoryProductItem = inventoryProductItem;
    }

    public List<WebElement> getInventoryProductItem() {
        return inventoryProductItem;
    }

    public void setInventoryProductItem(List<WebElement> inventoryProductItem) {
        this.inventoryProductItem = inventoryProductItem;
    }
    //

    //
    public WebElement getProductsList() {
        return productsList;
    }

    public void setProductsList(WebElement productsList) {
        this.productsList = productsList;
    }

    public WebElement getInventoryProductName() {
        return inventoryProductName;
    }

    public void setInventoryProductName(WebElement inventoryProductName) {
        this.inventoryProductName = inventoryProductName;
    }

    public WebElement getInventoryProductDesc() {
        return inventoryProductDesc;
    }

    public void setInventoryProductDesc(WebElement inventoryProductDesc) {
        this.inventoryProductDesc = inventoryProductDesc;
    }

    public WebElement getInventoryProductPrice() {
        return inventoryProductPrice;
    }

    public void setInventoryProductPrice(WebElement inventoryProductPrice) {
        this.inventoryProductPrice = inventoryProductPrice;
    }

    public WebElement getInventoryProductImage() {
        return inventoryProductImage;
    }

    public void setInventoryProductImage(WebElement inventoryProductImage) {
        this.inventoryProductImage = inventoryProductImage;
    }

    public WebElement getInventoryProductBtn() {
        return inventoryProductBtn;
    }

    public void setInventoryProductBtn(WebElement inventoryProductBtn) {
        this.inventoryProductBtn = inventoryProductBtn;
    }

    public WebElement getSortActiveOption() {
        return sortActiveOption;
    }

    public void setSortActiveOption(WebElement sortActiveOption) {
        this.sortActiveOption = sortActiveOption;
    }

    public WebElement getInventoryProductsSort() {
        return inventoryProductsSort;
    }

    public void setInventoryProductsSort(WebElement inventoryProductsSort) {
        this.inventoryProductsSort = inventoryProductsSort;
    }

    public WebElement getInventoryBurgerMenuBtn() {
        return inventoryBurgerMenuBtn;
    }

    public void setInventoryBurgerMenuBtn(WebElement inventoryBurgerMenuBtn) {
        this.inventoryBurgerMenuBtn = inventoryBurgerMenuBtn;
    }

    public WebElement getInventoryShoppingCart() {
        return inventoryShoppingCart;
    }

    public void setInventoryShoppingCart(WebElement inventoryShoppingCart) {
        this.inventoryShoppingCart = inventoryShoppingCart;
    }

    public WebElement getInventoryHeaderAppLogo() {
        return inventoryHeaderAppLogo;
    }

    public void setInventoryHeaderAppLogo(WebElement inventoryHeaderAppLogo) {
        this.inventoryHeaderAppLogo = inventoryHeaderAppLogo;
    }

    public WebElement getInventorySubHeaderTitle() {
        return inventorySubHeaderTitle;
    }

    public void setInventorySubHeaderTitle(WebElement inventorySubHeaderTitle) {
        this.inventorySubHeaderTitle = inventorySubHeaderTitle;
    }

    public WebElement getInventoryFooter() {
        return inventoryFooter;
    }

    public void setInventoryFooter(WebElement inventoryFooter) {
        this.inventoryFooter = inventoryFooter;
    }
}
