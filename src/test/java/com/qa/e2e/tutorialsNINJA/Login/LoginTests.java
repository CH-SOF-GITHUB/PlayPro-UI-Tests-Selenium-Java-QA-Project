package com.qa.e2e.tutorialsNINJA.Login;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.pages.TutorialsNINJA.MyAccountPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseClass {

    private LoginPage loginPage;
    private MyAccountPage myAccountPagePage;

    String ExpectedMsg1 = "Warning: No match for E-Mail Address and/or Password.";
    String ExpectedMsg2 = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        myAccountPagePage = new MyAccountPage(getDriver());
    }

    @Test(priority = 1, description = "TC_LF_001", suiteName = "(TS_002) - Login Functionality")
    public void verifySuccessfulLoginWithValidCredentials() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to login page and enter email and password");
        loginPage.login("chakerbensaid1@gmail.com", "2U8@C7VGxvtx@r");
        ExtentManager.logStep("TutorialsNINJA - Comparison of success message if is correct or not");
        Assert.assertTrue(myAccountPagePage.compareMyAccountTitle("My Account"), "ERROR: Success message does not match the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
        loginPage.logout();
        Assert.assertTrue(loginPage.compareLogoutMsg("Account Logout"), "ERROR: success logout message does not math the expected value");
        ExtentManager.logStep("TutorialsNINJA - Logout successfully !");
        staticWait(2);
    }

    @Test(priority = 2, description = "TC_LF_002", suiteName = "(TS_002) - Login Functionality")
    public void shouldShowErrorMsgWithInvalidCredentials() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to login page and enter invalid email and password");
        loginPage.login("chakerbensaid11@gmail.com", "2U8@C7VGxvtx@rr!");
        ExtentManager.logStep("TutorialsNINJA - Login fails and an Error message should display");
        // Use Ternary Operator in my assertion between two expected messages
        boolean IsValidErrorMsg = loginPage.verifyErrorMessage(ExpectedMsg1) || loginPage.verifyErrorMessage(ExpectedMsg2);
        Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "ERROR: error login message not display !");
        Assert.assertTrue(IsValidErrorMsg, "ERROR: error login message does not match the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }

    @Test(priority = 3, description = "TC_LF_003", suiteName = "(TS_002) - Login Functionality")
    public void shouldShowErrorMsgWithInvalidEmail() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to login page and enter invalid email");
        loginPage.login("chakerbensaid11@gmail.com", "2U8@C7VGxvtx@r");
        ExtentManager.logStep("TutorialsNINJA - Login fails and an Error message should display");
        // Use Ternary Operator in my assertion between two expected messages
        boolean IsValidErrorMsg = loginPage.verifyErrorMessage(ExpectedMsg1) || loginPage.verifyErrorMessage(ExpectedMsg2);
        Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "ERROR: error login message not display !");
        Assert.assertTrue(IsValidErrorMsg, "ERROR: error login message does not match the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }

    @Test(priority = 4, description = "TC_LF_004", suiteName = "(TS_002) - Login Functionality")
    public void shouldShowErrorMsgWithInvalidPassword() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to login page and enter invalid password");
        loginPage.login("chakerbensaid1@gmail.com", "2U8@C7VGxvtx@rr!");
        ExtentManager.logStep("TutorialsNINJA - Login fails and an Error message should display");
        // Use Ternary Operator in my assertion between two expected messages
        boolean IsValidErrorMsg = loginPage.verifyErrorMessage(ExpectedMsg1) || loginPage.verifyErrorMessage(ExpectedMsg2);
        Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "ERROR: error login message not display !");
        Assert.assertTrue(IsValidErrorMsg, "ERROR: error login message does not match the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }

    @Test(priority = 5, description = "TC_LF_005", suiteName = "(TS_002) - Login Functionality")
    public void shouldShowErrorMshWithEmptyCredentials() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to login page and don't enter any email or password");
        loginPage.login("", "");
        ExtentManager.logStep("TutorialsNINJA - Login fails and an Error message should display");
        // Use Ternary Operator in my assertion between two expected messages
        boolean IsValidErrorMsg = loginPage.verifyErrorMessage(ExpectedMsg1) || loginPage.verifyErrorMessage(ExpectedMsg2);
        Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "ERROR: error login message not display !");
        Assert.assertTrue(IsValidErrorMsg, "ERROR: error login message does not match the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }

    @Test(priority = 6, description = "TC_LF_006", suiteName = "(TS_002) - Login Functionality")
    public void forgetPwdTest() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to login page");
        loginPage.openLoginPage();
        ExtentManager.logStep("TutorialsNINJA - 'Forgotten Password' Link should be display and visible");
        Assert.assertTrue(loginPage.isForgetPwdLinkDisplayed(), "ERROR: 'Forgotten Password' Link not display !");
        Assert.assertTrue(loginPage.compareForgetLinkPwd("Forgotten Password"), "ERROR: 'Forgotten Password' Link Text does not match the expected value !");
        ExtentManager.logStep("TutorialsNINJA - Navigation and Verification of Forgotten Password Page");
        loginPage.openForgetPwdPage();
        Assert.assertTrue(loginPage.isForgetPwdTitleDisplayed(), "ERROR: 'Forgotten Password' Title Page not display !");
        Assert.assertTrue(loginPage.compareForgetTitlePwd("Forgot Your Password?"), "ERROR: 'Forgotten Password' Title Page does not match the expected value !");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }
}
