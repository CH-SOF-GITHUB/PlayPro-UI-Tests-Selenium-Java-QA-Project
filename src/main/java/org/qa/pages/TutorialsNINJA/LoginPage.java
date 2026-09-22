package org.qa.pages.TutorialsNINJA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {

    private ActionDriver actionDriver;

    // Locate Web Elements to access to Login page using class By
    private final By MyAccountDropDown = By.xpath("//span[text()='My Account']");
    private final By LoginOption = By.linkText("Login");
    private final By RegisterOption = By.linkText("Register");

    // Locate Web Elements to Login process using class By
    private final By EmailField = By.name("email");
    private final By PasswordField = By.name("password");
    private final By LoginBtn = By.xpath("//input[@value='Login']");

    // Locate Web Elements in Login Page: Proper breadcrumb, page heading, page title ...
    private final By LoginTitle1 = By.xpath("//h2[normalize-space()='Returning Customer']");
    private final By LoginTitle2 = By.xpath("//strong[normalize-space()='I am a returning customer']");
    private final By ListBreadCrumb = By.xpath("//ul[@class='breadcrumb']//li");

    // Locate Web Element of Error Message
    private final By ErrorMsg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

    // Locate Web Element of Logout
    private final By LogoutBtn = By.xpath("//a[@class='list-group-item' and text()='Logout']");
    private final By SuccessLogoutMsg = By.xpath("//div[@id='content']//h1");
    private final By LogoutOption = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']");

    // Locate Web Element of 'Forgotten Password'
    private final By ForgetPwdLink = By.linkText("Forgotten Password");
    private final By ForgetPwdTitle = By.xpath("//div[@id='content']//h1");

    // Locate Web Element depending on register new customer
    private final By ContinueBtn = By.xpath("//a[contains(@class,'btn-primary') and normalize-space()='Continue']");

    // Add Constructor of class page with Singleton Design Pattern
    public LoginPage(WebDriver driver) {
        this.actionDriver = BaseClass.getActionDriver();
    }

    // Login Steps: click en my account, click on login link, type email, type pwd, and click on Login Btn
    public void login(String email, String password) {
        openLoginPage();
        actionDriver.enter(EmailField, email);
        actionDriver.enter(PasswordField, password);
        actionDriver.click(LoginBtn);
    }

    public void openLoginPage() {
        actionDriver.click(MyAccountDropDown);
        actionDriver.click(LoginOption);
    }

    // Methods to Get Texts, Labels, Messages, breadcrumb of Login Form
    public String getLoginHeading1() {
        return actionDriver.getText(LoginTitle1);
    }

    public String getLoginHeading2() {
        return actionDriver.getText(LoginTitle2);
    }

    public List<String> getProperBreadcrumb() {
        List<WebElement> listBreadCrumb = actionDriver.getElements(ListBreadCrumb);
        List<String> Texts = new ArrayList<>();
        for (WebElement element : listBreadCrumb) {
            Texts.add(element.getText());
        }
        return Texts;
    }

    // Method to open page depending on URL
    public void openPageWithURL(String url) {
        actionDriver.openPage(url);
    }

    // Check if an error message displays or not when login fails
    public boolean verifyErrorMessage(String ExpectedErrorMsg) {
        String ActualText = actionDriver.getText(ErrorMsg);
        return ActualText.equals(ExpectedErrorMsg);
    }

    // Method to check if Error Message is displayed or not
    public boolean errorMsgIsDisplayed() {
        return actionDriver.isDisplayed(ErrorMsg);
    }

    // Method to logout from the system
    public void logout() {
        actionDriver.click(LogoutBtn);
    }

    // Method to logout from the system via drop down and navbar
    public void logoutViaDropDown() {
        actionDriver.click(MyAccountDropDown);
        actionDriver.click(LogoutOption);
    }

    // Method to compare Logout Message after logout system
    public boolean compareLogoutMsg(String ExpectedLogoutMsg) {
        return actionDriver.compareText(SuccessLogoutMsg, ExpectedLogoutMsg);
    }

    // Method to check if 'Forgotten Password' Link display and visible
    public boolean isForgetPwdLinkDisplayed() {
        return actionDriver.isDisplayed(ForgetPwdLink);
    }

    public boolean compareForgetLinkPwd(String ExpectedLink) {
        return actionDriver.compareText(ForgetPwdLink, ExpectedLink);
    }

    public void openForgetPwdPage() {
        actionDriver.click(ForgetPwdLink);
    }

    // Method to check if 'Forgotten Password' Title is displayed and visible
    public boolean isForgetPwdTitleDisplayed() {
        return actionDriver.isDisplayed(ForgetPwdTitle);
    }

    public boolean compareForgetTitlePwd(String ExpectedLink) {
        return actionDriver.compareText(ForgetPwdTitle, ExpectedLink);
    }

    // Method to check Login via TAB
    public void loginViaTab(String email, String pwd) {
        clickOnMyAccountDropmenu();
        clickOnLoginOption();
        actionDriver.tabToElementAndType(EmailField, email);
        actionDriver.tabToElementAndType(PasswordField, pwd);
        actionDriver.navigateWithTabAndPressEnter(LoginBtn);
    }

    public void clickOnMyAccountDropmenu() {
        actionDriver.click(MyAccountDropDown);
    }

    public void clickOnLoginOption() {
        actionDriver.click(LoginOption);
    }

    public void clickOnRegisterOption() {
        actionDriver.click(RegisterOption);
    }

    // Method to return the placeholder for any field input
    public boolean isEEmailHavePlaceholder(String ExpectedValue) {
        return actionDriver.compareByAttribute(EmailField, "placeholder", ExpectedValue);
    }

    // Methods of password:check placeholder and check tha password field hide its visibility
    public boolean isPwdHavePlaceholder(String ExpectedValue) {
        return actionDriver.compareByAttribute(PasswordField, "placeholder", ExpectedValue);
    }

    public boolean isHidePwdVisibility(String pwd, String attribute, String ExpectedValue) {
        actionDriver.enter(PasswordField, pwd);
        return actionDriver.compareByAttribute(PasswordField, attribute, ExpectedValue);
    }

    // Method to back in browser or navigate to another page
    public void backBrowser() throws InterruptedException {
        actionDriver.navigateBack();
        // return back to Login page
        Thread.sleep(2);
    }

    // Method to get Login page URL and Title
    public String returnCurrentURL() {
        return actionDriver.getCurrentURL();
    }

    public String returnCurrentTitle() {
        return actionDriver.getCurrentTitle();
    }

    // Method to Load the Current Page
    public void refreshCurrentPage() {
        actionDriver.refreshPage();
    }

    // Method to click on Login button multiple times
    public void clickMultipleLogin(int nb) {
        for (int i = 1; i < nb; i++) {
            actionDriver.click(LoginBtn);
            System.out.println("Login Button clicked - attempt #" + i);
        }
    }

    // Method to click on Continue button to access register new customer page
    public void clickOnContinue() {
        actionDriver.click(ContinueBtn);
    }
}
