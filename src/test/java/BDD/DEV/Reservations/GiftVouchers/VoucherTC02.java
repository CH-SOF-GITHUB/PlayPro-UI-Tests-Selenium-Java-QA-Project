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
    public void click_on_button_offer_a_gift_voucher() throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            Thread.sleep(1000);
            webGiftVoucherPage.clickAddGiftVoucherBtn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @When("Choose my gift card {int} CHF")
    public void choose_my_gift_card_chf(int amount) throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            if (amount == 250) {
                Thread.sleep(1000);
                webGiftVoucherPage.clickGift250Btn();
                log.info("Gift card of " + amount + " CHF is selected");
            } else if (amount == 200) {
                Thread.sleep(1000);
                webGiftVoucherPage.clickGift200Btn();
                log.info("Gift card of " + amount + " CHF is selected");
            } else if (amount == 100) {
                Thread.sleep(1000);
                webGiftVoucherPage.clickGift100Btn();
                log.info("Gift card of " + amount + " CHF is selected");
            } else if (amount == 50) {
                Thread.sleep(1000);
                webGiftVoucherPage.clickGift50Btn();
                log.info("Gift card of " + amount + " CHF is selected");
            } else if (amount == 25) {
                Thread.sleep(1000);
                webGiftVoucherPage.clickGift25Btn();
                log.info("Gift card of " + amount + " CHF is selected");
            } else {
                log.info("Gift card of " + amount + " CHF is not available");
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @When("I click on Next Button")
    public void i_click_on_next_button() throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            Thread.sleep(1000);
            webGiftVoucherPage.clickNextBtn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @When("I click on bank card stripe")
    public void i_click_on_bank_card_stripe() throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            Thread.sleep(1000);
            webCartPage.clickVisaCard11_26Btn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @When("I click on payment button")
    public void i_click_on_payment_button() throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            Thread.sleep(1000);
            webCartPage.clickPayNowBtn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @Then("Check that Voucher was buying With {string}")
    public void checkThatVoucherWasBuyingWith(String status) throws InterruptedException {
        try {
            // wait for 8s to load page
            Thread.sleep(8000);
            String SuccessOrderMsg = webCartPage.GetOrderConfirmationMessage();
            Assert.assertEquals(SuccessOrderMsg, "Merci pour votre commande ! \uD83C\uDF89", "Gift Voucher buying Failed! and Msg is not correct");
            log.info("Gift Voucher buying is " + status);
        } catch (Exception e) {
            e.fillInStackTrace();
            log.info("Gift Voucher buying is " + status);
            throw e;
        }
    }

}
