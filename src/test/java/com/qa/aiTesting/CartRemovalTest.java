package com.qa.aiTesting;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

/** Verifies cart removal and the resulting checkout subtotal. */
public class CartRemovalTest extends SwagLabsTestBase {

    @Test(description = "Removing a product updates cart quantity and checkout subtotal")
    public void removingProductUpdatesCartCountAndSubtotal() {
        loginAsStandardUser();
        inventoryPage.addProduct("sauce-labs-backpack", "1");
        inventoryPage.addProduct("sauce-labs-bike-light", "2");
        Assert.assertEquals(inventoryPage.getCartBadgeText(), "2", "Badge should show two products after adding them");

        cartPage.open();
        cartPage.waitForItemCount(2);
        cartPage.removeProduct("sauce-labs-bike-light", "1");
        Assert.assertEquals(cartPage.getBadgeText(), "1", "Badge should update to one after removing one product");
        cartPage.waitForItemCount(1);
        cartPage.checkout();

        checkoutPage.enterFirstName("Jane");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("02138");
        checkoutPage.continueCheckout();
        checkoutPage.waitForOverview();
        List<Double> remainingPrices = checkoutPage.getOverviewItemPrices();
        Assert.assertEquals(remainingPrices.size(), 1, "Checkout overview should contain the remaining product");
        double expectedSubtotal = remainingPrices.stream().mapToDouble(Double::doubleValue).sum();
        Assert.assertEquals(checkoutPage.getItemSubtotal(), expectedSubtotal, 0.001,
                "Checkout subtotal should match the remaining product price");
    }
}
