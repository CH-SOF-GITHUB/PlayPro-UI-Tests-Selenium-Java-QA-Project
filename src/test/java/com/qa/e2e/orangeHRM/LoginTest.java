package com.qa.e2e.orangeHRM;

import org.qa.base.BaseClass;
import org.qa.pages.orangeHRM.HomePage;
import org.qa.pages.orangeHRM.LoginPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;


    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
    }

    @Test
    public void validLoginTest() {
        // Lunch the test with Extent Report
        ExtentManager.startTest("Valid Login Test");
        ExtentManager.logStep("Navigating to login page and entering username & password");
        loginPage.login("Admin", "admin123");
        ExtentManager.logStep("Verifying Admin Tab is visible or not");
        Assert.assertTrue(homePage.isAdminTabVisible(), "Error: Admin tab is not visible after login");
        ExtentManager.logStep("Validation successful");
        homePage.logout();
        ExtentManager.logStep("Logout successfully!");
        staticWait(2);
    }

    @Test
    public void invalidLoginTest() {
        // Lunch the test with Extent Report
        ExtentManager.startTest("In-valid Login Test");
        ExtentManager.logStep("Navigating to login page and entering username & password");
        loginPage.login("Admin", "admin1234");
        String ExpectedErrorMsg = "Invalid credentials";
        // Assert.assertTrue(loginPage.checkErrorMsgDisplayed(), "Error: Error message is not displayed");
        Assert.assertTrue(loginPage.verifyErrorMessage(ExpectedErrorMsg), "Error: Error message is not correct");
        ExtentManager.logStep("Validation Of Error Message successful");
        ExtentManager.logStep("Logout successfully!");
    }

}
