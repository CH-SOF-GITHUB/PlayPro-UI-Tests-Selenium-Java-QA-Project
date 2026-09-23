package com.qa.e2e.tutorialsNINJA.TS_001;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTests3 extends BaseClass {

    private RegisterPage registerPage;

    @BeforeMethod
    public void setupPages() {
        registerPage = new RegisterPage(getDriver());
    }

    @Test(priority = 1, description = "TC_RF_004", suiteName = "(TS_001) - Register Functionality")
    public void verifyProperNotifications() {
        // Click on 'My Account' Link and 'Register' option to pass to register page
        registerPage.openRegisterPage();
        // Check you are in register page
        boolean isInRegisterPage = registerPage.compareRegisterHeadings("Register Account");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        isInRegisterPage = registerPage.comparePersonalDetailsLegend("Your Personal Details");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        isInRegisterPage = registerPage.comparePasswordLegend("Your Password");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        // Enter The First Name
        registerPage.enterFirstName("");
        // Enter The Last Name
        registerPage.enterLastName("");
        // Enter The E-mail
        registerPage.enterEmail("");
        // Enter The Telephone
        registerPage.enterTelephone("");
        // Enter The Password
        registerPage.enterPassword("");
        // Enter The Password Confirm
        registerPage.enterConfirmPassword("");
        // Terminate register
        registerPage.clickOnContinue();
        // Check proper notification messages are displayed when don't provide any fields
        boolean isDisplayed = registerPage.isFirstNameNotifDisplayed();
        Assert.assertTrue(isDisplayed, "The bellow warning message is not displayed!");
        isDisplayed = registerPage.isLastNameNotifDisplayed();
        Assert.assertTrue(isDisplayed, "The bellow warning message is not displayed!");
        isDisplayed = registerPage.isEmailNotifDisplayed();
        Assert.assertTrue(isDisplayed, "The bellow warning message is not displayed!");
        isDisplayed = registerPage.isPhoneNotifDisplayed();
        Assert.assertTrue(isDisplayed, "The bellow warning message is not displayed!");
        isDisplayed = registerPage.isPwdNotifDisplayed();
        Assert.assertTrue(isDisplayed, "The bellow warning message is not displayed!");
        isDisplayed = registerPage.isPrivacyNotifDisplayed();
        Assert.assertTrue(isDisplayed, "The bellow warning message is not displayed!");
    }
}
