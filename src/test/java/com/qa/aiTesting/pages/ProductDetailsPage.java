package com.qa.aiTesting.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Page object for an individual Swag Labs product detail view. */
public class ProductDetailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ProductDetailsPage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public String getName() { return visible(By.className("inventory_details_name")).getText(); }
    public String getDescription() { return visible(By.className("inventory_details_desc")).getText(); }
    public String getPrice() { return visible(By.className("inventory_details_price")).getText(); }

    public boolean isDetailedImageLoaded() {
        WebElement image = visible(By.className("inventory_details_img"));
        return Boolean.TRUE.equals(((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && arguments[0].naturalWidth > 0", image));
    }

    public void backToProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products"))).click();
        wait.until(ExpectedConditions.urlContains("inventory.html"));
    }

    private WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
