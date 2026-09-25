package com.qa.e2e.tutorialsNINJA.TS_001;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.RegisterPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTests9 extends BaseClass {

    private RegisterPage registerPage;


    @BeforeMethod
    public void setupPages() {
        registerPage = new RegisterPage(getDriver());
    }


    @Test(description = "TC_RF_012", suiteName = "(TS_001) - Register Functionality")
    public void verifyRegistrationUsingKeyTabs() {

    }
}
