package com.qa.aiTesting;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.testng.Assert;
import org.testng.annotations.Test;

/** Verifies checkout subtotal, tax and final total calculations. */
public class CheckoutCalculationTest extends SwagLabsTestBase {

    private static final String BACKPACK_ID = "sauce-labs-backpack";
    private static final String BIKE_LIGHT_ID = "sauce-labs-bike-light";
    private static final double TAX_RATE = 0.08;

    @Test(description = "Checkout totals and tax use the expected two-decimal rounding")
    public void calculatesItemTotalTaxAndGrandTotal() {
        loginAsStandardUser();

        double backpackPrice = inventoryPage.getProductPrice(BACKPACK_ID);
        inventoryPage.addProduct(BACKPACK_ID, "1");
        double bikeLightPrice = inventoryPage.getProductPrice(BIKE_LIGHT_ID);
        inventoryPage.addProduct(BIKE_LIGHT_ID, "2");

        cartPage.open();
        Assert.assertEquals(cartPage.getItemCount(), 2, "Cart should contain both selected products");
        cartPage.checkout();
        checkoutPage.enterFirstName("Jane");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("02138");
        checkoutPage.continueCheckout();
        checkoutPage.waitForOverview();

        double expectedItemTotal = roundToCents(backpackPrice + bikeLightPrice);
        double expectedTax = roundToCents(expectedItemTotal * TAX_RATE);
        double expectedGrandTotal = roundToCents(expectedItemTotal + expectedTax);

        Assert.assertEquals(checkoutPage.getItemSubtotal(), expectedItemTotal, 0.0, "Item total should equal the sum of both unit prices rounded to cents");
        Assert.assertEquals(checkoutPage.getTax(), expectedTax, 0.0, "Tax should equal 8% of item total rounded to cents");
        Assert.assertEquals(checkoutPage.getGrandTotal(), expectedGrandTotal, 0.0, "Grand total should equal item total plus tax rounded to cents");
    }

    @Test(description = "Checkout sums four product prices and adds tax to the item total")
    public void calculatesTotalsForFourProducts() {
        loginAsStandardUser();
        String[] productIds = {
                "sauce-labs-backpack",
                "sauce-labs-bike-light",
                "sauce-labs-bolt-t-shirt",
                "sauce-labs-fleece-jacket"
        };

        double expectedItemTotal = 0.0;
        for (int i = 0; i < productIds.length; i++) {
            expectedItemTotal += inventoryPage.getProductPrice(productIds[i]);
            inventoryPage.addProduct(productIds[i], String.valueOf(i + 1));
        }
        expectedItemTotal = roundToCents(expectedItemTotal);

        cartPage.open();
        Assert.assertEquals(cartPage.getItemCount(), productIds.length, "Cart should contain all four selected products");
        cartPage.checkout();
        checkoutPage.enterFirstName("Jane");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("02138");
        checkoutPage.continueCheckout();
        checkoutPage.waitForOverview();

        Assert.assertEquals(checkoutPage.getOverviewItemCount(), productIds.length, "Checkout overview should list all four selected products");
        Assert.assertEquals(roundToCents(checkoutPage.getItemSubtotal()), expectedItemTotal, 0.0, "Item total should equal the sum of all four unit prices rounded to cents");

        double actualItemTotal = roundToCents(checkoutPage.getItemSubtotal());
        double actualTax = roundToCents(checkoutPage.getTax());
        double expectedGrandTotal = roundToCents(actualItemTotal + actualTax);
        Assert.assertEquals(roundToCents(checkoutPage.getGrandTotal()), expectedGrandTotal, 0.0, "Grand total should equal item total plus tax rounded to cents");
    }

    private double roundToCents(double amount) {
        return BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
