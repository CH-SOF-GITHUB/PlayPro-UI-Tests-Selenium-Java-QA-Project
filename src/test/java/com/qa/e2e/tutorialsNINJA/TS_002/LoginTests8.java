package com.qa.e2e.tutorialsNINJA.TS_002;

import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.FooterPage;
import org.qa.pages.TutorialsNINJA.HeaderPage;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.qa.utilities.LTStatus.addLambdaStepContext;

public class LoginTests8 extends BaseClass {

    private LoginPage loginPage;
    private HeaderPage headerPage;
    public boolean isEqualOK;
    private FooterPage footerPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        headerPage = new HeaderPage();
        footerPage = new FooterPage();
    }

    @Test(priority = 1, description = "TC_LF_021", suiteName = "(TS_002) - Login Functionality")
    public void verifyEnableNavigateToPagesFromLogin() throws InterruptedException {
        // STEP 1: Click on 'My Account' Dropdown
        ExtentManager.logStep("TutorialsNINJA - Click on 'My Account' Dropmenu");
        addLambdaStepContext(getDriver(), "LT - Click on 'My Account' Dropmenu");
        loginPage.clickOnMyAccountDropmenu();
        // STEP 2: Click on 'Login' option
        ExtentManager.logStep("TutorialsNINJA - Click on 'Login' option");
        addLambdaStepContext(getDriver(), "LT - Click on 'Login' option");
        loginPage.clickOnLoginOption();
        // STEP 3: Click on 'Continue' button under 'New Customer' section
        ExtentManager.logStep("TutorialsNINJA - Click on 'Continue' button under 'New Customer' section");
        addLambdaStepContext(getDriver(), "LT - Click on 'Continue' button under 'New Customer' section");
        loginPage.clickOnContinue();
        // STEP 4: Navigate back to Login page
        ExtentManager.logStep("TutorialsNINJA - Navigate back to Login page");
        addLambdaStepContext(getDriver(), "LT - Navigate back to Login page");
        loginPage.backBrowser();
        // STEP 5: Click on different options to navigate to different pages
        // check the navigation
        ExtentManager.logStep("TutorialsNINJA - Check on click & navigate to differents pages");
        addLambdaStepContext(getDriver(), "LT - Check on click & navigate to differents pages");
        loginPage.openForgetPwdPage();
        isEqualOK = loginPage.returnCurrentURL().equals("https://tutorialsninja.com/demo/index.php?route=account/forgotten");
        Assert.assertTrue(isEqualOK, "Error the current url page does not match the expected url");
        // return back to Login page
        loginPage.backBrowser();
        headerPage.clickOnDesktopsOption();
        isEqualOK = loginPage.returnCurrentURL().equals("https://tutorialsninja.com/demo/index.php?route=product/category&path=20");
        Assert.assertTrue(isEqualOK, "Error the current url page does not match the expected url");
        // return back to Login page
        loginPage.backBrowser();
        headerPage.clickOnLaptopAndNoteBooksOption();
        isEqualOK = loginPage.returnCurrentURL().equals("https://tutorialsninja.com/demo/index.php?route=product/category&path=18_45");
        Assert.assertTrue(isEqualOK, "Error the current url page does not match the expected url");
        // return back to Login page
        loginPage.backBrowser();
        headerPage.clickOnComponentsOption();
        isEqualOK = loginPage.returnCurrentURL().equals("https://tutorialsninja.com/demo/index.php?route=product/category&path=25_29");
        Assert.assertTrue(isEqualOK, "Error the current url page does not match the expected url");
        // return back to Login page
        loginPage.backBrowser();
        headerPage.clickOnTabletsOption();
        isEqualOK = loginPage.returnCurrentURL().equals("https://tutorialsninja.com/demo/index.php?route=product/category&path=57");
        Assert.assertTrue(isEqualOK, "Error the current url page does not match the expected url");
        // return back to Login page
        loginPage.backBrowser();
        headerPage.clickOnSoftwareOption();
        isEqualOK = loginPage.returnCurrentURL().equals("https://tutorialsninja.com/demo/index.php?route=product/category&path=17");
        Assert.assertTrue(isEqualOK, "Error the current url page does not match the expected url");
        // return back to Login page
        loginPage.backBrowser();
        headerPage.clickOnPhonesAndPDAsOption();
        isEqualOK = loginPage.returnCurrentURL().equals("https://tutorialsninja.com/demo/index.php?route=product/category&path=24");
        Assert.assertTrue(isEqualOK, "Error the current url page does not match the expected url");
        // return back to Login page
        loginPage.backBrowser();
        headerPage.clickOnCamerasOption();
        isEqualOK = loginPage.returnCurrentURL().equals("https://tutorialsninja.com/demo/index.php?route=product/category&path=33");
        Assert.assertTrue(isEqualOK, "Error the current url page does not match the expected url");
        // return back to Login page
        loginPage.backBrowser();
        headerPage.clickOnMp3PlayersOption();
        isEqualOK = loginPage.returnCurrentURL().equals("https://tutorialsninja.com/demo/index.php?route=product/category&path=34_43");
        Assert.assertTrue(isEqualOK, "Error the current url page does not match the expected url");
        // return back to Login page & re-click on 'My Account' Dropdown
        loginPage.backBrowser();
        loginPage.clickOnMyAccountDropmenu();
        // Click on 'Register' option
        loginPage.clickOnRegisterOption();
        isEqualOK = loginPage.returnCurrentURL().equals("https://tutorialsninja.com/demo/index.php?route=account/register");
        Assert.assertTrue(isEqualOK, "Error the current url page does not match the expected url");
        // return back to Login page
        loginPage.backBrowser();
        // Click on 'About US' in footer
        footerPage.clickOnAboutUS();
        isEqualOK = loginPage.returnCurrentURL().equals("https://tutorialsninja.com/demo/index.php?route=information/information&information_id=4");
        Assert.assertTrue(isEqualOK, "Error the current url page does not match the expected url");
        ExtentManager.logStep("TutorialsNINJA - Verification Of navigate to the Appropriate pages Terminated");
        addLambdaStepContext(getDriver(), "LT - Verification Of navigate to the Appropriate pages Terminated");
    }
}
