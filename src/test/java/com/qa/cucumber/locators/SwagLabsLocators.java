package com.qa.cucumber.locators;

import org.openqa.selenium.By;

public class SwagLabsLocators {

    // base URL
    public static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    public static final By CHAMP_USERNAME = By.id("user-name");
    public static final By CHAMP_PASSWORD = By.id("password");
    public static final By LISTE_PRODUITS = By.className("inventory_list");
    public static final String USERNAME_VALIDE = "standard_user";
    public static final String PASSWORD_VALIDE = "secret_sauce";
    //
    public static final By ERROR_FIELD = By.cssSelector("[data-test='error']");
    //
    public static final By INVENTORY_PRODUCTS_TITLE = By.className("title");
    public static final By _PRODUCT_ITEMS = By.className("inventory_item");

    public SwagLabsLocators() {
    }
}
