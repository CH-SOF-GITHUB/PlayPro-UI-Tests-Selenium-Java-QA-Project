package com.qa.tests.JUnit5;

import com.qa.tests.AbstractChromeWebDriverTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.qa.pages.SwagLabs.SwagLabsActionPage;

import java.util.Arrays;
import java.util.List;

import io.testomat.core.annotation.TestId;

public class Junit5Inventory1Tests extends AbstractChromeWebDriverTest {

    // base URL
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    // Locators
    private static final By CHAMP_USERNAME = By.id("user-name");
    private static final By CHAMP_PASSWORD = By.id("password");
    private static final String USERNAME_VALIDE = "standard_user";
    private static final String PASSWORD_VALIDE = "secret_sauce";
    private static final By _PRODUCT_ITEMS = By.className("inventory_item");
    //
    private static final By INVENTORY_SUB_HEADER_TITLE = By.className("title");
    private static final By INVENTORY_PRODUCT_DESCRIPTION = By.className("inventory_item_desc");
    private static final By INVENTORY_PRODUCT_NAME = By.className("inventory_item_name");
    private static final By INVENTORY_PRODUCT_PRICE = By.className("inventory_item_price");
    private static final By INVENTORY_PRODUCT_IMAGE = By.cssSelector(".inventory_item_img img");
    private static final By INVENTORY_BOUTON_ACTION = By.cssSelector(".inventory_item .btn_inventory");
    //
    private static final By PRODUCT_SORT_ACTIVE_OPTION = By.className("active_option");
    private static final By PRODUCT_SORT_CONTAINER = By.className("product_sort_container");

    // burger menu button
    private static final By BURGER_MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By BURGER_MENU_ALL_ITEMS = By.id("inventory_sidebar_link");
    private static final By BURGER_MENU_ABOUT = By.id("about_sidebar_link");
    private static final By BURGER_MENU_LOGOUT = By.id("logout_sidebar_link");
    private static final By BURGER_MENU_RESET_APP_STATE = By.id("reset_sidebar_link");
    private static final By BURGER_X_BUTTON = By.id("react-burger-cross-btn");
    private static final By BM_MENU_WRAP = By.className("bm-menu-wrap");

    // shopping cart icon
    private static final By SHOPPING_CART_ICON = By.id("shopping_cart_container");
    private static final By BOUTONS_ACTION = By.cssSelector(".inventory_item .btn_inventory");
    private static final By CART_BADGE = By.className("shopping_cart_badge");
    private static final By CART_LINK = By.className("shopping_cart_link");
    //
    private static final By SauceLabsBackpack = By.xpath("//div[normalize-space()='Sauce Labs Backpack']");
    private static final By PRODUCT_DETAILS_NAME = By.className("inventory_details_name");
    private static final By PRODUCT_DETAILS_PRICE = By.className("inventory_details_price");
    //
    private static final By BACK_TO_PRODUCTS = By.id("back-to-products");
    //
    private static final By SauceLabsBikeLightImg = By.id("item_0_img_link");
    //
    private static final By CART_TABLE_ITEM = By.className("cart_item");
    //
    private static final By INVENTORY_FOOTER = By.className("footer");
    private static final By TWITTER_X_LINK = By.className("social_x");
    private static final By FACEBOOK_LINK = By.className("social_facebook");
    private static final By LINKEDIN_LINK = By.className("social_linkedin");
    private static final By INVENTORY_FOOTER_COPY = By.className("footer_copy");
    //
    private static final By MESSAGE_ERREUR = By.cssSelector("[data-test='error']");
    private static final String ERREUR_FORBIDEN_LOGIN = "Epic sadface: You can only access '/inventory.html' when you are logged in.";

    // create an instance of POM pages
    SwagLabsActionPage swagLabsActionPage;

    boolean isRemoveBtnText;
    boolean isAddToCartBtnText;
    boolean isCartEmpty;

    Object[] windowHandles;

    @BeforeEach
    public void setupPages() {
        swagLabsActionPage = new SwagLabsActionPage(driver);
        // 1. login
        login();
    }

