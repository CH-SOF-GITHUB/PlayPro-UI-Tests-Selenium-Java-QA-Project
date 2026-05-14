package tests.PlayPro.DEV.LambdaTest.Chrome.Reservations;

import Levels.Priority;
import org.testng.annotations.Test;
import tests.PlayPro.DEV.LoggedBaseTest;

import java.util.Objects;

public class TC02 extends LoggedBaseTest {
    /*
    Test Case 2: Verify that the user can navigate to the Reservations page of Activities grouped by Gategory
                 by clicking on the category name itself.

    Steps:
    1. Open the home page of Demo V3 PlayPro site.
    2. Click on the "Réserver" link in the navbar.
    3. Click on gategory.
    4. Verify that the user is navigated to the Reservations page of Activities grouped by Gategory.

    Expected Result:
    The user should be successfully navigated to the Reservations page of Activities grouped by Gategory Type VR.
    */

    /**/
    @Test(priority = Priority.P1)
    public void testNavigateToReservationsPageByCategory() throws InterruptedException {
        // Test implementation goes here
        // click on Réservations link in navbar
        webReservationPage.clickRéservationsLinkEnNavBar();
        // checking load page before operation
        // scroll to category VR and click it
        webReservationPage.clickOnCategoryVR();
        // verify the results
        Wait.until(d -> Objects.equals(d.getCurrentUrl(), "https://demotenant.playpro.fr/discover/tag/vr-categorie?id=1"));
    }

    @Test(priority = Priority.P2)
    public void testNavigateToActivityVrPartyTest() throws InterruptedException {
        // Test implementation goes here
        // click on Réservations link in navbar
        webReservationPage.clickRéservationsLinkEnNavBar();
        // checking load page before operation
        // scroll to category VR and click it
        webReservationPage.clickOnCategoryVR();
        // click on activity Vr Party Test
        webEXP1Page.clickVrPartyTestCard();
        // checking load page and verify the results
        Wait.until(d -> Objects.equals(d.getCurrentUrl(), "https://demotenant.playpro.fr/discover/reservation/vr-party-test"));
    }
}
