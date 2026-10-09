package com.qa.cucumber.bdd.EVENTHUB;

import com.qa.cucumber.bdd.Hooks;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static org.qa.base.BaseClass.getSoftAsserts;

public class BookNewEventSteps {
    // AJOUT : initialiser les objets après le démarrage du navigateur
    private final WebDriver driver = Hooks.getDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final HomePage homePage = new HomePage(driver);
    private final BookingsPage bookingsPage = new BookingsPage(driver);
    private final BookNewEventPage bookNewEventPage = new BookNewEventPage(driver);
    // AJOUT : conserver une instance de SoftAssert pour le scénario
    private final SoftAssert softAssert = getSoftAsserts();

    @Given("The User is logged into the EventHUB Application")
    public void theUserIsLoggedIntoTheEventHUBApplication() {
        loginPage.openLoginPage();
        loginPage.enterCredentials("bchaker28@yahoo.com", "Q5n@j!i!QnZQmYm");
        loginPage.clickSignIn();
    }

    @When("User navigates to the Events section")
    public void user_navigates_to_the_events_section() {
        // Write code here that turns the phrase above into concrete actions
        homePage.clickOnBrowseEvents();
    }

    @And("User selects an available event {string}")
    public void userSelectsAnAvailableEvent(String NbrEvent) {
        bookingsPage.clickOnBookNowById(Integer.parseInt(NbrEvent));
    }

    @And("User selects the number of tickets to book")
    public void user_selects_the_number_of_tickets_to_book() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        for (int i = 0; i < 5; i++) {
            bookNewEventPage.clickPlusButton();
            Thread.sleep(1000);
        }
    }

    @And("User enters the booking details")
    public void user_enters_the_booking_details() {
        // Write code here that turns the phrase above into concrete actions
        bookNewEventPage.fillOutBookingDetails("Chaker Ben Said", "bchaker28@yahoo.com", "+9758484812");
    }

    @And("User clicks the Confirm Booking button")
    public void user_clicks_the_confirm_booking_button() {
        // Write code here that turns the phrase above into concrete actions
        bookNewEventPage.clickConfirmBooking();
    }

    @Then("User should see a booking confirmation message")
    public void user_should_see_a_booking_confirmation_message() {
        // Write code here that turns the phrase above into concrete actions
        String Text = bookNewEventPage.getBookingConfirmMsg();
        System.out.println("Text 1 : " + Text);
        softAssert.assertTrue(bookNewEventPage.checkBookingConfirmMsg("Booking Confirmed! 🎉"), "The booking confirmation message is not displayed.");
        softAssert.assertTrue(bookNewEventPage.checkTicketsReservedMsg("Your tickets are reserved."));
    }

    @And("User should see the booking reference")
    public void user_should_see_the_booking_reference() {
        // Write code here that turns the phrase above into concrete actions
        String Text2 = bookNewEventPage.getBookingRef();
        System.out.println("Text 2 : " + Text2);
        softAssert.assertTrue(bookNewEventPage.checkBookingRef("D-ZEPPUW"), "Error-The booking reference fails and not correct");
    }

    @And("User should see the customer name and number of tickets")
    public void user_should_see_the_customer_name_and_number_of_tickets() {
        // Write code here that turns the phrase above into concrete actions
        String Text3 = bookNewEventPage.getCustomerName();
        System.out.println("Text 3 : " + Text3);
        softAssert.assertTrue(bookNewEventPage.checkCustomerNameField("Chaker Ben Said"), "Error-The booking Customer Name fails and not correct");
        String Text4 = bookNewEventPage.getNumberOfTickets();
        System.out.println("Text 4 : " + Text4);
        softAssert.assertTrue(bookNewEventPage.checkNumberOfTicketsField("6"), "Error-The booking Tickets Number fails and not correct");
    }

    @And("User should see the correct total booking amount")
    public void user_should_see_the_correct_total_booking_amount() {
        // Write code here that turns the phrase above into concrete actions
        softAssert.assertTrue(bookNewEventPage.checkTotalPriceConfirmation(1800));
        // Collecter toutes les assertions échouées
        softAssert.assertAll();
    }
}
