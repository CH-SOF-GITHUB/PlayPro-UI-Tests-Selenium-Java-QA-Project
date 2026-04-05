package BDD.DEV.Reservations.GiftVouchers;

import PageObject.WebCookiesPage;
import PageObject.WebGiftVoucherPage;
import PageObject.WebLoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;

import static BDD.Hooks.driver;

public class VoucherTC02 {
    private static final Log log = LogFactory.getLog(VoucherTC02.class);
    // define the objects pages
    WebLoginPage webLoginPage = new WebLoginPage(driver);
    WebCookiesPage webCookiesPage = new WebCookiesPage(driver);
    WebGiftVoucherPage webGiftVoucherPage = new WebGiftVoucherPage(driver);

    // define the steps of the scenario
    @Given("I open login page")
    public void i_open_login_page() {
        webCookiesPage.clickAcceptCookiesButton();
    }

    @Given("I type email {string}")
    public void i_type_email(String Email) {
        webLoginPage.EnterEmail(Email);
    }

    @Given("I type pwd {string}")
    public void i_type_pwd(String pwd) {
        webLoginPage.EnterPassword(pwd);
    }

    @When("I click on Login Btn")
    public void i_click_on_login_btn() {
        webLoginPage.ClickLoginButton();
    }

    @Given("Click on Btn Offer a gift voucher")
    public void click_on_btn_offer_a_gift_voucher() {
        webGiftVoucherPage.clickAddGiftVoucherBtn();
    }

    @When("Choose your gift card {int} CHF")
    public void choose_your_gift_card_chf(int amount) {
        if (amount == 250) {
            webGiftVoucherPage.clickGift250Btn();
            log.info("Gift card of " + amount + " CHF is selected");
        } else if (amount == 200) {
            webGiftVoucherPage.clickGift200Btn();
            log.info("Gift card of " + amount + " CHF is selected");
        } else if (amount == 100) {
            webGiftVoucherPage.clickGift100Btn();
            log.info("Gift card of " + amount + " CHF is selected");
        } else if (amount == 50) {
            webGiftVoucherPage.clickGift50Btn();
            log.info("Gift card of " + amount + " CHF is selected");
        } else if (amount == 25) {
            webGiftVoucherPage.clickGift25Btn();
            log.info("Gift card of " + amount + " CHF is selected");
        } else {
            log.info("Gift card of " + amount + " CHF is not available");
        }
    }

    @When("I click on Next Btn")
    public void i_click_on_next_btn() {
        webGiftVoucherPage.clickNextBtn();
    }

    @When("I click on bank card stripe in cart")
    public void i_click_on_bank_card_stripe_in_cart() {
        webGiftVoucherPage.clickBankCardBtn();
        webGiftVoucherPage.clickVisaCard11_26Btn();
    }

    @When("I click on payment btn")
    public void i_click_on_payment_btn() {
        webGiftVoucherPage.clickPayNowBtn();
    }

    @Then("Check that Voucher was buying With {string}")
    public void checkThatVoucherWasBuyingWith(String status) throws InterruptedException {
        // wait for 7s to load page
        Thread.sleep(7000);
        String SuccessOrderMsg = webGiftVoucherPage.GetOrderConfirmationMessage();
        Assert.assertEquals(SuccessOrderMsg, "Merci pour votre commande ! \uD83C\uDF89", "Gift Voucher buying Failed! and Msg is not correct");
        log.info("Gift Voucher buying is " + status);
    }
}
