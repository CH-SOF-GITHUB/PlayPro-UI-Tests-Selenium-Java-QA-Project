package com.qa.e2e.tutorialsNINJA.TS_001;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTests1 extends BaseClass {

    // (TS_001) Register Functionality

    private RegisterPage registerPage;

    @BeforeMethod
    public void setupPages() {
        registerPage = new RegisterPage(getDriver());
    }

    @Test(priority = 1, description = "TC_RF_001", suiteName = "(TS_001) - Register Functionality")
    public void registerWithMandatoryFields() {
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
        registerPage.enterFirstName("John");
        // Enter The Last Name
        registerPage.enterLastName("Anderson");
        // Enter The E-mail
        registerPage.enterEmail("chakerbensaid12@yopmail.com");
        // Enter The Telephone
        registerPage.enterTelephone("21622114567");
        // Enter The Password
        registerPage.enterPassword("QaTest@1122");
        // Enter The Password Confirm
        registerPage.enterConfirmPassword("QaTest@1122");
        // select privacy policy option before terminate register
        registerPage.clickPrivacyPolicy();
        // Terminate register
        registerPage.clickOnContinue();
        // Check That a new account registered
        boolean isSuccessRegister1 = registerPage.compareSuccessHeadings("Your Account Has Been Created!");
        Assert.assertTrue(isSuccessRegister1, "error in register account & success text does not display");
        isSuccessRegister1 = registerPage.compareCongratulationsHeading("Congratulations! Your new account has been successfully created!");
        Assert.assertTrue(isSuccessRegister1, "error in register account & congratulations text does not display");
    }
}
