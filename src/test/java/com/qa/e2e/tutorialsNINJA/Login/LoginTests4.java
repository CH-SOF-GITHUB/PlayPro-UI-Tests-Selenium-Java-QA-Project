package com.qa.e2e.tutorialsNINJA.Login;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.qa.utilities.LTStatus.addLambdaStepContext;

public class LoginTests4 extends BaseClass {

    private LoginPage loginPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
    }

    @Test(priority = 1, suiteName = "(TS_002) - Login Functionality")
    public void verifyLogoutViaNavBar() {
        ExtentManager.logStep("TutorialsNINJA - Login with valid credentials");
        addLambdaStepContext(getDriver(), "LT - Login with valid credentials");
        loginPage.login("chakerbensaid1@gmail.com", "2U8@C7VGxvtx@r");
        ExtentManager.logStep("TutorialsNINJA - Click on My Account DropDown and Logout From System");
        addLambdaStepContext(getDriver(), "LT - Click on My Account DropDown and Logout From System");
        loginPage.logoutViaDropDown();
        ExtentManager.logStep("TutorialsNINJA - Verification That user logged out");
        addLambdaStepContext(getDriver(), "LT - Verification That user logged out");
        Assert.assertTrue(loginPage.compareLogoutMsg("Account Logout"), "ERROR: success logout message does not math the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification Of Logout URL");
        addLambdaStepContext(getDriver(), "LT - Verification Of Logout URL via NavBar");
        String ExpectedLogoutURL = "https://tutorialsninja.com/demo/index.php?route=account/logout";
        String ActualCurrentURL = loginPage.returnCurrentURL();
        boolean isLogoutURL = ActualCurrentURL.equals(ExpectedLogoutURL);
        Assert.assertTrue(isLogoutURL, "ERROR: Current URL Logout does not match the expected Logout URL");
        ExtentManager.logStep("Verification Of Logout Via Navbar Terminated !");
        addLambdaStepContext(getDriver(), "LT- Verification Of Logout Via Navbar Terminated !");
    }

    @Test(priority = 2, description = "TC_LF_014", suiteName = "(TS_002) - Login Functionality")
    public void verifyNbOfUnsuccessfulLoginAttemps() {
        ExtentManager.logStep("TutorialsNINJA - Login with Invalid credentials");
        addLambdaStepContext(getDriver(), "LT - Login with Invalid credentials");
        loginPage.login("testing@gmail.com", "2U8@C7VGxvtx@r");
        ExtentManager.logStep("TutorialsNINJA - Click on Login Button multiple times");
        addLambdaStepContext(getDriver(), "Click on Login Button multiple times");
        loginPage.clickMultipleLogin(5);
        ExtentManager.logStep("TutorialsNINJA - Warning Error Msg displays for The 5th time");
        addLambdaStepContext(getDriver(), "LT - Warning Error Msg displays for The 5th time");
        Assert.assertTrue(loginPage.errorMsgIsDisplayed(), "Warning Error Message does not display !");
        String ExpectedWarnMsg = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
        Assert.assertTrue(loginPage.verifyErrorMessage(ExpectedWarnMsg), "ERROR: Warn Attemps message does not math the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification of unsuccessful Login Attemps at 5th time Terminated !");
        addLambdaStepContext(getDriver(), "LT - Verification of unsuccessful Login Attemps at 5th time Terminated !");
    }
}
