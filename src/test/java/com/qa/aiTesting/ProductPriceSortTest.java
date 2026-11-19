package com.qa.aiTesting;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

/** Verifies price sorting on the Swag Labs inventory page. */
public class ProductPriceSortTest extends SwagLabsTestBase {

    @Test(description = "Product prices are correctly sorted from low to high and high to low")
    public void productsCanBeSortedByPriceAscendingAndDescending() {
        loginAsStandardUser();
        inventoryPage.sortProducts("lohi");
        List<Double> ascendingPrices = inventoryPage.getProductPrices();
        Assert.assertFalse(ascendingPrices.isEmpty(), "Inventory should contain product prices");
        Assert.assertEquals(ascendingPrices, ascendingPrices.stream().sorted().toList(),
                "Prices should be sorted from low to high");

        inventoryPage.sortProducts("hilo");
        List<Double> descendingPrices = inventoryPage.getProductPrices();
        Assert.assertFalse(descendingPrices.isEmpty(), "Inventory should contain product prices");
        Assert.assertEquals(descendingPrices,
                descendingPrices.stream().sorted((left, right) -> Double.compare(right, left)).toList(),
                "Prices should be sorted from high to low");
    }
}
