package com.qa.e2e.tutorialsNINJA.TS_001;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.RegisterPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTests8 extends BaseClass {

    private RegisterPage registerPage;

    @BeforeMethod
    public void setupPages() {
        registerPage = new RegisterPage(getDriver());
    }

    @Test(invocationCount = 2, dependsOnMethods = "verifyRegistrationWithInvalidEmailFormat", description = "TC_RF_009", suiteName = "(TS_001) - Register Functionality")
    public void verifyRegistrationWithExistAccount() {
        // Click on 'My Account' Link and 'Register' option to pass to register page
        ExtentManager.logStep("STEP 1 - Navigate to Register Page");
        registerPage.openRegisterPage();
        // Check you are in register page
        ExtentManager.logStep("STEP 2 - Verify That User in Register Page");
        boolean isInRegisterPage = registerPage.compareRegisterHeadings("Register Account");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        isInRegisterPage = registerPage.comparePersonalDetailsLegend("Your Personal Details");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        isInRegisterPage = registerPage.comparePasswordLegend("Your Password");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        // Enter The First Name
        ExtentManager.logStep("STEP 3 - Enter The First Name");
        registerPage.enterFirstName("John");
        // Enter The Last Name
        ExtentManager.logStep("STEP 4 - Enter The Last Name");
        registerPage.enterLastName("Anderson");
        // Enter The E-mail
        ExtentManager.logStep("STEP 5 - Enter The Existing E-Mail");
        registerPage.enterEmail("chaker68@yopmail.com");
        // Enter The Telephone
        ExtentManager.logStep("STEP 6 - Enter The Telephone");
        registerPage.enterTelephone("21622114588");
        // Enter The Password
        ExtentManager.logStep("STEP 7 - Enter The Password");
        registerPage.enterPassword("QaTest@1122");
        // Enter The Password Confirm
        ExtentManager.logStep("STEP 8 - Enter The Confirm Password");
        registerPage.enterConfirmPassword("QaTest@1122");
        // Click on "Yes" radio option for Newsletter
        ExtentManager.logStep("STEP 9 - Click on \"Yes\" radio option for Newsletter");
        registerPage.selectSubscribeYES();
        // select privacy policy option before terminate register
        ExtentManager.logStep("STEP 10 - Select privacy policy option");
        registerPage.clickPrivacyPolicy();
        // Terminate register
        ExtentManager.logStep("STEP 11 - Click on Submit Button");
        registerPage.clickOnContinue();
        // Check That Account not be created and an error message displays to explain Email already exists
        ExtentManager.logStep("STEP 12 - Check That Account not created and A Existant E-mail warning message got displayed");
        boolean isEmailExist = registerPage.isExistEmailNotifDisplayed();
        Assert.assertTrue(isEmailExist, "Error: Email not exists before and it can make a problem in flow registration");
        String EmailExistText = registerPage.getEmailExistMessage();
        Assert.assertEquals(EmailExistText, "Warning: E-Mail Address is already registered!", "Error: Email already exists with text not displayed");
    }

    @Test(description = "TC_RF_010", suiteName = "(TS_001) - Register Functionality")
    public void verifyRegistrationWithInvalidEmailFormat() {
        // Click on 'My Account' Link and 'Register' option to pass to register page
        ExtentManager.logStep("STEP 1 - Navigate to Register Page");
        registerPage.openRegisterPage();
        // Check you are in register page
        ExtentManager.logStep("STEP 2 - Verify That User in Register Page");
        boolean isInRegisterPage = registerPage.compareRegisterHeadings("Register Account");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        isInRegisterPage = registerPage.comparePersonalDetailsLegend("Your Personal Details");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        isInRegisterPage = registerPage.comparePasswordLegend("Your Password");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        // Enter The First Name
        ExtentManager.logStep("STEP 3 - Enter The First Name");
        registerPage.enterFirstName("John");
        // Enter The Last Name
        ExtentManager.logStep("STEP 4 - Enter The Last Name");
        registerPage.enterLastName("Anderson");
        // Enter The E-mail
        ExtentManager.logStep("STEP 5 - Enter The Existing E-Mail");
        registerPage.enterEmail("chaker@gmail");
        // Enter The Telephone
        ExtentManager.logStep("STEP 6 - Enter The Telephone");
        registerPage.enterTelephone("21622114588");
        // Enter The Password
        ExtentManager.logStep("STEP 7 - Enter The Password");
        registerPage.enterPassword("QaTest@1122");
        // Enter The Password Confirm
        ExtentManager.logStep("STEP 8 - Enter The Confirm Password");
        registerPage.enterConfirmPassword("QaTest@1122");
        // Click on "Yes" radio option for Newsletter
        ExtentManager.logStep("STEP 9 - Click on \"Yes\" radio option for Newsletter");
        registerPage.selectSubscribeYES();
        // select privacy policy option before terminate register
        ExtentManager.logStep("STEP 10 - Select privacy policy option");
        registerPage.clickPrivacyPolicy();
        // Terminate register
        ExtentManager.logStep("STEP 11 - Click on Submit Button");
        registerPage.clickOnContinue();
        // Check That Account not be created and an error message displays to explain Email already exists
        ExtentManager.logStep("STEP 12 - Check That Account not created and A Mal Format E-mail warning message got displayed");
        boolean isEmailUnformated = registerPage.isEmailUnformatedMsgDisplayed();
        Assert.assertTrue(isEmailUnformated, "Error: Email Mal Format Message not exists before and it can make a problem in flow registration");
        String EmailUnformatedText = registerPage.getEmailUnformatedMessage();
        Assert.assertEquals(EmailUnformatedText, "E-Mail Address does not appear to be valid!", "Error: Email Mal Format with text not displayed");
    }

    @Test(dependsOnMethods = "verifyRegistrationWithExistAccount", description = "TC_RF_011", suiteName = "(TS_001) - Register Functionality")
    public void verifyRegistrationWithInvalidPhoneFormat() {
        // Click on 'My Account' Link and 'Register' option to pass to register page
        ExtentManager.logStep("STEP 1 - Navigate to Register Page");
        registerPage.openRegisterPage();
        // Check you are in register page
        ExtentManager.logStep("STEP 2 - Verify That User in Register Page");
        boolean isInRegisterPage = registerPage.compareRegisterHeadings("Register Account");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        isInRegisterPage = registerPage.comparePersonalDetailsLegend("Your Personal Details");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        isInRegisterPage = registerPage.comparePasswordLegend("Your Password");
        Assert.assertTrue(isInRegisterPage, "Error: user is not in register page");
        // Enter The First Name
        ExtentManager.logStep("STEP 3 - Enter The First Name");
        registerPage.enterFirstName("John");
        // Enter The Last Name
        ExtentManager.logStep("STEP 4 - Enter The Last Name");
        registerPage.enterLastName("Anderson");
        // Enter The E-mail
        ExtentManager.logStep("STEP 5 - Enter The Existing E-Mail");
        registerPage.enterEmail("chakerTesting@gmail.com");
        // Enter The Telephone
        ExtentManager.logStep("STEP 6 - Enter The Telephone");
        registerPage.enterTelephone("abcde");
        // Enter The Password
        ExtentManager.logStep("STEP 7 - Enter The Password");
        registerPage.enterPassword("QaTest@1122");
        // Enter The Password Confirm
        ExtentManager.logStep("STEP 8 - Enter The Confirm Password");
        registerPage.enterConfirmPassword("QaTest@1122");
        // Click on "Yes" radio option for Newsletter
        ExtentManager.logStep("STEP 9 - Click on \"Yes\" radio option for Newsletter");
        registerPage.selectSubscribeYES();
        // select privacy policy option before terminate register
        ExtentManager.logStep("STEP 10 - Select privacy policy option");
        registerPage.clickPrivacyPolicy();
        // Terminate register
        ExtentManager.logStep("STEP 11 - Click on Submit Button");
        registerPage.clickOnContinue();
        // Check That Account not be created and an error message displays to explain Email already exists
        ExtentManager.logStep("STEP 12 - Check That Account not created and A Telephone Mal Format warning message got displayed");
        boolean isPhoneUnformated = registerPage.isPhoneUnformatedMessageDisplayed();
        Assert.assertTrue(isPhoneUnformated, "Error: Telephone Mal Format Message not exists before and it can make a problem in flow registration");
    }
}
