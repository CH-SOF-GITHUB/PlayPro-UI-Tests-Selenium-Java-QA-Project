package com.qa.aiTesting;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

/** Verifies alphabetical product sorting on the Swag Labs inventory page. */
public class ProductSortTest extends SwagLabsTestBase {

    @Test(description = "Products are displayed in alphabetical order from A to Z")
    public void productsCanBeSortedByNameAscending() {
        loginAsStandardUser();
        inventoryPage.sortProducts("az");
        List<String> actualNames = inventoryPage.getProductNames();
        List<String> expectedNames = actualNames.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList();
        Assert.assertFalse(actualNames.isEmpty(), "Inventory should contain products");
        Assert.assertEquals(actualNames, expectedNames, "Products should be sorted alphabetically from A to Z");
    }

    @Test(description = "The first product is the alphabetically last product when sorted Z to A")
    public void firstProductIsAlphabeticallyLastWhenSortedDescending() {
        loginAsStandardUser();
        inventoryPage.sortProducts("za");
        List<String> actualNames = inventoryPage.getProductNames();
        Assert.assertEquals(actualNames.get(0), "Test.allTheThings() T-Shirt (Red)",
                "The first product should be the alphabetically last product in Z to A order");
        Assert.assertEquals(actualNames.get(actualNames.size() - 1), "Sauce Labs Backpack",
                "The last product should be the alphabetically first product in Z to A order");
    }
}
