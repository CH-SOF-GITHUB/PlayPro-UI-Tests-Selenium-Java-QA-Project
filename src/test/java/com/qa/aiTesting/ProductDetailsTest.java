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
        Assert.assertEquals(productDetailsPage.getName(), PRODUCT_NAME, "The selected product name should be displayed");
        Assert.assertFalse(productDetailsPage.getDescription().isBlank(), "Product description should not be empty");
        Assert.assertTrue(productDetailsPage.getPrice().matches("\\$\\d+\\.\\d{2}"), "Product detail should show a price in currency format");
        Assert.assertTrue(productDetailsPage.isDetailedImageLoaded(), "Detailed product image should be fully loaded");

        productDetailsPage.backToProducts();
        Assert.assertTrue(inventoryPage.isLoaded() && inventoryPage.isInventoryUrl(),
                "Back to products should return to the inventory page");
    }

    @Test(description = "Add and remove a product from its detail page")
    public void cartStateStaysConsistentFromProductDetailsToInventory() {
        loginAsStandardUser();
        inventoryPage.openProduct(PRODUCT_NAME);

        productDetailsPage.addToCart();
        Assert.assertEquals(productDetailsPage.getCartBadgeText(), "1", "Cart badge should show one item after adding from product details");
        Assert.assertEquals(productDetailsPage.getProductActionText(), "Remove", "Product detail action should change to Remove after adding");

        productDetailsPage.removeFromCart();
        Assert.assertTrue(productDetailsPage.isCartBadgeAbsentFromDom(), "Cart badge should disappear after removing the only item");
        Assert.assertEquals(productDetailsPage.getProductActionText(), "Add to cart", "Product detail action should return to Add to cart after removal");

        productDetailsPage.backToProducts();
        Assert.assertTrue(inventoryPage.isLoaded() && inventoryPage.isInventoryUrl(), "Back to products should return to inventory");
        Assert.assertEquals(inventoryPage.getProductActionText("sauce-labs-backpack"), "Add to cart", "Inventory should reflect the removed product");
        Assert.assertTrue(inventoryPage.isCartBadgeAbsentFromDom(), "Inventory cart badge should remain absent after returning");
    }
}
