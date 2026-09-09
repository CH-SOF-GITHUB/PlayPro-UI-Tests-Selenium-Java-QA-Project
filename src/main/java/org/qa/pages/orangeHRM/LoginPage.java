package org.qa.pages.orangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;

public class LoginPage {

    private ActionDriver actionDriver;

    // Define locators by class
    private By userNameField = By.name("username");
    private By passwordField = By.cssSelector("input[type='password']");
    private By loginButton = By.cssSelector("button[type='submit']");

    private By errorMessage = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");

    // Initialize action driver by constructor
    public LoginPage(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }


    // Method to perform login
    public void login(String username, String password) {
        actionDriver.enter(userNameField, username);
        actionDriver.enter(passwordField, password);
        actionDriver.click(loginButton);
    }

    // Method to check if error message is display
    public boolean checkErrorMsgDisplayed() {
        return actionDriver.isDisplayed(errorMessage);
    }

    // Method to print Text from error message
    public String getErrorMsgText() {
        return actionDriver.getText(errorMessage);
    }

    // Method if error is correct or not
    public boolean verifyErrorMessage(String expectedErrorMsg) {
        return actionDriver.compareText(errorMessage, expectedErrorMsg);
    }

}
