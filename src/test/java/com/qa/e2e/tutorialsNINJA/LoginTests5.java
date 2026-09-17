package com.qa.e2e.tutorialsNINJA;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.qa.utilities.LTStatus.addLambdaStepContext;

public class LoginTests5 extends BaseClass {

    private LoginPage loginPage;


    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
    }


    @Test
    public void verifyLogoutByBackBrowser() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to Login Page and login with email and password");
        addLambdaStepContext(getDriver(), "Navigation to Login Page and login with email and password");
        loginPage.login("chakerbensaid1@gmail.com", "2U8@C7VGxvtx@r");
        ExtentManager.logStep("TutorialsNINJA - Logout via Navbar(My Account)");
        addLambdaStepContext(getDriver(), "Logout via Navbar(My Account)");
        loginPage.logoutViaDropDown();
        ExtentManager.logStep("TutorialsNINJA - Browsing Back");
        addLambdaStepContext(getDriver(), "Browsing Back");
        loginPage.backBrowser();
        ExtentManager.logStep("TutorialsNINJA - Verify That User Not Logged in again");
        addLambdaStepContext(getDriver(), "Verify That User Not Logged in again");
        // String ExpectedURL = "https://tutorialsninja.com/demo/index.php?route=account/login";
        String ActualCurrentURL = loginPage.returnCurrentURL();
        boolean isLogoutURL = ActualCurrentURL.contains("login");
        Assert.assertTrue(isLogoutURL, "ERROR: Current URL does not match the expected URL");
        addLambdaStepContext(getDriver(), "LT- Verification Of Logout By Back Browser Terminated");
        ExtentManager.logStep("Verification Of Logout By Back Browser Terminated");
        staticWait(5);
    }
}
