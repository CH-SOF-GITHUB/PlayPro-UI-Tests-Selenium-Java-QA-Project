package com.qa.e2e.orangeHRM;

import org.qa.base.BaseClass;
import org.qa.pages.orangeHRM.HomePage;
import org.qa.pages.orangeHRM.LoginPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeTest extends BaseClass {
    private LoginPage loginPage;
    private HomePage homePage;


    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
    }

    @Test
    public void verifyOrangeHRMLogo() {
        // Lunch the test with Extent Report
        // ExtentManager.startTest("Home Page Logo Test");   // This has been implemented in TestListener
        ExtentManager.logStep("Navigating to login page and entering username & password");
        loginPage.login("Admin", "admin123");
        ExtentManager.logStep("Verifying Logo is visible or not");
        Assert.assertTrue(homePage.verifyOrangeHRMLogo(), "Error: OrangeHRM logo is not visible on the home page");
        ExtentManager.logStep("Validation Logo successful");
        ExtentManager.logStep("Logout successfully!");
    }
}
