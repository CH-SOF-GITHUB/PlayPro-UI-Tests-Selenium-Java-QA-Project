package com.qa.e2e.tutorialsNINJA.TS_001;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.pages.TutorialsNINJA.NewsletterPage;
import org.qa.pages.TutorialsNINJA.RegisterPage;
import org.qa.pages.TutorialsNINJA.RightColumnPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTests7 extends BaseClass {

    private RegisterPage registerPage;
    private NewsletterPage newsletterPage;
    private RightColumnPage rightColumnPage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setupPages() {
        registerPage = new RegisterPage(getDriver());
        newsletterPage = new NewsletterPage(getDriver());
        rightColumnPage = new RightColumnPage(getDriver());
        loginPage = new LoginPage(getDriver());
    }

    @Test(priority = 1, description = "TC_RF_008", suiteName = "(TS_001) - Register Functionality")
    public void verifyNotificationOfConfirmPwd() {
        // Click on 'My Account' Link and 'Register' option to pass to register page
        ExtentManager.logStep("TutorialsNINJA - Pass to Register Page");
        registerPage.openRegisterPage();
        // Check you are in register page
        ExtentManager.logStep("TutorialsNINJA - Verify User in Register Page");
        boolean isInRegisterPage = registerPage.compareRegisterHeadings("Register Account");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        isInRegisterPage = registerPage.comparePersonalDetailsLegend("Your Personal Details");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        isInRegisterPage = registerPage.comparePasswordLegend("Your Password");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        // Enter The First Name
        ExtentManager.logStep("TutorialsNINJA - Enter The First Name");
        registerPage.enterFirstName("John");
        // Enter The Last Name
        ExtentManager.logStep("TutorialsNINJA - Enter The Last Name");
        registerPage.enterLastName("Anderson");
        // Enter The E-mail
        ExtentManager.logStep("TutorialsNINJA - Enter The E-Mail");
        registerPage.enterEmail("confirmpwd@yopmail.com");
        // Enter The Telephone
        ExtentManager.logStep("TutorialsNINJA - Enter The Telephone");
        registerPage.enterTelephone("21622114588");
        // Enter The Password
        ExtentManager.logStep("TutorialsNINJA - Enter The Password");
        registerPage.enterPassword("QaTest@1122");
        // Enter The Password Confirm
        ExtentManager.logStep("TutorialsNINJA - Enter The Confirm Password");
        registerPage.enterConfirmPassword("abcdef12");
        // Click on "Yes" radio option for Newsletter
        ExtentManager.logStep("TutorialsNINJA - Click on \"Yes\" radio option for Newsletter");
        registerPage.selectSubscribeYES();
        // select privacy policy option before terminate register
        ExtentManager.logStep("TutorialsNINJA - Select privacy policy option");
        registerPage.clickPrivacyPolicy();
        // Terminate register
        ExtentManager.logStep("TutorialsNINJA - Click on Submit Button");
        registerPage.clickOnContinue();
        // Check That Account not be created and an error message displays under confirm password field
        boolean isConfirmErrorDisplayed = registerPage.isConfirmPwdNotifDisplayed();
        Assert.assertTrue(isConfirmErrorDisplayed, "Confirm password notification message not displayed");
        String ConfirmErrorText = registerPage.getConfirmPwdMessage();
        Assert.assertEquals(ConfirmErrorText, "Password confirmation does not match password!", "Error to check text of Confirm password notification message");
        ExtentManager.logStep("TutorialsNINJA - Check That Account not be created and an error message displays under confirm password field");
    }
}
