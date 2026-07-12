package com.qa.cucumber.bdd.DEV.Login;

import com.qa.cucumber.bdd.Hooks;
import com.qa.pages.WebCookiesPage;
import com.qa.pages.WebLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class TC001 {
    // Define the web driver
    WebDriver driver = Hooks.driver;
    // define instances of page object
    WebCookiesPage webCookiesPage = new WebCookiesPage(driver);
    WebLoginPage webLoginPage = new WebLoginPage(driver);

    @Given("I am on the authentification page")
    public void i_am_on_the_authentification_page() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("I am on the authentification page");
        webCookiesPage.clickAcceptCookiesButton();
    }

    @When("I enter valid email")
    public void i_enter_valid_email() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        webLoginPage.EnterEmail("chakerqa-client@yopmail.com");
    }

    @When("I enter valid password")
    public void i_enter_valid_password() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        webLoginPage.EnterPassword("Admin1234!");
    }

    @When("I click on login button")
    public void i_click_on_login_button() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        webLoginPage.ClickLoginButton();
    }

    @Then("I should see the welcome message")
    public void i_should_see_the_welcome_message() throws InterruptedException, IOException {
        // Write code here that turns the phrase above into concrete actions
        // wait for some time to let the page load
        Thread.sleep(7000);
        // verify the login conditions
        String welcomeMsg = webLoginPage.GetWelcomeMessage();
        Assert.assertEquals(welcomeMsg, "Heureux de vous revoir \uD83D\uDC4B");
        File srcFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile1, new File("src/test/java/com/qa/cucumber/bdd/Screenshots/success/scenario_login_ok.png"));
        Thread.sleep(3000);
        // console log if test ok
        System.out.println("Test OK");
    }
}
