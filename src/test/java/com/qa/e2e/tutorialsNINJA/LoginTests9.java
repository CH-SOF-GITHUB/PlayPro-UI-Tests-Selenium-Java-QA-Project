package com.qa.e2e.tutorialsNINJA;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.pages.TutorialsNINJA.MyAccountPage;
import org.qa.pages.TutorialsNINJA.RegisterPage;
import org.qa.pages.TutorialsNINJA.RightColumnPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.qa.utilities.LTStatus.addLambdaStepContext;

public class LoginTests9 extends BaseClass {

    private RegisterPage registerPage;
    private RightColumnPage rightColumnPage;
    private LoginPage loginPage;
    protected boolean isInLoginPage;
    private MyAccountPage myAccountPage;


    @BeforeMethod
    public void setupPages() {
        registerPage = new RegisterPage(getDriver());
        rightColumnPage = new RightColumnPage(getDriver());
        loginPage = new LoginPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());
    }

    @Test(priority = 1, description = "TC_LF_022")
    public void verifyWaysToLoginPage() {
        // STEP 1: Click on 'Login Page' link in the 'Register Account' page
        ExtentManager.logStep("TutorialsNINJA - Click on 'Login Page' link in the 'Register Account' page");
        loginPage.clickOnMyAccountDropmenu();
        loginPage.clickOnRegisterOption();
        registerPage.clickLoginPageLink();
        // STEP 2: (Way-1) Check Tne navigation to Login Page
        ExtentManager.logStep("TutorialsNINJA - (Way-1) Check Tne navigation to Login Page");
        String ActualTitle1 = loginPage.getLoginHeading1();
        String ActualTitle2 = loginPage.getLoginHeading2();
        isInLoginPage = ActualTitle1.equals("Returning Customer") && ActualTitle2.equals("I am a returning customer");
        Assert.assertTrue(isInLoginPage, "Error: User is not in login page without any title or text");
        // STEP 3: Click on 'Login' option from The Right Column
        ExtentManager.logStep("TutorialsNINJA - Click on 'Login' option from The Right Column");
        loginPage.openPageWithURL("https://tutorialsninja.com/demo/index.php?route=account/register");
        rightColumnPage.clickLoginRightColumn();
        // STEP 4: (Way-2) Check The navigation to Login Page
        ExtentManager.logStep("TutorialsNINJA - (Way-2) Check The navigation to Login Page");
        ActualTitle1 = loginPage.getLoginHeading1();
        ActualTitle2 = loginPage.getLoginHeading2();
        isInLoginPage = ActualTitle1.equals("Returning Customer") && ActualTitle2.equals("I am a returning customer");
        Assert.assertTrue(isInLoginPage, "Error: User is not in login page without any title or text");
        // STEP 5: Select 'Login' option from 'My Account' Dropmenu
        ExtentManager.logStep("TutorialsNINJA - Select 'Login' option from 'My Account' Dropmenu");
        loginPage.openPageWithURL("https://tutorialsninja.com/demo/");
        loginPage.clickOnMyAccountDropmenu();
        loginPage.clickOnLoginOption();
        // STEP 6: (Way-3) Check The navigation to Login Page
        ExtentManager.logStep("TutorialsNINJA - (Way-3) Check The navigation to Login Page");
        ActualTitle1 = loginPage.getLoginHeading1();
        ActualTitle2 = loginPage.getLoginHeading2();
        isInLoginPage = ActualTitle1.equals("Returning Customer") && ActualTitle2.equals("I am a returning customer");
        Assert.assertTrue(isInLoginPage, "Error: User is not in login page without any title or text");
        ExtentManager.logStep("TutorialsNINJA - Verification navigate to Login Page with 3 ways Terminated");
    }

    @Test(priority = 2, description = "TC_LF_023")
    public void verifyLoginPageBreadcrumbHeadingTitleAndURL() {
        // STEP 1: Click on 'My Account' in Dropmenu
        ExtentManager.logStep("TutorialsNINJA - Click on 'My Account' in Dropmenu");
        loginPage.clickOnMyAccountDropmenu();
        // STEP 2: Select 'Login' option
        ExtentManager.logStep("TutorialsNINJA - Select 'Login' option");
        loginPage.clickOnLoginOption();
        // STEP 3: Verify Proper Breadcrumb, Page Heading, Page URL and Page Title
        ExtentManager.logStep("TutorialsNINJA - Verify Proper Breadcrumb, Page Heading, Page URL and Page Title displayed");
        // Check Title
        String ActualTitle = loginPage.returnCurrentTitle();
        String ExpectedTitle = "Account Login";
        boolean isLoginTitle = ActualTitle.equals(ExpectedTitle);
        Assert.assertTrue(isLoginTitle, "Error: User is not in login page without any title");
        // Check Page URL
        String currentURL = loginPage.returnCurrentURL();
        Assert.assertTrue(currentURL.contains("route=account/login"), "Page URL is not correct");
        // Check proper breadcrumb
        List<String> breadcrumbs = loginPage.getProperBreadcrumb();
        String ActualProper1 = breadcrumbs.get(1);
        String ActualProper2 = breadcrumbs.get(2);
        boolean isProper1 = ActualProper1.equals("Account");
        boolean isProper2 = ActualProper2.equals("Login");
        Assert.assertTrue(isProper1, "Error: Actual breadcrumb proper does not match the expected");
        Assert.assertTrue(isProper2, "Error: Actual breadcrumb proper does not match the expected");
        // Check Page Headings
        String ActualH1 = loginPage.getLoginHeading1();
        String ActualH2 = loginPage.getLoginHeading2();
        boolean isHeadingsPage = ActualH1.equals("Returning Customer") && ActualH2.equals("I am a returning customer");
        Assert.assertTrue(isHeadingsPage, "Error: Headings Titles don't match the expected Titles");
        ExtentManager.logStep("TutorialsNINJA - Verification UI Page Terminated");
    }

    @Test(priority = 3, description = "TC_LF_025")
    public void verifyLoginInAllSupportedEnvironments() {
        // STEP 1: Click on 'My Account' in Dropmenu
        ExtentManager.logStep("TutorialsNINJA - Click on 'My Account' in Dropmenu");
        loginPage.clickOnMyAccountDropmenu();
        // STEP 2: Select 'Login' option
        ExtentManager.logStep("TutorialsNINJA - Select 'Login' option");
        loginPage.clickOnLoginOption();
        // STEP 3: Login with valid email and password
        ExtentManager.logStep("TutorialsNINJA - Login with valid email and password");
        loginPage.login("chakerbensaid1@yopmail.com", "Admin1234!");
        // STEP 4: Check logging into system and return of user account details
        ExtentManager.logStep("TutorialsNINJA - Check Login Functionality");
        List<String> contentHeadings = myAccountPage.getContentHeadings();
        String content1 = contentHeadings.get(0);
        String content2 = contentHeadings.get(1);
        String content3 = contentHeadings.get(2);
        String content4 = contentHeadings.get(3);
        boolean isContent1 = content1.equals("My Account");
        boolean isContent2 = content2.equals("My Orders");
        boolean isContent3 = content3.equals("My Affiliate Account");
        boolean isContent4 = content4.equals("Newsletter");
        Assert.assertTrue(isContent1, "Error: Content headings don't match the expected content");
        Assert.assertTrue(isContent2, "Error: Content headings don't match the expected content");
        Assert.assertTrue(isContent3, "Error: Content headings don't match the expected content");
        Assert.assertTrue(isContent4, "Error: Content headings don't match the expected content");
        // STEP 5:
        ExtentManager.logStep("TutorialsNINJA - Verification Login Functionality In all supported Environments");
    }

}
