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

    @Test(priority = 1, description = "Check Login system when browser back")
    public void verifyLoginByBrowserBack() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to Login Page and login with email and password");
        loginPage.login("chakerbensaid1@gmail.com", "2U8@C7VGxvtx@r");
        ExtentManager.logStep("TutorialsNINJA - Browsing back in page");
        loginPage.backBrowser();
        ExtentManager.logStep("TutorialsNINJA - Verification that should not logout");
        String ExpectedLoginURL = "https://tutorialsninja.com/demo/index.php?route=account/login";
        String ActualCurrentURL = loginPage.returnCurrentURL();
        boolean isLoginURL = ActualCurrentURL.equals(ExpectedLoginURL);
        Assert.assertFalse(isLoginURL, "ERROR: Current URL match the expected Login URL");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }

    @Test(priority = 2, description = "Check After Logout when browser back not logging again")
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
        addLambdaStepContext(getDriver(), "LT-Verification Of Logout By Back Browser Terminated");
        ExtentManager.logStep("Verification Of Logout By Back Browser Terminated");
        staticWait(5);
    }

    @Test(priority = 3, description = "Verify Logging into the Application through browsing back and Loading page")
    public void verifyLoginByBrowserBackAndLoadPage() {
        ExtentManager.logStep("TutorialsNINJA - Navigation to Login Page and Login with email and password");
        loginPage.login("chakerbensaid1@gmail.com", "2U8@C7VGxvtx@r");
        ExtentManager.logStep("TutorialsNINJA - Browsing back in page");
        loginPage.backBrowser();
        ExtentManager.logStep("TutorialsNINJA - Verification That User should not logout");
        loginPage.refreshCurrentPage();
        staticWait(5);
        String ExpectedLoginOKURL = "https://tutorialsninja.com/demo/index.php?route=account/account";
        String ActualCurrentURL = loginPage.returnCurrentURL();
        boolean isLoginOKURL = ActualCurrentURL.equals(ExpectedLoginOKURL);
        Assert.assertTrue(isLoginOKURL, "ERROR: Current URL match the expected Login URL");
        ExtentManager.logStep("TutorialsNINJA - Verification is terminated !");
    }

}
