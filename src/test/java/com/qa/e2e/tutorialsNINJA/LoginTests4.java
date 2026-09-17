package com.qa.e2e.tutorialsNINJA;

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

    @Test(priority = 1)
    public void verifyLogoutViaNavBar() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to Login Page and login with email and password");
        addLambdaStepContext(getDriver(), "Navigation to Login Page and login with email and password");
        loginPage.login("chakerbensaid1@gmail.com", "2U8@C7VGxvtx@r");
        ExtentManager.logStep("TutorialsNINJA - Click on My Account DropDown and Logout From System");
        addLambdaStepContext(getDriver(), "Click on My Account DropDown and Logout From System");
        loginPage.logoutViaDropDown();
        ExtentManager.logStep("TutorialsNINJA - Verification That user logged out");
        addLambdaStepContext(getDriver(), "Verification That user logged out");
        Assert.assertTrue(loginPage.compareLogoutMsg("Account Logout"), "ERROR: success logout message does not math the expected value");
        ExtentManager.logStep("TutorialsNINJA - Verification Of Logout URL");
        addLambdaStepContext(getDriver(), "Verification Of Logout URL");
        String ExpectedLogoutURL = "https://tutorialsninja.com/demo/index.php?route=account/account";
        String ActualCurrentURL = loginPage.returnCurrentURL();
        boolean isLogoutURL = ActualCurrentURL.equals(ExpectedLogoutURL);
        Assert.assertTrue(isLogoutURL, "ERROR: Current URL does not match the expected Logout URL");
        addLambdaStepContext(getDriver(), "LT- Verification Of Logout Terminated");
        ExtentManager.logStep("Verification Of Logout Terminated");
    }
}
