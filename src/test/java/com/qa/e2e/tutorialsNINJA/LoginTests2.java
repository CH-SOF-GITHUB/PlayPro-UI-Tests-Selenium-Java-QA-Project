package com.qa.e2e.tutorialsNINJA;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.pages.TutorialsNINJA.MyAccountPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests2 extends BaseClass {
    private LoginPage loginPage;
    private MyAccountPage myAccountPagePage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        myAccountPagePage = new MyAccountPage(getDriver());
    }

    @Test(priority = 1)
    public void validLoginViaTab() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to Login Page and Press TAB Key to enter email and password and to click on Login Btn");
        loginPage.loginViaTab("chakerbensaid1@gmail.com", "2U8@C7VGxvtx@r");
        ExtentManager.logStep("TutorialsNINJA - Comparison oF success message if is correct or not");
        Assert.assertTrue(myAccountPagePage.compareMyAccountTitle("My Account"), "ERROR: Success message does not match the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
        loginPage.logout();
        Assert.assertTrue(loginPage.compareLogoutMsg("Account Logout"), "ERROR: success logout message does not math the expected value");
        ExtentManager.logStep("TutorialsNINJA - Logout successfully !");
        staticWait(2);
    }

    @Test(priority = 2)
    public void verifyEmailAndPwdPlaceholder() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to Login Page");
        loginPage.openLoginPage();
        ExtentManager.logStep("TutorialsNINJA - Check Email field has the place holder text");
        String ExpectedEmailPlaceholder = "E-Mail Address";
        Assert.assertTrue(loginPage.isEEmailHavePlaceholder(ExpectedEmailPlaceholder), "Email Field does not have the place holder Text");
        ExtentManager.logStep("TutorialsNINJA - Check Password field has the place holder text");
        String ExpectedPwdPlaceholder = "Password";
        Assert.assertTrue(loginPage.isPwdHavePlaceholder(ExpectedPwdPlaceholder), "Password Field does not have the place holder Text");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
        staticWait(2);
    }


    @Test(priority = 3, description = "Check Login system when browser back")
    public void verifySystemLoginAfterBrowserBack() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to Login Page and login with email and password");
        loginPage.login("chakerbensaid11@gmail.com", "2U8@C7VGxvtx@rr!");
        ExtentManager.logStep("TutorialsNINJA - Browsing back in page");
        loginPage.backBrowser();
        ExtentManager.logStep("TutorialsNINJA - Verification that should not logout");
        String ExpectedLoginURL = "https://tutorialsninja.com/demo/index.php?route=account/login";
        String ActualCurrentURL = loginPage.returnCurrentURL();
        boolean isLoginURL = ActualCurrentURL.equals(ExpectedLoginURL);
        Assert.assertFalse(isLoginURL, "ERROR: Current URL match the expected Login URL");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }

}
