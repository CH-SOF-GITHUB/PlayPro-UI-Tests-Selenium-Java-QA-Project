package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.qa.pages.SwagLabs.SwagLabsActionPage;

import java.util.List;
import java.util.Objects;

public class Junit5DynamicCatalogSliderTests extends AbstractChromeWebDriverTest {

    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    //
    private static final By _PRODUCT_ITEMS = By.className("inventory_item");
    private static final By INVENTORY_PRODUCT_NAME = By.className("inventory_item_name");
    private static final By INVENTORY_PRODUCT_PRICE = By.className("inventory_item_price");
    private static final By INVENTORY_BOUTON_ACTION = By.cssSelector(".inventory_item .btn_inventory");
    //
    private static final By INVENTORY_PRODUCT_SORT = By.className("product_sort_container");
    // burger menu button
    private static final By BURGER_MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By BURGER_DYNAMIC_CATALOG = By.id("dynamic_catalog_sidebar_link");
    //
    private static final By BURGER_DYNAMIC_CATALOG_SPINNER = By.id("dynamic_catalog_spinner_link");
    private static final By BURGER_SPINNER = By.className("dynamic_catalog_spinner");
    private static final By BURGER_PRODUCT_CARDS_CONTAINER = By.className("dynamic_catalog_spinner_container");
    private static final By BURGER_DYNAMIC_CATALOG_CARD = By.className("dynamic_catalog_card");
    //
    private static final By BURGER_DYNAMIC_CATALOG_SLIDER = By.id("dynamic_catalog_slider_link");
    private static final By BURGER_DYNAMIC_CATALOG_SLIDER_CONTAINER = By.className("dynamic_catalog_slider_container");
    //
    private static final By BURGER_DYNAMIC_CATALOG_CARD_NAME = By.className("dynamic_catalog_card_name");
    private static final By BURGER_DYNAMIC_CATALOG_CARD_PRICE = By.className("dynamic_catalog_card_price");
    //
    private static final By BURGER_MENU_ALL_ITEMS = By.id("inventory_sidebar_link");
    private static final By BURGER_MENU_ABOUT = By.id("about_sidebar_link");
    private static final By BURGER_MENU_LOGOUT = By.id("logout_sidebar_link");
    private static final By BURGER_MENU_RESET_APP_STATE = By.id("reset_sidebar_link");
    private static final By BURGER_X_BUTTON = By.id("react-burger-cross-btn");
    private static final By BM_MENU_WRAP = By.className("bm-menu-wrap");

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
        // 1. Log in to Swag Labs
        login();
    }

    // TESTS //
    @DisplayName("Menu Navigation To Dynamic Catalog Slider")
    @EnabledOnOs(OS.WINDOWS)
    @RepeatedTest(2)
    public void testMenuNavigationToDynamicCatalogSlider() {
        // Open burger menu (#react-burger-menu-btn).
        driver.findElement(BURGER_MENU_BUTTON).click();
        // Expand Dynamic Catalog menu if collapsed.
        String isExpanded = driver.findElement(BURGER_DYNAMIC_CATALOG).getAttribute("aria-expanded");
        if (Objects.equals(isExpanded, "false") || isExpanded == null) {
            driver.findElement(BURGER_DYNAMIC_CATALOG).click();
        }
        // Click Slider option.
        driver.findElement(BURGER_DYNAMIC_CATALOG_SLIDER).click();
        // Assert page URL navigates to /dynamic-catalog-slider.html.
        boolean isDynamicCatalogSlider = swagLabsActionPage.isUrlOK(BASE_URL, "/dynamic-catalog-slider.html");
        Assertions.assertTrue(isDynamicCatalogSlider);
        // Assert slider container is displayed.
        boolean sliderContainerIsDisplayed = swagLabsActionPage.isElementDisplayed(BURGER_DYNAMIC_CATALOG_SLIDER_CONTAINER);
        Assertions.assertTrue(sliderContainerIsDisplayed);
    }

    // Actions
    // sample login method
    private void login() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
    }

    private List<WebElement> getProducts() {
        return swagLabsActionPage.getList(_PRODUCT_ITEMS);
    }

    private List<WebElement> getDynamicCatalogProducts() {
        return swagLabsActionPage.getList(BURGER_DYNAMIC_CATALOG_CARD);
    }

}
