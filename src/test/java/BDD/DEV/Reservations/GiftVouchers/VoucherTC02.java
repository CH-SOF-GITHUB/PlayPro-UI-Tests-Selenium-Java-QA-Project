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

public class VoucherTC02 {
    private static final Log log = LogFactory.getLog(VoucherTC02.class);
    // define the objects pages
    WebGiftVoucherPage webGiftVoucherPage = new WebGiftVoucherPage(driver);
    WebCartPage webCartPage = new WebCartPage(driver);

    // define the steps of the scenario
    @Given("Click on Button Offer a gift voucher")
    public void click_on_button_offer_a_gift_voucher() {
        // Write code here that turns the phrase above into concrete actions
        webGiftVoucherPage.clickAddGiftVoucherBtn();
    }

    @When("Choose my gift card {int} CHF")
    public void choose_my_gift_card_chf(int amount) {
        // Write code here that turns the phrase above into concrete actions
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

    @When("I click on Next Button")
    public void i_click_on_next_button() {
        // Write code here that turns the phrase above into concrete actions
        webGiftVoucherPage.clickNextBtn();
    }

    @When("I click on bank card stripe")
    public void i_click_on_bank_card_stripe() {
        // Write code here that turns the phrase above into concrete actions
        webCartPage.clickVisaCard11_26Btn();
    }

    @When("I click on payment button")
    public void i_click_on_payment_button() {
        // Write code here that turns the phrase above into concrete actions
        webCartPage.clickPayNowBtn();
    }

    @Then("Check that Voucher was buying With {string}")
    public void checkThatVoucherWasBuyingWith(String status) throws InterruptedException {
        // wait for 7s to load page
        Thread.sleep(7000);
        String SuccessOrderMsg = webCartPage.GetOrderConfirmationMessage();
        Assert.assertEquals(SuccessOrderMsg, "Merci pour votre commande ! \uD83C\uDF89", "Gift Voucher buying Failed! and Msg is not correct");
        log.info("Gift Voucher buying is " + status);
    }

}
