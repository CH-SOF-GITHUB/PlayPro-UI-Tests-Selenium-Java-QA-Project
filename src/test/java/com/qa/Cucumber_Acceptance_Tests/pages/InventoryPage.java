package com.qa.Cucumber_Acceptance_Tests.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
    private static final By TITLE = By.className("title");
    private static final By PRODUCTS = By.className("inventory_item");

    private final WebDriverWait wait;

    public InventoryPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDisplayed() {
        return "Products".equals(wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE)).getText())
                && !wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCTS)).isEmpty();
    }

    public int getProductCount() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCTS)).size();
    }
}
