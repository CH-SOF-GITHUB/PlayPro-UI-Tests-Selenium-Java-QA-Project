package com.qa.cucumber.bdd.EVENTHUB;

import com.qa.cucumber.bdd.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;
import org.testng.Assert;

public class LoginPage {

    // Add Constructor of class page with Singleton Design Pattern
    private ActionDriver actionDriver;

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
        this.actionDriver = Hooks.getActionDriver();
    }

    // Methods
    public void openLoginPage() {
        actionDriver.openPage("https://eventhub.rahulshettyacademy.com/login");
    }

    public void enterCredentials(String email, String password) {
        actionDriver.enter(emailField, email);
        actionDriver.enter(passwordField, password);
    }

    public void enterEmail(String email) {
        actionDriver.enter(emailField, email);
    }

    public void enterPassword(String password) {
        actionDriver.enter(passwordField, password);
    }

    public void clickSignIn() {
        actionDriver.click(signInButton);
    }

    public void checkPwdOrEmailErrorMessageByText(String expectedErrorMessage) {
        String actualErrorMessage = actionDriver.getText(errorPwdOrEmailMessage);
        boolean isErrorMessageCorrect = actualErrorMessage.equals(expectedErrorMessage);
        Assert.assertTrue(isErrorMessageCorrect, "Expected error message: " + expectedErrorMessage + ", but got: " + actualErrorMessage);
    }

    public void checkInvalidFormatEmailErrorMessageByText(String expectedErrorMessage) {
        String actualErrorMessage = actionDriver.getText(errorInvalidFormatEmailMessage);
        boolean isErrorMessageCorrect = actualErrorMessage.equals(expectedErrorMessage);
        Assert.assertTrue(isErrorMessageCorrect, "Expected error message: " + expectedErrorMessage + ", but got: " + actualErrorMessage);
    }

    public void checkInvalidPasswordErrorMessageByText(String expectedErrorMessage) {
        String actualErrorMessage = actionDriver.getText(errorInvalidPasswordMessage);
        boolean isErrorMessageCorrect = actualErrorMessage.equals(expectedErrorMessage);
        Assert.assertTrue(isErrorMessageCorrect, "Expected error message: " + expectedErrorMessage + ", but got: " + actualErrorMessage);
    }


}
