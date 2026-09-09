package com.qa.e2e.orangeHRM;

import org.qa.base.BaseClass;
import org.qa.pages.orangeHRM.HomePage;
import org.qa.pages.orangeHRM.LoginPage;
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
        loginPage.login("Admin", "admin123");
        Assert.assertTrue(homePage.verifyOrangeHRMLogo(), "Error: OrangeHRM logo is not visible on the home page");
    }
}
