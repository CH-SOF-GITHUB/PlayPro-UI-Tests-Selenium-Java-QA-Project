package com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.Login;

import com.qa.pages.WebCookiesPage;
import com.qa.pages.WebLoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.tests.PlayPro.DEV.BaseTest;

public class TC001 extends BaseTest {
    // define Login object page
    WebLoginPage webLoginPage;
    WebCookiesPage webCookiesPage;


    @Test
    @Epic("Web Interface")
    @Feature("Login features")
    @Story("Authentication")
    @Description("This test attempts to login into the website using a valid login and password. if fail an error happens.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void SampleLogin() throws InterruptedException {
        Allure.suite("Suite 001");
        // define objects pages
        webLoginPage = new WebLoginPage(driver);
        webCookiesPage = new WebCookiesPage(driver);
        step1();
        step2("demotenant1@yopmail.com");
        step3("Admin1234!");
        step4();
        step5();
    }

    @Step("Step 1")
    public void step1() {
        webCookiesPage.clickAcceptCookiesButton();
    }

    @Step("Step 2")
    public void step2(String email) throws InterruptedException {
        webLoginPage.EnterEmail(email);
    }

    @Step("Step 3")
    public void step3(String pwd) throws InterruptedException {
        webLoginPage.EnterPassword(pwd);
    }

    @Step("Step 4")
    public void step4() throws InterruptedException {
        webLoginPage.ClickLoginButton();
    }

    @Step("Step 5: assertion for successful login")
    public void step5() throws InterruptedException {
        // wait for some time to let the page load
        Thread.sleep(7000);
        // verify the login conditions
        Assert.assertEquals(driver.getCurrentUrl(), "https://demotenant.playpro.fr/");
        Assert.assertEquals(driver.getTitle(), "DEMO TENANT");
    }

    @Step("Step 6: assertion for error login")
    public void step6() throws InterruptedException {
        String ErrorMsg = webLoginPage.GetLoginErrorMessage();
        Assert.assertEquals(ErrorMsg, "Veuillez vérifier votre email/ mot de passe", "Error Login Failed!");
    }

    @Test
    @Epic("Web Interface")
    @Feature("Login features")
    @Story("Authentication")
    @Description("This test attempts to login into the website using a wrong login or password and a error message should display. If fails an error happens.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void SampleErrorLogin() throws InterruptedException {
        Allure.suite("Suite 001");
        // define objects pages
        webLoginPage = new WebLoginPage(driver);
        webCookiesPage = new WebCookiesPage(driver);
        step1();
        step2("demo@gmail.com");
        step3("Admin1234!");
        step4();
        step6();
    }
}
