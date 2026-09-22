package com.qa.e2e.tutorialsNINJA.TS_002;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.pages.TutorialsNINJA.MyAccountPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.qa.utilities.LTStatus.addLambdaStepContext;

public class LoginTests2 extends BaseClass {
    private LoginPage loginPage;
    private MyAccountPage myAccountPagePage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        myAccountPagePage = new MyAccountPage(getDriver());
    }

    @Test(priority = 1, description = "TC_LF_007", suiteName = "(TS_002) - Login Functionality")
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

    @Test(priority = 2, description = "TC_LF_008", suiteName = "(TS_002) - Login Functionality")
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

    @Test(priority = 3, description = "TC_LF_013", suiteName = "(TS_002) - Login Functionality")
    public void verifyTextIntoPwdHideItsVisibility() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to Login Page");
        addLambdaStepContext(getDriver(), "LT - Navigation to Login Page");
        loginPage.openLoginPage();
        ExtentManager.logStep("TutorialsNINJA - Enter Text In Password Field & Check is toggled to hide its visibility");
        addLambdaStepContext(getDriver(), "LT - Enter Text In Password Field & Check is toggled to hide its visibility");
        loginPage.isHidePwdVisibility("2U8@C7VGxvtx@r", "type", "password");
        ExtentManager.logStep("TutorialsNINJA - Verification Pwd Field hide its visibility Terminated !");
        addLambdaStepContext(getDriver(), "LT - Verification Pwd Field hide its visibility Terminated !");
    }
}
