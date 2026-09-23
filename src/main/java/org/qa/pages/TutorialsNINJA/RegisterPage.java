package org.qa.pages.TutorialsNINJA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

public class RegisterPage {

    private ActionDriver actionDriver;

    public RegisterPage(WebDriver driver) {
        this.actionDriver = BaseClass.getActionDriver();
    }

    // URL of success register
    String successRegister = "https://tutorialsninja.com/demo/index.php?route=account/success";

    // Locate Web Elements to access to Login page using class By
    private final By MyAccountDropDown = By.xpath("//span[text()='My Account']");
    private final By RegisterOption = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']");

    // Locate web element depending on register new customer
    // Web Element(s) of 'login page' Link
    private final By loginPageLink = By.cssSelector("div[id='content'] p a");
    // Web Element(s) of Heading(s) Title(s)
    private final By registerHeading1 = By.xpath("//div[@id='content']//h1");
    private final By personalDetailsLegend = By.xpath("//legend[normalize-space()='Your Personal Details']");
    private final By passwordLegend = By.xpath("//legend[normalize-space()='Your Password']");

    // Web Element(s) of Form register
    private final By firstNameField = By.id("input-firstname");
    private final By lastNameField = By.id("input-lastname");
    private final By EmailField = By.id("input-email");
    private final By telephoneField = By.id("input-telephone");
    private final By pwdField = By.id("input-password");
    private final By confirmPwdField = By.id("input-confirm");
    private final By privacyPolicy = By.xpath("//input[@type='checkbox']");
    private final By ContinueBtn = By.xpath("//input[@type='submit']");

    // Web Element(s) of success account
    private final By successHeading = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");
    private final By congratulationsHeading = By.xpath("//p[contains(text(),'Congratulations! Your new account has been success')]");

    // Method to pass to login page via Link
    public void clickLoginPageLink() {
        actionDriver.click(loginPageLink);
    }

    // Method to compare Headings Titles of register page
    public boolean compareRegisterHeadings(String expectedTitle) {
        return actionDriver.compareText(registerHeading1, expectedTitle);
    }

    public boolean comparePersonalDetailsLegend(String expectedDetails) {
        return actionDriver.compareText(personalDetailsLegend, expectedDetails);
    }

    public boolean comparePasswordLegend(String expectedLegend) {
        return actionDriver.compareText(passwordLegend, expectedLegend);
    }

    // Methods to compare success account headings and Congratulations Texts
    public boolean compareSuccessHeadings(String expectedTitle) {
        return actionDriver.compareText(successHeading, expectedTitle);
    }

    public boolean compareCongratulationsHeading(String expectedValue) {
        return actionDriver.compareText(congratulationsHeading, expectedValue);
    }

    // Method to open register page
    public void openRegisterPage() {
        actionDriver.click(MyAccountDropDown);
        actionDriver.click(RegisterOption);
    }

    // Method to enter First Name in register form
    public void enterFirstName(String firstName) {
        actionDriver.enter(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        actionDriver.enter(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        actionDriver.enter(EmailField, email);
    }

    public void enterTelephone(String telephone) {
        actionDriver.enter(telephoneField, telephone);
    }

    public void enterPassword(String password) {
        actionDriver.enter(pwdField, password);
    }

    public void enterConfirmPassword(String confirmPwd) {
        actionDriver.enter(confirmPwdField, confirmPwd);
    }

    public void clickPrivacyPolicy() {
        actionDriver.click(privacyPolicy);
    }

    public void clickOnContinue() {
        actionDriver.click(ContinueBtn);
    }

}
