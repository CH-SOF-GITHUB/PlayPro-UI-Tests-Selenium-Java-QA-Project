package com.qa.cucumber.locators;

import org.openqa.selenium.By;

public class SwagLabsLocators {

    // base URL
    public static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    public static final By CHAMP_USERNAME = By.id("user-name");
    public static final By CHAMP_PASSWORD = By.id("password");
    public static final By _PRODUCTS_LIST = By.className("inventory_list");
    public static final String USERNAME_VALIDE = "standard_user";
    public static final String PASSWORD_VALIDE = "secret_sauce";
    //
    public static final By ERROR_FIELD = By.cssSelector("[data-test='error']");
    //
    public static final By INVENTORY_PAGE_TITLE = By.className("title");
    public static final By _PRODUCT_ITEMS = By.className("inventory_item");
    public static final By INVENTORY_BOUTON_ACTION = By.cssSelector(".inventory_item .btn_inventory");
    public static final By INVENTORY_PRODUCT_NAME = By.className("inventory_item_name");
    public static final By INVENTORY_PRODUCT_DESC = By.className("inventory_item_desc");
    public static final By INVENTORY_PRODUCT_PRICE = By.className("inventory_item_price");
    public static final By INVENTORY_PRODUCT_IMAGE = By.cssSelector(".inventory_item_img img");


    public SwagLabsLocators() {
    }
}