    /*
    1. Page load and UI element presence (smoke)
    * */
    // TC-INV-01: Verify default inventory page layout and headers
    @TestId("2107a6e3")
    @Tag("critical")
    @Test
    public void testVerifyDefaultInventoryPageLayoutAndHeaders() {
        // 1. Verify page title or app logo displays "Swag Labs".
        boolean isInventoryTitle = swagLabsActionPage.isTitle("Swag Labs");
        Assertions.assertTrue(isInventoryTitle);
        // 2. Verify header displays burger menu button and shopping cart icon.
        boolean isBurgerMenuBtn = swagLabsActionPage.isElementDisplayed(BURGER_MENU_BUTTON);
        boolean isShoppingCartIcon = swagLabsActionPage.isElementDisplayed(SHOPPING_CART_ICON);
        Assertions.assertTrue(isBurgerMenuBtn & isShoppingCartIcon);
        // 3. Verify sub-header displays "Products" label and product sort container.
        boolean isProductsTitle = swagLabsActionPage.compareElement(INVENTORY_SUB_HEADER_TITLE, "Products");
        Assertions.assertTrue(isProductsTitle);
        boolean isProductSortContainer = swagLabsActionPage.isElementDisplayed(PRODUCT_SORT_CONTAINER);
        Assertions.assertTrue(isProductSortContainer);
        // 4. Verify exactly 6 product items are displayed in the inventory list.
        Assertions.assertEquals(6, getProducts().size());
    }

    /*
    2. Product catalog display and data validation
    * */
    // TC-INV-02: Verify product details and prices
    @TestId("3f8ba6a8")
    @Tag("edge")
    @Test
    public void testVerifyProductDetailsAndPrices() {
        for (WebElement prod : getProducts()) {
            // 2. Verify each product has a non-empty title.
            //    Verify each product has a non-empty price.
            Assertions.assertFalse(prod.findElement(INVENTORY_PRODUCT_NAME).getText().isEmpty());
            Assertions.assertFalse(prod.findElement(INVENTORY_PRODUCT_PRICE).getText().isEmpty());
            System.out.printf("%s: price %s", prod.findElement(INVENTORY_PRODUCT_NAME).getText(), prod.findElement(INVENTORY_PRODUCT_PRICE).getText()).println();
            // 3. Verify each product has a non-empty description.
            Assertions.assertFalse(prod.findElement(INVENTORY_PRODUCT_DESCRIPTION).getText().isEmpty());
            // 4. Verify each item initially displays an "Add to cart" button.
            Assertions.assertEquals("Add to cart", prod.findElement(INVENTORY_BOUTON_ACTION).getText());
            // 5. Verify each product has a valid non-broken image asset.
            String src = prod.findElement(INVENTORY_PRODUCT_IMAGE).getAttribute("src");
            Assertions.assertFalse(src == null && src.trim().isEmpty());
        }
    }

