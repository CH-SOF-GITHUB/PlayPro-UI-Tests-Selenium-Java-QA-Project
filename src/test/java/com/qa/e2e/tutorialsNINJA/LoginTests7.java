package com.qa.e2e.tutorialsNINJA;

import lombok.extern.slf4j.Slf4j;
import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.pages.TutorialsNINJA.MyAccountPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Slf4j
public class LoginTests7 extends BaseClass {

    private LoginPage loginPage;
    private MyAccountPage myAccountPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        myAccountPage = new MyAccountPage(getDriver());
    }

    @Test(priority = 1, description = "TC_LF_019", suiteName = "(TS_002) - Login Functionality")
    public void verifyAutoLoginAfterCloseAndReopenBrowser() {

    }

}
