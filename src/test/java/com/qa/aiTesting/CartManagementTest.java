package com.qa.aiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.aiTesting.pages.CartManagementPage;

/** Tests cart removals and Reset App State using the cart management page facade. */
public class CartManagementTest extends SwagLabsTestBase {

    @Test(description = "Remove products from inventory and cart and verify the badge count")
    public void removesProductsFromInventoryAndCart() {
        loginAsStandardUser();
        CartManagementPage managementPage = new CartManagementPage(driver, WAIT_TIMEOUT);
        managementPage.addProduct("sauce-labs-backpack", "1");
        managementPage.addProduct("sauce-labs-bike-light", "2");
        managementPage.addProduct("sauce-labs-bolt-t-shirt", "3");
        Assert.assertEquals(managementPage.getCartBadgeText(), "3", "Badge should show three products");

        managementPage.removeProductFromInventory("sauce-labs-bolt-t-shirt", "2");
        managementPage.openCart();
        managementPage.removeProductFromCart("sauce-labs-bike-light", "1");
        Assert.assertEquals(managementPage.getCartBadgeText(), "1",
                "Badge should show one product after removing two products");
    }

    @Test(description = "Reset App State removes the cart badge from the DOM")
    public void resetAppStateClearsCartBadge() {
        loginAsStandardUser();
        CartManagementPage managementPage = new CartManagementPage(driver, WAIT_TIMEOUT);
        managementPage.addProduct("sauce-labs-backpack", "1");
        managementPage.addProduct("sauce-labs-bike-light", "2");
        Assert.assertEquals(managementPage.getCartBadgeText(), "2", "Badge should show two products before reset");

        managementPage.resetAppState();
        Assert.assertTrue(managementPage.isCartBadgeAbsentFromDom(),
                "Cart badge should be removed from the DOM after Reset App State");
    }
}
