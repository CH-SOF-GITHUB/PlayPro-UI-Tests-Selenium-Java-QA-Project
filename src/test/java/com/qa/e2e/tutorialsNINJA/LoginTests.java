package com.qa.e2e.tutorialsNINJA;

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

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        myAccountPagePage = new MyAccountPage(getDriver());
    }

    @Test(priority = 1)
    public void verifySuccessfulLoginWithValidCredentials() {
        String email = BaseClass.email;
        String pwd = BaseClass.password;
        ExtentManager.logStep("TutorialsNINJA - Navigation to login page and enter email and password");
        loginPage.login(email, pwd);
        ExtentManager.logStep("TutorialsNINJA - Comparison of success message if is correct or not");
        Assert.assertTrue(myAccountPagePage.compareMyAccountTitle("My Account"), "ERROR: Success message does not match the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
        loginPage.logout();
        Assert.assertTrue(loginPage.compareLogoutMsg("Account Logout"), "ERROR: success logout message does not math the expected value");
        ExtentManager.logStep("TutorialsNINJA - Logout successfully !");
        staticWait(2);
    }

    @Test(priority = 2)
    public void shouldShowErrorMsgWithInvalidCredentials() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to login page and enter invalid email and password");
        loginPage.login("chakerbensaid11@gmail.com", "2U8@C7VGxvtx@rr!");
        ExtentManager.logStep("TutorialsNINJA - Login fails and an Error message should display");
        Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "ERROR: error login message not display !");
        Assert.assertTrue(loginPage.verifyErrorMessage("Warning: No match for E-Mail Address and/or Password."), "ERROR: error login message does not match the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }

    @Test(priority = 3)
    public void shouldShowErrorMsgWithInvalidEmail() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to login page and enter invalid email");
        loginPage.login("chakerbensaid11@gmail.com", "2U8@C7VGxvtx@r");
        ExtentManager.logStep("TutorialsNINJA - Login fails and an Error message should display");
        Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "ERROR: error login message not display !");
        Assert.assertTrue(loginPage.verifyErrorMessage("Warning: No match for E-Mail Address and/or Password."), "ERROR: error login message does not match the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }

    @Test(priority = 4)
    public void shouldShowErrorMsgWithInvalidPassword() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to login page and enter invalid password");
        loginPage.login("chakerbensaid1@gmail.com", "2U8@C7VGxvtx@rr!");
        ExtentManager.logStep("TutorialsNINJA - Login fails and an Error message should display");
        Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "ERROR: error login message not display !");
        Assert.assertTrue(loginPage.verifyErrorMessage("Warning: No match for E-Mail Address and/or Password."), "ERROR: error login message does not match the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }

    @Test(priority = 5)
    public void shouldShowErrorMshWithEmptyCredentials() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to login page and don't enter any email or password");
        loginPage.login("", "");
        ExtentManager.logStep("TutorialsNINJA - Login fails and an Error message should display");
        Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "ERROR: error login message not display !");
        Assert.assertTrue(loginPage.verifyErrorMessage("Warning: No match for E-Mail Address and/or Password."), "ERROR: error login message does not match the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }
}
