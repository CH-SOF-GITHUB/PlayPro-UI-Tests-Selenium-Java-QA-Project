package com.qa.aiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

/** Verifies the product details view and return navigation on Swag Labs. */
public class ProductDetailsTest extends SwagLabsTestBase {

    private static final String PRODUCT_NAME = "Sauce Labs Backpack";

    @Test(description = "Product detail shows its name, description, price and image")
    public void displaysProductDetailsAndReturnsToInventory() {
        loginAsStandardUser();
        inventoryPage.openProduct(PRODUCT_NAME);
        Assert.assertEquals(productDetailsPage.getName(), PRODUCT_NAME,
                "The selected product name should be displayed");
        Assert.assertFalse(productDetailsPage.getDescription().isBlank(), "Product description should not be empty");
        Assert.assertTrue(productDetailsPage.getPrice().matches("\\$\\d+\\.\\d{2}"),
                "Product detail should show a price in currency format");
        Assert.assertTrue(productDetailsPage.isDetailedImageLoaded(), "Detailed product image should be fully loaded");

        productDetailsPage.backToProducts();
        Assert.assertTrue(inventoryPage.isLoaded() && inventoryPage.isInventoryUrl(),
                "Back to products should return to the inventory page");
    }
}
