package BDD.DEV.Reservations.GiftVouchers;

import PageObject.WebCartPage;
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

public class VoucherTC01 {
    private static final Log log = LogFactory.getLog(VoucherTC01.class);
    // define the objects pages
    WebCartPage webCartPage = new WebCartPage(driver);
    WebGiftVoucherPage webGiftVoucherPage = new WebGiftVoucherPage(driver);

    // define the steps of the scenario
    @Given("Click on Btn Offer a gift voucher")
    public void click_on_btn_offer_a_gift_voucher() {
        try {
            webGiftVoucherPage.clickAddGiftVoucherBtn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @When("Choose your gift card {int} CHF")
    public void choose_your_gift_card_chf(int amount) {
        try {
            webGiftVoucherPage.clickGift250Btn();
            log.info("Gift card of " + amount + " CHF is selected");
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @When("I click on Next Btn")
    public void i_click_on_next_btn() {
        try {
            webGiftVoucherPage.clickNextBtn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @When("I click on bank card stripe in cart")
    public void i_click_on_bank_card_stripe_in_cart() {
        try {
            webCartPage.clickVisaCard11_26Btn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @When("I click on payment btn")
    public void i_click_on_payment_btn() {
        try {
            webCartPage.clickPayNowBtn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @Then("Check that Voucher was buying successfully")
    public void check_that_voucher_was_buying_successfully() throws InterruptedException {
        try {
            // wait for 7s to load page
            Thread.sleep(7000);
            String SuccessOrderMsg = webCartPage.GetOrderConfirmationMessage();
            Assert.assertEquals(SuccessOrderMsg, "Merci pour votre commande ! \uD83C\uDF89", "Gift Voucher buying Failed! and Msg is not correct");
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }
}

