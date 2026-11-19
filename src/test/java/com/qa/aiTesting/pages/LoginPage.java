package com.qa.aiTesting.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Page object for Swag Labs authentication. */
public class LoginPage {
    private static final String URL = "https://www.saucedemo.com/";
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void open() { driver.get(URL); }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    public boolean isLoginFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button"))).isDisplayed()
                && driver.findElement(By.id("user-name")).isDisplayed();
    }

    public boolean isLoginPageUrl() {
        return driver.getCurrentUrl().contains("saucedemo.com");
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")))
                .getText();
    }
}
