package com.qa.aiTesting;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.aiTesting.pages.SideMenuPage.DynamicCatalogObservation;

/**
 * TestNG coverage for the Swag Labs left-side menu on inventory.html.
 */
public class SideMenuTest extends SwagLabsTestBase {

    private static final String UNAUTHORIZED_INVENTORY_ERROR = "Epic sadface: You can only access '/inventory.html' when you are logged in.";
    private static final String BACKPACK_ID = "sauce-labs-backpack";
    private static final String BIKE_LIGHT_ID = "sauce-labs-bike-light";

    @Test(description = "Side menu opens and closes from inventory")
    public void opensAndClosesSideMenu() {
        loginAsStandardUser();
        Assert.assertTrue(sideMenuPage.isClosed(), "Side menu should be closed on initial inventory load");

        sideMenuPage.open();
        Assert.assertTrue(sideMenuPage.arePrimaryLinksVisible(), "Primary side-menu links should be visible");

        sideMenuPage.close();
        Assert.assertTrue(sideMenuPage.isClosed(), "Side menu should be closed after clicking its close button");
    }

    @Test(description = "All Items returns from product details to inventory")
    public void allItemsReturnsFromProductDetailsToInventory() {
        loginAsStandardUser();
        inventoryPage.openProduct("Sauce Labs Backpack");
        Assert.assertEquals(productDetailsPage.getName(), "Sauce Labs Backpack");

        sideMenuPage.open();
        sideMenuPage.clickAllItems();

        Assert.assertTrue(inventoryPage.isInventoryUrl(), "All Items should navigate to inventory.html");
        Assert.assertFalse(inventoryPage.getProductNames().isEmpty(), "Product list should be displayed");
    }

    @Test(description = "Dynamic Catalog responds to click or exposes its optional state")
    public void inspectsDynamicCatalogOption() {
        loginAsStandardUser();
        sideMenuPage.open();
        DynamicCatalogObservation observation = sideMenuPage.inspectDynamicCatalog();

        if (observation.present() && observation.clickable()) {
            Assert.assertTrue(observation.clickPerformed(), "Clickable Dynamic Catalog should be activated");
            Assert.assertTrue(observation.stateObserved(), "Activation should expose a submenu, toggle state, or associated navigation");
        }
    }

    @Test(description = "About navigates to the official Sauce Labs site")
    public void aboutOpensSauceLabs() {
        loginAsStandardUser();
        sideMenuPage.open();
        sideMenuPage.clickAbout();
        Assert.assertTrue(sideMenuPage.isAboutDestination(), "About should navigate to saucelabs.com");
    }

    @Test(description = "Logout blocks a subsequent direct inventory access")
    public void logoutReturnsToLoginAndProtectsInventory() {
        loginAsStandardUser();
        sideMenuPage.open();
        sideMenuPage.logout();
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Logout should display the login form");

        inventoryPage.openDirectly();
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Direct inventory access should redirect to login");
        Assert.assertEquals(loginPage.getErrorMessage(), UNAUTHORIZED_INVENTORY_ERROR, "Direct inventory access after logout should show an authorization error");
    }

    @Test(description = "Reset App State clears cart badge and restores product buttons")
    public void resetAppStateClearsCartAndRestoresButtons() {
        loginAsStandardUser();
        inventoryPage.addProduct(BACKPACK_ID, "1");
        inventoryPage.addProduct(BIKE_LIGHT_ID, "2");
        Assert.assertEquals(inventoryPage.getCartBadgeText(), "2", "Cart badge should show two products");
        // Liste immuable des sélecteurs possibles pour le bouton d'action du produit
        List<String> productActionsBeforeReset = List.of(
                inventoryPage.getProductActionText(BACKPACK_ID),
                inventoryPage.getProductActionText(BIKE_LIGHT_ID));
        Assert.assertEquals(productActionsBeforeReset, List.of("Remove", "Remove"), "Both selected product buttons should be in the Remove state before reset");

        sideMenuPage.open();
        sideMenuPage.resetAppState();
        Assert.assertTrue(sideMenuPage.isCartBadgeAbsentFromDom(), "Reset App State should remove badge from the DOM");
        List<String> productActionsAfterReset = List.of(
                inventoryPage.getProductActionText(BACKPACK_ID),
                inventoryPage.getProductActionText(BIKE_LIGHT_ID));
        Assert.assertEquals(productActionsAfterReset, List.of("Add to cart", "Add to cart"), "Reset App State should restore both product buttons to Add to cart; actual states: "
                + productActionsAfterReset);
    }
}
