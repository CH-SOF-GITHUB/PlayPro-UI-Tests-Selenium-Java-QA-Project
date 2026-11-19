package com.qa.aiTesting.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Page object for the Swag Labs checkout information and overview steps. */
public class CheckoutPage {
    private static final By CART_ITEMS = By.className("cart_item");
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutPage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name"))).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code"))).sendKeys(postalCode);
    }

    public void continueCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("continue"))).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")))
                .getText();
    }

    public void waitForOverview() {
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("checkout_summary_container")));
    }

    public int getOverviewItemCount() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CART_ITEMS)).size();
    }

    public double getItemSubtotal() {
        return readAmount(By.className("summary_subtotal_label"), "Item total: $");
    }

    public double getTax() {
        return readAmount(By.className("summary_tax_label"), "Tax: $");
    }

    public double getGrandTotal() {
        return readAmount(By.className("summary_total_label"), "Total: $");
    }

    public List<Double> getOverviewItemPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_price")));
        return driver.findElements(By.className("inventory_item_price")).stream()
                .map(WebElement::getText).map(value -> Double.valueOf(value.replace("$", "").trim())).toList();
    }

    public void finishOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();
    }

    public String getCompletionMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='complete-header']"))).getText();
    }

    private double readAmount(By locator, String label) {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        return Double.parseDouble(text.replace(label, "").trim());
    }
}
