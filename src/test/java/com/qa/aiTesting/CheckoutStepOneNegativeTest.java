package com.qa.aiTesting;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Data-driven validation of required fields on checkout step one.
 */
public class CheckoutStepOneNegativeTest extends SwagLabsTestBase {

    @DataProvider(name = "missingRequiredCheckoutFields")
    public Object[][] missingRequiredCheckoutFields() {
        return new Object[][]{
                {"", "Doe", "02138", "Error: First Name is required"},
                {"Jane", "", "02138", "Error: Last Name is required"},
                {"Jane", "Doe", "", "Error: Postal Code is required"}
        };
    }

    @Test(dataProvider = "missingRequiredCheckoutFields", description = "Checkout step one displays the validation error for a missing required field")
    public void reportsMissingRequiredField(String firstName, String lastName, String postalCode, String expectedError) {
        loginAsStandardUser();
        inventoryPage.addProduct("sauce-labs-backpack", "1");
        cartPage.open();
        cartPage.checkout();

        if (!firstName.isEmpty()) {
            checkoutPage.enterFirstName(firstName);
        }
        if (!lastName.isEmpty()) {
            checkoutPage.enterLastName(lastName);
        }
        if (!postalCode.isEmpty()) {
            checkoutPage.enterPostalCode(postalCode);
        }
        checkoutPage.continueCheckout();

        Assert.assertEquals(checkoutPage.getErrorMessage(), expectedError, "Checkout should report the missing required field");
    }
}
