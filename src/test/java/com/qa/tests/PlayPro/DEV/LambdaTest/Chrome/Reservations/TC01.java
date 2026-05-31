package com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.Reservations;

import com.qa.constants.Priority;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.tests.PlayPro.DEV.LoggedBaseTest;

public class TC01 extends LoggedBaseTest {
    /*
    Test Case 1: Verify navigation to the Reservations page via navbar links.

    Description:
    This test ensures an authenticated user (login performed automatically by LoggedBaseTest)
    can reach the Reservations page from the navbar using two entries: "Réservations" and "Réserver".
    It validates the final URL to confirm correct redirection.
    */
    /**/

    @Test(priority = Priority.P1)
    public void ClickToNavigateToReservationsPage1() throws InterruptedException {
        // Test implementation goes here
        // click on Réservations link in navbar
        webReservationPage.clickRéservationsLinkEnNavBar();
        Thread.sleep(7000);
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://demotenant.playpro.fr/discover/reservation", "Client is not navigated to Reservations page !");
    }

    @Test(priority = Priority.P2)
    public void ClickToNavigateToReservationsPage2() throws InterruptedException {
        // Test implementation goes here
        // click on Réservations link in navbar
        webReservationPage.clickRéserverLink();
        Thread.sleep(7000);
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://demotenant.playpro.fr/discover/reservation", "Client is not navigated to Reservations page !");
    }
}
