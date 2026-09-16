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

}
