package com.qa.cucumber.bdd.EVENTHUB;

import com.qa.cucumber.bdd.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class ViewEmptyBookingsSteps {
    // Define the WebDriver instance and the page objects
    WebDriver driver = Hooks.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);
    BookingsPage bookingsPage = new BookingsPage(driver);

    @Given("The user is logged into the EventHUB Application")
    public void the_user_is_logged_into_the_event_hub_application() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.openLoginPage();
        loginPage.enterCredentials("bchaker28@yahoo.com", "Q5n@j!i!QnZQmYm");
        loginPage.clickSignIn();
    }

    @When("User navigates to the My Bookings section")
    public void user_navigates_to_the_my_bookings_section() {
        // Write code here that turns the phrase above into concrete actions
        homePage.clickOnMyBookingsButton();
        bookingsPage.checkUserInBookingsPage();
    }

    @Then("User should see a message indicating no bookings are available")
    public void user_should_see_a_message_indicating_no_bookings_are_available() {
        // Write code here that turns the phrase above into concrete actions
        bookingsPage.CheckNoBookingsMessage("No bookings yet");
    }

    @Then("User should see the subDescription Text below the message")
    public void user_should_see_the_sub_description_text_below_the_message() {
        // Write code here that turns the phrase above into concrete actions
        bookingsPage.CheckNoBookingsSubText("You haven't booked any events yet. Browse upcoming events and grab your tickets!");
    }
}
