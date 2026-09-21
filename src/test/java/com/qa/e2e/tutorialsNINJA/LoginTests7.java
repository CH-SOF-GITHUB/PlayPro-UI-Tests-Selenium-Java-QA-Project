package com.qa.e2e.tutorialsNINJA;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qa.base.BaseClass;
import org.qa.pages.TutorialsNINJA.LoginPage;
import org.qa.pages.TutorialsNINJA.MyAccountPage;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.qa.utilities.LTStatus.addLambdaStepContext;

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

        // STEP 1: Login With Valid credentials
        ExtentManager.logStep("TutorialsNINJA - Login with valid credentials");
        addLambdaStepContext(getDriver(), "LT - Login with valid credentials");
        loginPage.login("chakerbensaid1@yopmail.com", "Admin1234!");
        staticWait(2);

        // STEP 2: open a new window and switches to new window
        ExtentManager.logStep("TutorialsNINJA - Open and switch to a new Window");
        addLambdaStepContext(getDriver(), "LT - Open and switch to a new Window");
        getDriver().switchTo().newWindow(WindowType.WINDOW);
        //loggr.info("Open a new window and switch to new window");
        staticWait(2);

        // STEP 3: fetch handles of all windows, there will be two, [0]- default, [1] - new window
        ExtentManager.logStep("TutorialsNINJA - Fetch handles of all windows");
        addLambdaStepContext(getDriver(), "LT - Fetch handles of all windows");
        Object[] windowHandles = getDriver().getWindowHandles().toArray();
        System.out.println("windowHandles Object[] : " + Arrays.toString(windowHandles));

        // STEP 4: switch to old: window [0] and close the browser
        ExtentManager.logStep("TutorialsNINJA - Close the old browser");
        addLambdaStepContext(getDriver(), "LT - Close the old browser");
        getDriver().switchTo().window((String) windowHandles[0]);
        staticWait(2);
        getDriver().close();
        //loggr.info("The browser is closed");

        // STEP 5: switch to window [1] and re-open the application
        ExtentManager.logStep("TutorialsNINJA - Switch to new browser and open the application");
        addLambdaStepContext(getDriver(), "LT - Switch to new browser and open the application");
        getDriver().switchTo().window((String) windowHandles[1]);
        staticWait(2);
        getDriver().get("https://tutorialsninja.com/demo/index.php?route=account/account");
        //loggr.info("In new browser, application are reopened successfully !");

        // STEP 6: Verify user is still logged in
        ExtentManager.logStep("TutorialsNINJA - Check user still not logging out");
        addLambdaStepContext(getDriver(), "LT - Check user still not logging out");
        Assert.assertTrue(myAccountPage.compareMyAccountTitle("My Account"), "ERROR: User is not automatically logged in after browser reopening");
        //loggr.info("Verification Session still opened Terminated");

        ExtentManager.logStep("TutorialsNINJA - Verification Session still opened Terminated");
        addLambdaStepContext(getDriver(), "LT - Verification Session still opened Terminated");
    }

}
