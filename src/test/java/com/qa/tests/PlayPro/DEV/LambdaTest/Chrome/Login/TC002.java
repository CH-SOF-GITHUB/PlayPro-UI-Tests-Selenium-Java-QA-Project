package com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.Login;

import com.qa.pages.WebLoginPage;
import com.qa.tests.PlayPro.DEV.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC002 extends BaseTest {
    // define Login Object Page
    WebLoginPage webLoginPage;

    @Test
    @Parameters({"Email", "Password"})
    @Epic("Web Interface")
    @Feature("Login features")
    @Story("Authentication Parameterized Tests")
    @Description("This test attempts to login into the website using differents login and password.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void testAuthentification(String email, String password) throws InterruptedException {
        Allure.suite("Suite 001");
        // initialize Login Page Object
        webLoginPage = new WebLoginPage(driver);
        // enter email
        Allure.step("Step 001: Enter Email: " + email);
        webLoginPage.EnterEmail(email);
        // enter password
        Allure.step("Step 002: Enter Password: " + email);
        webLoginPage.EnterPassword(password);
        // click on login button
        Allure.step("Step 003: Click on Login Button");
        webLoginPage.ClickLoginButton();
        // wait for 7 s to allow the page to load
        Allure.step("Step 004: Verify the login was successful");
        Thread.sleep(7000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://chakerqa.playpro.fr/");
        Assert.assertEquals(driver.getTitle(), "playprosite");
    }

}
