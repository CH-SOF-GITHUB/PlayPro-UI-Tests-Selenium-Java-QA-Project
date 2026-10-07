package org.qa.pages.EventHUB;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;
import org.testng.Assert;

import java.time.Duration;

import static io.appium.java_client.CommandExecutionHelper.executeScript;

public class LoginPage {

    // Add Constructor of class page with Singleton Design Pattern
    private WebDriver driver;
    private WebDriverWait wait;

    // Add locators for the login page elements
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By signInButton = By.xpath("(//button[normalize-space()='Sign In'])[1]");

    // Add locators for the error message invalid email or password
    private final By errorPwdOrEmailMessage = By.xpath("//p[contains(@class,'text-sm font-medium flex-1 leading-snug')]");
    private final By errorInvalidFormatEmailMessage = By.xpath("//p[normalize-space()='Enter a valid email']");
    private final By errorInvalidPasswordMessage = By.xpath("//p[normalize-space()='Password must be at least 6 characters']");

    // Add Constructor of class page with Singleton Design Pattern
    public LoginPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver = driver;
    }

    // Methods
    public void openLoginPage() {
        driver.get("https://eventhub.rahulshettyacademy.com/login");
    }

    public void enterCredentials(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }

    public void enterEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        element.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.sendKeys(password);
    }

    public void clickSignIn() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        // Click on Web Element using JavaScriptExecutor to avoid ElementClickInterceptedException
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public boolean checkPwdOrEmailErrorMessageByText(String expectedErrorMessage) {
        String actualErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorPwdOrEmailMessage)).getText();
        return actualErrorMessage.equals(expectedErrorMessage);
    }

    public boolean checkInvalidFormatEmailErrorMessageByText(String expectedErrorMessage) {
        String actualErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorInvalidFormatEmailMessage)).getText();
        return actualErrorMessage.equals(expectedErrorMessage);
    }

    public boolean checkInvalidPasswordErrorMessageByText(String expectedErrorMessage) {
        String actualErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorInvalidPasswordMessage)).getText();
        return actualErrorMessage.equals(expectedErrorMessage);
    }

}