    /*
    3. Product sorting functionality
    * */
    // TC-INV-03: Sort products by Name (A to Z) - default sort
    @TestId("ec529da2")
    @Tag("functional")
    @Test
    public void testSortProductsByNameAToZ() {
        // 1. Verify dropdown defaults to "Name (A to Z)".
        boolean defaultProdsSort = swagLabsActionPage.compareElement(PRODUCT_SORT_ACTIVE_OPTION, "Name (A to Z)");
        Assertions.assertTrue(defaultProdsSort);
        // 2. Verify first item is "Sauce Labs Backpack" and last item is "Test.allTheThings() T-Shirt (Red)".
        System.out.println("First Product : " + swagLabsActionPage.getElementText(getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME)));
        System.out.println("Last Product  : " + swagLabsActionPage.getElementText(getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_NAME)));
        boolean firstProdSauceLabsBackpack = swagLabsActionPage.compareElement(getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME), "Sauce Labs Backpack");
        boolean lastProdTShirtRed = swagLabsActionPage.compareElement(getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_NAME), "Test.allTheThings() T-Shirt (Red)");
        Assertions.assertTrue(firstProdSauceLabsBackpack & lastProdTShirtRed);
    }

    // TC-INV-04: Sort products by Name (Z to A)
    @TestId("340c194f")
    @Tag("functional")
    @Test
    public void testSortProductsByNameZToA() {
        // 1. Select "Name (Z to A)" from the sort dropdown.
        WebElement select = driver.findElement(PRODUCT_SORT_CONTAINER);
        Select productSort = new Select(select);
        productSort.selectByValue("za");
        // 2. Verify first item is "Test.allTheThings() T-Shirt (Red)" and last item is "Sauce Labs Backpack".
        System.out.println("First Product : " + swagLabsActionPage.getElementText(getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME)));
        System.out.println("Last Product  : " + swagLabsActionPage.getElementText(getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_NAME)));
        boolean firstProdSauceLabsBackpack = swagLabsActionPage.compareElement(getProducts().get(0).findElement(INVENTORY_PRODUCT_NAME), "Test.allTheThings() T-Shirt (Red)");
        boolean lastProdTShirtRed = swagLabsActionPage.compareElement(getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_NAME), "Sauce Labs Backpack");
        Assertions.assertTrue(firstProdSauceLabsBackpack & lastProdTShirtRed);
    }

    // TC-INV-05: Sort products by Price (low to high)
    @TestId("c08b5629")
    @Tag("functional")
    @Test
    public void testSortProductsByPriceHighToLow() {
        // 1. Select "Price (high to low)" from the sort dropdown.
        WebElement select = driver.findElement(PRODUCT_SORT_CONTAINER);
        Select productSort = new Select(select);
        productSort.selectByValue("hilo");
        // 2. Verify first item is "Sauce Labs Fleece Jacket" ($49.99).
        System.out.println("First Product's Price : " + swagLabsActionPage.getElementText(getProducts().get(0).findElement(INVENTORY_PRODUCT_PRICE)));
        boolean firstProdPrice = swagLabsActionPage.compareElement(getProducts().get(0).findElement(INVENTORY_PRODUCT_PRICE), "$49.99");
        // 3. Verify last item is "Sauce Labs Onesie" ($7.99).
        System.out.println("Last Product's Price  : " + swagLabsActionPage.getElementText(getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_PRICE)));
        boolean lastProdPrice = swagLabsActionPage.compareElement(getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_PRICE), "$7.99");
        // 4. Verify prices across all items are in non-increasing order.
        Assertions.assertTrue(firstProdPrice & lastProdPrice);
    }

    // TC-INV-06: Sort products by Price (low to high)
    @TestId("788cf4c0")
    @Tag("functional")
    @Test
    public void testSortProductsByPriceLowToHigh() {
        // 1. Select "Price (high to low)" from the sort dropdown.
        WebElement select = driver.findElement(PRODUCT_SORT_CONTAINER);
        Select productSort = new Select(select);
        productSort.selectByValue("lohi");
        // 2. Verify first item is "Sauce Labs Fleece Jacket" ($49.99).
        System.out.println("First Product's Price : " + swagLabsActionPage.getElementText(getProducts().get(0).findElement(INVENTORY_PRODUCT_PRICE)));
        boolean firstProdPrice = swagLabsActionPage.compareElement(getProducts().get(0).findElement(INVENTORY_PRODUCT_PRICE), "$7.99");
        // 3. Verify last item is "Sauce Labs Onesie" ($7.99).
        System.out.println("Last Product's Price  : " + swagLabsActionPage.getElementText(getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_PRICE)));
        boolean lastProdPrice = swagLabsActionPage.compareElement(getProducts().get(getProducts().size() - 1).findElement(INVENTORY_PRODUCT_PRICE), "$49.99");
        // 4. Verify prices across all items are in non-increasing order.
        Assertions.assertTrue(firstProdPrice & lastProdPrice);
    }

    /*
    4. Add to cart and remove item interactions
    * */
    // TC-INV-07: Add single item to cart
    @TestId("7bb49f0b")
    @Tag("critical")
    @Test
    public void testAddSingleItemToCart() {
        // 1. Locate "Sauce Labs Backpack" and click "Add to cart".
        getProducts().get(0).findElement(BOUTONS_ACTION).click();
        // 2. Verify the button text changes from "Add to cart" to "Remove".
        isRemoveBtnText = swagLabsActionPage.compareElement(getProducts().get(0).findElement(INVENTORY_BOUTON_ACTION), "Remove");
        Assertions.assertTrue(isRemoveBtnText);
        // 3. Verify the shopping cart icon shows a badge with the number 1.
        boolean isOneProductInCart = swagLabsActionPage.compareElement(CART_BADGE, 1);
        Assertions.assertTrue(isOneProductInCart);
    }

    // TC-INV-08: Remove item directly from inventory page
    @TestId("33e2aca2")
    @Tag("critical")
    @Test
    public void testRemoveItemDirectlyFromInventoryPage() {
        // 1. Add an item to the cart (button shows "Remove", badge displays 1).
        getProducts().get(0).findElement(BOUTONS_ACTION).click();
        isRemoveBtnText = swagLabsActionPage.compareElement(getProducts().get(0).findElement(INVENTORY_BOUTON_ACTION), "Remove");
        Assertions.assertTrue(isRemoveBtnText);
        boolean isOneProductInCart = swagLabsActionPage.compareElement(CART_BADGE, 1);
        Assertions.assertTrue(isOneProductInCart);
        // 2. Click the "Remove" button on that same product card.
        getProducts().get(0).findElement(BOUTONS_ACTION).click();
        // 3. Verify the button changes back to "Add to cart".
        isAddToCartBtnText = swagLabsActionPage.compareElement(getProducts().get(0).findElement(INVENTORY_BOUTON_ACTION), "Add to cart");
        Assertions.assertTrue(isAddToCartBtnText);
        // 4. Verify the shopping cart badge is removed (cart is empty).
        isCartEmpty = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, empty");
        Assertions.assertTrue(isCartEmpty);
    }

    // TC-INV-09: Add multiple items and verify cumulative cart count
    @TestId("d12d4778")
    @Tag("functional")
    @Test
    public void testAddMultipleItemsAndVerifyCumulativeCartCount() throws InterruptedException {
        // 1. Click "Add to cart" on 3 distinct products (e.g. Backpack, Bike Light, Onesie).
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 2 || i == 3) {
                continue;
            }
            if (i == 5) {
                break;
            }
            //System.out.println("Product Name : " + swagLabsActionPage.getElementText(myList.get(i).findElement(INVENTORY_PRODUCT_NAME)));
            getProducts().get(i).findElement(BOUTONS_ACTION).click();
        }
        // 2. Verify cart badge displays 3.
        boolean isThreeProductsInCart = swagLabsActionPage.compareElement(CART_BADGE, 3);
        Assertions.assertTrue(isThreeProductsInCart);
        // 3. Click "Add to cart" on the remaining 3 products.
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 0 || i == 1 || i == 4) {
                continue;
            }
            //System.out.println("Product Name : " + swagLabsActionPage.getElementText(myList.get(i).findElement(INVENTORY_PRODUCT_NAME)));
            getProducts().get(i).findElement(BOUTONS_ACTION).click();
        }
        // 4. Verify cart badge displays 6.
        boolean isSixProductsInCart = swagLabsActionPage.compareElement(CART_BADGE, 6);
        Assertions.assertTrue(isSixProductsInCart);
        // 5. Remove 2 items and verify cart badge decrements to 4.
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 2 || i == 3 || i == 4 || i == 5) {
                continue;
            }
            //System.out.println("Product Name : " + swagLabsActionPage.getElementText(myList.get(i).findElement(INVENTORY_PRODUCT_NAME)));
            getProducts().get(i).findElement(BOUTONS_ACTION).click();
        }
        boolean isFourProductsInCart = swagLabsActionPage.compareElement(CART_BADGE, 4);
        Assertions.assertTrue(isFourProductsInCart);
    }

    /* 5. Navigation and product detail links
     */
    // TC-INV-10: Navigate to product detail page via item title
    @TestId("5e8a0bf8")
    @Tag("functional")
    @Test
    public void testNavigateToProductDetailPageViaItemTitle() {
        // 1. Click on the product name "Sauce Labs Backpack".
        driver.findElement(SauceLabsBackpack).click();
        // 2. Verify URL contains /inventory-item.html?id=4.
        boolean isPageId4 = swagLabsActionPage.isContain("/inventory-item.html?id=4");
        Assertions.assertTrue(isPageId4);
        // 3. Verify product details view displays "Sauce Labs Backpack" and $29.99.
        boolean is_prod_details_name = swagLabsActionPage.compareElement(PRODUCT_DETAILS_NAME, "Sauce Labs Backpack");
        Assertions.assertTrue(is_prod_details_name);
        boolean is_prod_details_price = swagLabsActionPage.compareElement(PRODUCT_DETAILS_PRICE, "$29.99");
        Assertions.assertTrue(is_prod_details_price);
        // 4. Click "Back to products".
        driver.findElement(BACK_TO_PRODUCTS).click();
        // 5. Verify user returns to /inventory.html.
        boolean isPageInventory = swagLabsActionPage.isContain("/inventory.html");
        Assertions.assertTrue(isPageInventory);
    }

    // TC-INV-11: Navigate to product detail page via item image
    @TestId("310f7524")
    @Tag("functional")
    @Test
    public void testNavigateToProductDetailPageViaItemImage() {
        // 1. Click on the image for "Sauce Labs Bike Light".
        driver.findElement(SauceLabsBikeLightImg).click();
        // 2. Verify URL navigates to /inventory-item.html?id=0.
        boolean isPageId0 = swagLabsActionPage.isContain("/inventory-item.html?id=0");
        Assertions.assertTrue(isPageId0);
        // 3. Click "Back to products" and confirm return to /inventory.html.
        driver.findElement(BACK_TO_PRODUCTS).click();
        boolean isPageInventory = swagLabsActionPage.isContain("/inventory.html");
        Assertions.assertTrue(isPageInventory);
    }

    // TC-INV-12: Navigate to shopping cart page
    @TestId("ac7cf877")
    @Tag("critical")
    @Test
    public void testNavigateToShoppingCartPage() throws InterruptedException {
        // 1. Add one or more products to the cart.
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 2 || i == 3 || i == 4) {
                //System.out.println("Product Name : " + swagLabsActionPage.getElementText(myList.get(i).findElement(INVENTORY_PRODUCT_NAME)));
                getProducts().get(i).findElement(BOUTONS_ACTION).click();
            }
        }
        // 2. Click on the shopping cart icon in the top right.
        driver.findElement(CART_LINK).click();
        // 3. Verify URL changes to /cart.html.
        boolean isCartPage = swagLabsActionPage.isContain("/cart.html");
        Assertions.assertTrue(isCartPage);
        // 4. Verify added items appear in the cart list.
        Thread.sleep(2000);
        List<WebElement> cartTable = swagLabsActionPage.getList(CART_TABLE_ITEM);
        Assertions.assertEquals(3, cartTable.size());
    }

    /*
    6. Burger menu functionality
    * */
    // TC-INV-13: Open and close sidebar burger menu
    @TestId("a54d640a")
    @Tag("edge")
    @Test
    public void testOpenAndCloseSidebarBurgerMenu() throws InterruptedException {
        // 1. Click burger menu button (#react-burger-menu-btn).
        driver.findElement(BURGER_MENU_BUTTON).click();
        Thread.sleep(3000);
        // 2. Verify sidebar menu expands with navigation options: "All Items", "About", "Logout", "Reset App State".
        boolean isAllItemsMenu = swagLabsActionPage.isElementDisplayed(BURGER_MENU_ALL_ITEMS);
        Assertions.assertTrue(isAllItemsMenu);
        boolean isAboutMenu = swagLabsActionPage.isElementDisplayed(BURGER_MENU_ABOUT);
        Assertions.assertTrue(isAboutMenu);
        boolean isLogoutMenu = swagLabsActionPage.isElementDisplayed(BURGER_MENU_LOGOUT);
        Assertions.assertTrue(isLogoutMenu);
        boolean isResetAppStateMenu = swagLabsActionPage.isElementDisplayed(BURGER_MENU_RESET_APP_STATE);
        Assertions.assertTrue(isResetAppStateMenu);
        boolean isInventoryActive = swagLabsActionPage.compareAttribute(BM_MENU_WRAP, "aria-hidden", "false");
        Assertions.assertTrue(isInventoryActive);
        // 3. Click the "X" button to close
        driver.findElement(BURGER_X_BUTTON).click();
        // 4. Verify sidebar closes and inventory grid remains interactive.
        boolean isInventoryInteractive = swagLabsActionPage.compareAttribute(BM_MENU_WRAP, "aria-hidden", "true");
        Assertions.assertTrue(isInventoryInteractive);
    }

    // TC-INV-14: Sidebar "All Items" link
    @TestId("f44d88f8")
    @Tag("edge")
    @Test
    public void testSidebarAllItemsLink() {
        // 1. Open the sidebar menu and click "All Items".
        driver.findElement(BURGER_MENU_BUTTON).click();
        // 2. Open burger menu and click "All Items".
        driver.findElement(BURGER_MENU_ALL_ITEMS).click();
        // 3. Verify user returns to /inventory.html.
        boolean isPageInventory = swagLabsActionPage.isContain("/inventory.html");
        Assertions.assertTrue(isPageInventory);
    }

    // TC-INV-15: Sidebar "About" link
    @TestId("8f92cd69")
    @Tag("edge")
    @Test
    public void testSidebarAboutLink() {
        // 1. Open burger menu and click "About".
        driver.findElement(BURGER_MENU_BUTTON).click();
        driver.findElement(BURGER_MENU_ABOUT).click();
        // 2. Verify navigation to saucelabs.com.
        boolean isSauceLabsWebSite = swagLabsActionPage.isContain("https://saucelabs.com/");
        Assertions.assertTrue(isSauceLabsWebSite);
    }

    // TC-INV-16: Sidebar "Reset App State"
    @TestId("862bda44")
    @Tag("functional")
    @Test
    public void testSidebarResetAppState() throws InterruptedException {
        // 1. Add 2 products to the cart (badge shows 2).
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 1 || i == 2) {
                //System.out.println("Product Name : " + swagLabsActionPage.getElementText(myList.get(i).findElement(INVENTORY_PRODUCT_NAME)));
                getProducts().get(i).findElement(BOUTONS_ACTION).click();
            }
        }
        // 2. Open burger menu and click "Reset App State".
        driver.findElement(BURGER_MENU_BUTTON).click();
        driver.findElement(BURGER_MENU_RESET_APP_STATE).click();
        // 3. Close sidebar
        Thread.sleep(2000);
        driver.findElement(BURGER_X_BUTTON).click();
        // 4. Verify cart badge is cleared and product buttons revert to "Add to cart".
        isCartEmpty = swagLabsActionPage.compareAttribute(CART_LINK, "aria-label", "Cart, empty");
        Assertions.assertTrue(isCartEmpty);
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 1 || i == 2) {
                //System.out.println("Product Name : " + swagLabsActionPage.getElementText(myList.get(i).findElement(INVENTORY_PRODUCT_NAME)));
                System.out.printf("Product %d 's button revert to : %s", i, getProducts().get(i).findElement(BOUTONS_ACTION).getText()).println();
                Assertions.assertEquals("Add to cart", getProducts().get(i).findElement(BOUTONS_ACTION).getText());
            }
        }
    }

    // TC-INV-17: Sidebar "Logout"
    @TestId("3b5a93dc")
    @Tag("critical")
    @Test
    public void testSidebarLogout() {
        // 1. Open burger menu and click "Logout".
        driver.findElement(BURGER_MENU_BUTTON).click();
        driver.findElement(BURGER_MENU_LOGOUT).click();
        // 2. Verify user returns to /login.html.
        boolean isPageLogin = swagLabsActionPage.isContain("https://www.saucedemo.com/");
        Assertions.assertTrue(isPageLogin);
    }

    /*
     * 7. Footer links and legal text validation
     * */
    // TC-INV-18: Verify social media external links
    @TestId("891d08a0")
    @Tag("edge")
    @Test
    public void testVerifySocialMediaExternalLinks() throws InterruptedException {
        // 1. Scroll to the footer.
        WebElement footerSection = driver.findElement(INVENTORY_FOOTER);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footerSection);

        // 2. Verify Twitter/X icon has link to https://twitter.com/saucelabs.
        driver.findElement(TWITTER_X_LINK).click();
        Thread.sleep(2000);
        // fetch handles of all windows
        windowHandles = driver.getWindowHandles().toArray();
        System.out.println(Arrays.toString(windowHandles));
        driver.switchTo().window((String) windowHandles[1]);
        boolean isTwitterPage = swagLabsActionPage.isContain("https://x.com/saucelabs");
        Assertions.assertTrue(isTwitterPage);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window((String) windowHandles[0]);

        // 3. Verify Facebook icon has link to https://www.facebook.com/saucelabs.
        Thread.sleep(2000);
        driver.findElement(FACEBOOK_LINK).click();
        // fetch handles of all windows
        windowHandles = driver.getWindowHandles().toArray();
        System.out.println(Arrays.toString(windowHandles));
        Thread.sleep(2000);
        driver.switchTo().window((String) windowHandles[1]);
        boolean isFacebookPage = swagLabsActionPage.isContain("https://www.facebook.com/saucelabs");
        Assertions.assertTrue(isFacebookPage);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window((String) windowHandles[0]);

        // 4. Verify LinkedIn icon has link to https://www.linkedin.com/company/sauce-labs/.
        Thread.sleep(2000);
        driver.findElement(LINKEDIN_LINK).click();
        // fetch handles of all windows
        windowHandles = driver.getWindowHandles().toArray();
        System.out.println(Arrays.toString(windowHandles));
        Thread.sleep(2000);
        driver.switchTo().window((String) windowHandles[1]);
        boolean isLinkedinPage = swagLabsActionPage.isContain("https://www.linkedin.com/company/sauce-labs/");
        Assertions.assertTrue(isLinkedinPage);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window((String) windowHandles[0]);
    }

    // TC-INV-19: Verify footer copyright and policy text
    @TestId("fda6eaad")
    @Tag("edge")
    @Test
    public void testVerifyFooterCopyrightAndPolicyText() {
        // 1. Scroll to the bottom of the page.
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(INVENTORY_FOOTER));
        // 2. Verify text: © 2026 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy.
        String inventory_footer_copy = driver.findElement(INVENTORY_FOOTER_COPY).getText();
        System.out.println("inventory_footer_copy : " + inventory_footer_copy);
        Assertions.assertEquals("© 2026 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy", inventory_footer_copy);
    }

    /*
    8. State persistence and security / session guards
    * */
    // TC-INV-20: Cart state persistence on page reload
    @TestId("7c41fb21")
    @Tag("edge")
    @Test
    public void testCartStatePersistenceOnPageReload() {
        // 1. Add 2 items to the cart.
        for (int i = 0; i < getProducts().size(); i++) {
            if (i == 1 || i == 2) {
                //System.out.println("Product Name : " + swagLabsActionPage.getElementText(myList.get(i).findElement(INVENTORY_PRODUCT_NAME)));
                getProducts().get(i).findElement(BOUTONS_ACTION).click();
            }
        }
        // 2. Reload the page.
        swagLabsActionPage.reloadPage();
        // 3. Verify cart badge still displays 2 and the selected items still show "Remove".
        boolean stillTwoProductsInCart = swagLabsActionPage.compareElement(CART_BADGE, 2);
        Assertions.assertTrue(stillTwoProductsInCart);
        // On relit la liste : les anciens elements sont perimes apres le reload
        List<WebElement> productsAfterReload = swagLabsActionPage.getList(_PRODUCT_ITEMS);
        for (int i = 0; i < productsAfterReload.size(); i++) {
            if (i == 1 || i == 2) {
                String btnText = productsAfterReload.get(i).findElement(BOUTONS_ACTION).getText();
                System.out.printf("Product %d 's button reverted to : %s%n", i, btnText).println();
                Assertions.assertEquals("Remove", btnText);
            }
        }
    }

    // TC-INV-21: Unauthorized direct access to inventory page
    @TestId("15844dd7")
    @Tag("edge")
    @Test
    public void testUnauthorizedDirectAccessToInventoryPage() {
        // 1. In a clean session without authentication, navigate directly to https://www.saucedemo.com/inventory.html.
        logout();
        driver.get("https://www.saucedemo.com/inventory.html");
        // 2. Verify user is redirected back to the login page.
        boolean userIsRedirectedBackToLogin = swagLabsActionPage.isContain("https://www.saucedemo.com/");
        Assertions.assertTrue(userIsRedirectedBackToLogin);
        // 3. Verify error message appears indicating access is denied (e.g. "Epic sadface: You can only access '/inventory.html' when you are logged in.").
        String errorMsg = driver.findElement(MESSAGE_ERREUR).getText();
        Assertions.assertEquals(ERREUR_FORBIDEN_LOGIN, errorMsg);
    }

    // Renvoie toujours la liste actuelle des produits de la page affichee
    private List<WebElement> getProducts() {
        System.out.println("Fetching product list from the inventory page.");
        return swagLabsActionPage.getList(_PRODUCT_ITEMS);
    }

    // sample login method
    private void login() {
        driver.get(URL_LOGIN);
        swagLabsActionPage.enter(CHAMP_USERNAME, USERNAME_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, PASSWORD_VALIDE);
        swagLabsActionPage.enter(CHAMP_PASSWORD, String.valueOf(Keys.ENTER));
    }

    private void logout() {
        driver.findElement(BURGER_MENU_BUTTON).click();
        driver.findElement(BURGER_MENU_LOGOUT).click();
    }
}
