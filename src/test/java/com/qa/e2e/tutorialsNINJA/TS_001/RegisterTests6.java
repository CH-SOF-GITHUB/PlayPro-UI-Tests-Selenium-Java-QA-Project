package com.qa.e2e.tutorialsNINJA.TS_001;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.pages.TutorialsNINJA.NewsletterPage;
import org.qa.pages.TutorialsNINJA.RegisterPage;
import org.qa.pages.TutorialsNINJA.RightColumnPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTests6 extends BaseClass {

    private RegisterPage registerPage;
    private NewsletterPage newsletterPage;
    private RightColumnPage rightColumnPage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setupPages() {
        registerPage = new RegisterPage(getDriver());
        newsletterPage = new NewsletterPage(getDriver());
        rightColumnPage = new RightColumnPage(getDriver());
        loginPage = new LoginPage(getDriver());
    }

    @Test(priority = 1, description = "TC_RF_007", suiteName = "(TS_001) - Register Functionality")
    public void verifyWaysToRegisterPage() {
        // STEP 1: 1. Click on 'My Account' Drop menu
        //         2. Click on 'Register' option (ER-1)
        ExtentManager.logStep("TutorialsNINJA - Click on 'My Account' Drop menu and on 'Register' option");
        registerPage.openRegisterPage();
        // STEP 2: Check That User is in Register Page
        ExtentManager.logStep("TutorialsNINJA - Check That User is in Register Page");
        String ActualURL = getDriver().getCurrentUrl();
        Assert.assertEquals(ActualURL, "https://tutorialsninja.com/demo/index.php?route=account/register", "User is not in Register page !");
        // STEP 3: 3. Click on 'My Account' Drop menu
        //         4. Click on 'Login' option
        ExtentManager.logStep("TutorialsNINJA - Click on 'My Account' Drop menu and on 'Login' option");
        loginPage.openLoginPage();
        // STEP 4: Click  on 'Continue' button inside 'New Customer' box
        ExtentManager.logStep("TutorialsNINJA - Click  on 'Continue' button inside 'New Customer' box");
        loginPage.clickOnContinue();
        // STEP 5: Check That User is in Register Page
        ExtentManager.logStep("TutorialsNINJA - Check That User is in Register Page");
        Assert.assertEquals(ActualURL, "https://tutorialsninja.com/demo/index.php?route=account/register", "User is not in Register page !");
        // STEP 6: 3. Click on 'My Account' Drop menu
        //         4. Click on 'Login' option
        ExtentManager.logStep("TutorialsNINJA - Click on 'My Account' Drop menu and on 'Login' option");
        loginPage.openLoginPage();
        // STEP 7: Click on 'Register' option in right column
        ExtentManager.logStep("TutorialsNINJA - Click on 'Register' option in right column");
        rightColumnPage.clickRegisterRightColumn();
        // STEP 8: Check That User is in Register Page
        ExtentManager.logStep("TutorialsNINJA - Check That User is in Register Page");
        Assert.assertEquals(ActualURL, "https://tutorialsninja.com/demo/index.php?route=account/register", "User is not in Register page !");
        // Verification TC_RF_007 Terminated
        ExtentManager.logStep("TutorialsNINJA - Verification TC_RF_007 Terminated");
    }
}
