package com.qa.e2e.tutorialsNINJA;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.pages.TutorialsNINJA.RegisterPage;
import org.qa.pages.TutorialsNINJA.RightColumnPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests9 extends BaseClass {

    private RegisterPage registerPage;
    private RightColumnPage rightColumnPage;
    private LoginPage loginPage;
    protected boolean isInLoginPage;


    @BeforeMethod
    public void setupPages() {
        registerPage = new RegisterPage(getDriver());
        rightColumnPage = new RightColumnPage(getDriver());
        loginPage = new LoginPage(getDriver());
    }

    @Test(priority = 1, description = "Verify the different ways of navigating to the Login page")
    public void verifyWaysToLoginPage() {
        // STEP 1: Click on 'Login Page' link in the 'Register Account' page
        loginPage.clickOnMyAccountDropmenu();
        loginPage.clickOnRegisterOption();
        registerPage.clickLoginPageLink();
        // STEP 2: (Way-1) Check Tne navigation to Login Page
        String ActualTitle1 = loginPage.getLoginTitleH2();
        String ActualTitle2 = loginPage.getLoginTitleSpan();
        isInLoginPage = ActualTitle1.equals("Returning Customer") && ActualTitle2.equals("I am a returning customer");
        Assert.assertTrue(isInLoginPage, "Error: User is not in login page without any title or text");
        // STEP 3: Click on 'Login' option from The Right Column
        loginPage.openPageWithURL("https://tutorialsninja.com/demo/index.php?route=account/register");
        rightColumnPage.clickLoginRightColumn();
        // STEP 4: (Way-2) Check The navigation to Login Page
        ActualTitle1 = loginPage.getLoginTitleH2();
        ActualTitle2 = loginPage.getLoginTitleSpan();
        isInLoginPage = ActualTitle1.equals("Returning Customer") && ActualTitle2.equals("I am a returning customer");
        Assert.assertTrue(isInLoginPage, "Error: User is not in login page without any title or text");
        // STEP 5: Select 'Login' option from 'My Account' Dropmenu
        loginPage.openPageWithURL("https://tutorialsninja.com/demo/");
        loginPage.clickOnMyAccountDropmenu();
        loginPage.clickOnLoginOption();
        // STEP 6: (Way-3) Check The navigation to Login Page
        ActualTitle1 = loginPage.getLoginTitleH2();
        ActualTitle2 = loginPage.getLoginTitleSpan();
        isInLoginPage = ActualTitle1.equals("Returning Customer") && ActualTitle2.equals("I am a returning customer");
        Assert.assertTrue(isInLoginPage, "Error: User is not in login page without any title or text");
    }
}
