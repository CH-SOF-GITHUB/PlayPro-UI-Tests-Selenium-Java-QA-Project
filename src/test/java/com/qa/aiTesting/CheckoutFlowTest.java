package com.qa.aiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

/** Exercises adding multiple products to a cart and completing checkout. */
public class CheckoutFlowTest extends SwagLabsTestBase {

    @Test(description = "Add multiple products and complete a Swag Labs checkout")
    public void completesCheckoutWithMultipleProducts() {
        loginAsStandardUser();
        inventoryPage.addProduct("sauce-labs-backpack", "1");
        inventoryPage.addProduct("sauce-labs-bike-light", "2");
        Assert.assertEquals(inventoryPage.getCartBadgeText(), "2", "Cart badge should show two products");

        cartPage.open();
        Assert.assertEquals(cartPage.getItemCount(), 2, "Cart should contain both selected products");
        cartPage.checkout();

        checkoutPage.enterFirstName("Jane");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("02138");
        checkoutPage.continueCheckout();
        checkoutPage.waitForOverview();
        Assert.assertEquals(checkoutPage.getOverviewItemCount(), 2,
                "Checkout overview should list both selected products");
        checkoutPage.finishOrder();
        Assert.assertEquals(checkoutPage.getCompletionMessage(), "Thank you for your order!",
                "Checkout should display the final order confirmation");
    }
}
