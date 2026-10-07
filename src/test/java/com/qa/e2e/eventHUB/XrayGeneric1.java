package com.qa.e2e.eventHUB;


import org.qa.base.BaseClass;
import org.qa.pages.EventHUB.LoginPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class XrayGeneric1 extends BaseClass {

    LoginPage loginPage;

    // @BeforeSuite: The annotation method will be run before all tests in this suite have run.
    @BeforeSuite
    public void configureLogs() {
        // AJOUT : masquer les logs Selenium INFO/WARNING
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket").setLevel(Level.SEVERE);
    }

    @BeforeMethod(alwaysRun = true)
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
    }

    @Test(priority = 1, description = "Verify login with invalid email and valid password")
    public void VerifyLoginWithInvalidEmail() {
        // STEP 1: Open the login page
        ExtentManager.logStep("EventHUB - Open the login page");
        loginPage.openLoginPage();
        // STEP 2: Enter invalid email and valid password, then check for error message
        ExtentManager.logStep("EventHUB - Enter invalid email and valid password, then click on Sign In button");
        loginPage.enterCredentials("bchaker289@yahoo.com", "Q5n@j!i!QnZQmYm");
        // STEP 3: Check for error message
        ExtentManager.logStep("EventHUB - Check for invalid email error message");
        loginPage.checkPwdOrEmailErrorMessageByText("Invalid email or password");
        boolean isErrorMessageCorrect = loginPage.checkPwdOrEmailErrorMessageByText("Invalid email or password");
        Assert.assertTrue(isErrorMessageCorrect, "Expected error message with invalid email login !");
        // STEP 4: Terminate the Verification
        ExtentManager.logStep("EventHUB - Verification Terminated");
    }
}
