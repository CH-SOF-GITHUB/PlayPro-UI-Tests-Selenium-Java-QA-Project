package BDD.DEV.Reservations.Activities;


import PageObject.Activites.WebEXP1Page;
import PageObject.WebCartPage;
import PageObject.WebReservationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;

import static BDD.Hooks.driver;

public class ExperienceTC01 {
    private static final Log log = LogFactory.getLog(ExperienceTC01.class);
    // define the objects pages
    WebEXP1Page webEXP1Page = new WebEXP1Page(driver);
    WebReservationPage webReservationPage = new WebReservationPage(driver);
    WebCartPage webCartPage = new WebCartPage(driver);

    // define the steps of the scenario
    @Given("I click on Experience Card")
    public void i_click_on_experience_card() throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            webReservationPage.clickRéserverLink();
            webEXP1Page.clickVrPartyTestCard();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @Given("I select Nbre of participants {int}")
    public void i_select_nbre_of_participants(Integer number) throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            webEXP1Page.clickSelectParticipantsBtn();
            switch (number) {
                case 4:
                    webEXP1Page.clickOption4ParticipantsBtn();
                    break;
                case 5:
                    webEXP1Page.clickOption5ParticipantsBtn();
                    break;
                case 6:
                    webEXP1Page.clickOption6ParticipantsBtn();
                    break;
                case 7:
                    webEXP1Page.clickOption7ParticipantsBtn();
                    break;
                case 8:
                    webEXP1Page.clickOption8ParticipantsBtn();
                    break;
                case 9:
                    webEXP1Page.clickOption9ParticipantsBtn();
                    break;
                case 10:
                    webEXP1Page.clickOption10ParticipantsBtn();
                    break;
                default:
                    log.info("Invalid number of participants: " + number);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @And("I select duration price by min {int}")
    public void iSelectDurationPriceByMin(int min) throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            webEXP1Page.clickSelectDurationPriceBtn();
            switch (min) {
                case 45:
                    webEXP1Page.clickOption45MinBtn();
                    break;
                case 90:
                    webEXP1Page.clickOption90MinBtn();
                    break;
                default:
                    log.info("Invalid duration price: " + min);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @Given("I click on Continue Btn")
    public void i_click_on_continue_btn() throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            webEXP1Page.clickContinueBtn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @Given("I select time slot {string}")
    public void i_select_time_slot(String time) throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            String DayOfReservation = webEXP1Page.checkDayOfReservation();
            log.info("The day of reservation is " + DayOfReservation);
            if (DayOfReservation.equals("vendredi 24 avril")) {
                if (time.equals("20:45")) {
                    webEXP1Page.clickTimeSlot20_45_5_Btn();
                } else if (time.equals("20:00")) {
                    webEXP1Page.clickTimeSlot20_00_4_Btn();
                } else {
                    log.info("Invalid time slot: " + time);
                }
            } else {
                log.info("The day of reservation is not correct!");
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @Given("I click on Confirm Btn")
    public void i_click_on_confirm_btn() throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            webEXP1Page.clickConfirmBtn();
            webEXP1Page.clickContinueBtn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @Given("I click on Continue without option Btn")
    public void i_click_on_continue_without_option_btn() throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            Thread.sleep(3000);
            webEXP1Page.clickContinueWithoutOptionBtn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @Given("I click on bank card by stripe")
    public void i_click_on_bank_card_by_stripe() throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            webCartPage.clickVisaCard11_26Btn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @When("I click on payment now button")
    public void i_click_on_payment_now_button() throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            webCartPage.clickPayNowBtn();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    @Then("Check that reservation was made with {string}")
    public void check_that_reservation_was_made_with(String string) throws InterruptedException {
        try {
            // Write code here that turns the phrase above into concrete actions
            // wait for 7s to load page
            Thread.sleep(7000);
            String SuccessOrderMsg = webCartPage.GetOrderConfirmationMessage();
            Assert.assertEquals(SuccessOrderMsg, "Merci pour votre réservation ! \uD83C\uDF89", "Gift Voucher buying Failed! and Msg is not correct");
            log.info("Experience reservation is " + string);
        } catch (Exception e) {
            e.fillInStackTrace();
            log.info("Experience reservation is " + string);
            throw e;
        }
    }
}
