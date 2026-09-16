package org.qa.pages.TutorialsNINJA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

public class LoginPage {

    private ActionDriver actionDriver;

    // Locate Web Elements to access to Login page using class By
    private final By MyAccountDropDown = By.xpath("//span[text()='My Account']");
    private final By LoginOption = By.linkText("Login");

    // Locate Web Elements to Login process using class By
    private final By EmailField = By.name("email");
    private final By PasswordField = By.name("password");
    private final By LoginBtn = By.xpath("//input[@value='Login']");

    // Locate Web Element of Error Message
    private final By ErrorMsg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

    // Locate Web Element of Logout
    private final By LogoutBtn = By.xpath("//a[@class='list-group-item' and text()='Logout']");
    private final By SuccessLogoutMsg = By.xpath("//div[@id='content']//h1");

    // Add Constructor of class page with Singleton Design Pattern
    public LoginPage(WebDriver driver) {
        this.actionDriver = BaseClass.getActionDriver();
    }

    // Login Steps: click en my account, click on login link, type email, type pwd, and click on Login Btn
    public void login(String email, String password) {
        actionDriver.click(MyAccountDropDown);
        actionDriver.click(LoginOption);
        actionDriver.enter(EmailField, email);
        actionDriver.enter(PasswordField, password);
        actionDriver.click(LoginBtn);
    }

    // Check if an error message displays or not when login fails
    public boolean verifyErrorMessage(String ExpectedErrorMsg) {
        return actionDriver.compareText(ErrorMsg, ExpectedErrorMsg);
    }

    // Method to check if Error Message is displayed or not
    public boolean errorMsgIsDisplayed() {
        return actionDriver.isDisplayed(ErrorMsg);
    }

    // Method to logout from the system
    public void logout() {
        actionDriver.click(LogoutBtn);
    }

    // Method to compare Logout Message after logout system
    public boolean compareLogoutMsg(String ExpectedLogoutMsg) {
        return actionDriver.compareText(SuccessLogoutMsg, ExpectedLogoutMsg);
    }
}
