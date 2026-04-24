package BDD;

import PageObject.WebCookiesPage;
import PageObject.WebLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import static BDD.Hooks.driver;

public class CommonSteps {
    // define the log
    private static final Log log = LogFactory.getLog(CommonSteps.class);

    // define instances of POM pages classes
    WebLoginPage webLoginPage = new WebLoginPage(driver);
    WebCookiesPage webCookiesPage = new WebCookiesPage(driver);

    // define the common steps before each scenario
    @Given("I navigate to login page")
    public void i_navigate_to_login_page() {
        // Write code here that turns the phrase above into concrete actions
        webCookiesPage.clickAcceptCookiesButton();
        log.info("COMMON-STEP: Cookie page accepted");
    }

    @Given("I enter email {string}")
    public void i_enter_email(String Email) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        webLoginPage.EnterEmail(Email);
        log.info("COMMON-STEP: Enter email " + Email);
    }

    @Given("I enter pwd {string}")
    public void i_enter_pwd(String Pwd) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        webLoginPage.EnterPassword(Pwd);
        log.info("COMMON-STEP: Enter password " + Pwd);
    }

    @When("I click Login Button")
    public void i_click_login_button() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        webLoginPage.ClickLoginButton();
        log.info("COMMON-STEP: Click login button");
    }
}
