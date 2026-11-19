package com.qa.aiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

/** Verifies required-field validation during Swag Labs checkout. */
public class CheckoutRequiredFieldsTest extends SwagLabsTestBase {

    @Test(description = "Checkout reports each missing required customer field")
    public void checkoutRequiresFirstNameLastNameAndPostalCode() {
        loginAsStandardUser();
        inventoryPage.addProduct("sauce-labs-backpack", "1");
        cartPage.open();
        cartPage.checkout();

        checkoutPage.continueCheckout();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
        checkoutPage.enterFirstName("Jane");
        checkoutPage.continueCheckout();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required");
        checkoutPage.enterLastName("Doe");
        checkoutPage.continueCheckout();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required");
        checkoutPage.enterPostalCode("02138");
        checkoutPage.continueCheckout();
        checkoutPage.waitForOverview();
        Assert.assertEquals(checkoutPage.getOverviewItemCount(), 1,
                "Completing required fields should open the checkout overview");
    }
}
