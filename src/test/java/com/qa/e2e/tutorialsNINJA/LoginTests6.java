package com.qa.e2e.tutorialsNINJA;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.pages.TutorialsNINJA.MyAccountPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.qa.utilities.LTStatus.addLambdaStepContext;

public class LoginTests6 extends BaseClass {

    private LoginPage loginPage;
    private MyAccountPage myAccountPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());
    }


    @Test(priority = 1, description = "Verify Logging into the Application after changing the password")
    public void verifyLoggingAfterChangePwd() {
        // STEP 1: Go to login page and Login with valid credentials
        ExtentManager.logStep("TutorialsNINJA - Navigation to Login Page and connects with valid credentials");
        addLambdaStepContext(getDriver(), "LT - Navigation to Login Page and connects with valid credentials");
        loginPage.login("chakerbensaid1@gmail.com", "2U8@C7VGxvtx@r");
        // STEP 2: Click on 'Change your password' Link
        ExtentManager.logStep("TutorialsNINJA - Click on 'Change your password' Link");
        addLambdaStepContext(getDriver(), "LT - Click on 'Change your password' Link");
        myAccountPage.passToNewPwd();
        // STEP 3: Change new Password
        ExtentManager.logStep("TutorialsNINJA - Enter and change with new Password");
        addLambdaStepContext(getDriver(), "LT - Enter and change with new Password");
        myAccountPage.enterNewPwd("2U8@C7VGxvtx@r!!!");
        // STEP 4: Logout from System
        ExtentManager.logStep("TutorialsNINJA - Logout from system via Navbar");
        addLambdaStepContext(getDriver(), "LT - Logout from system via Navbar");
        loginPage.logoutViaDropDown();
        // STEP 5: Enter new credentials and Login Again
        ExtentManager.logStep("TutorialsNINJA - Login with new credentials");
        addLambdaStepContext(getDriver(), "LT - Login with new credentials");
        loginPage.login("chakerbensaid1@gmail.com", "2U8@C7VGxvtx@r!!!");
        // STEP 6: Check Login with new credentials
        ExtentManager.logStep("TutorialsNINJA - Verification of login with new credentials Terminated");
        addLambdaStepContext(getDriver(), "LT - Verification of login with new credentials Terminated");
        Assert.assertTrue(myAccountPage.compareMyAccountTitle("My Account"), "ERROR: Success message does not match the expected value");
        String ExpectedURL = "https://tutorialsninja.com/demo/index.php?route=account/account";
        String ActualURL = loginPage.returnCurrentURL();
        boolean isInSession = ActualURL.equals(ExpectedURL);
        Assert.assertTrue(isInSession, "ERROR: User not logging into application !");
    }
}
