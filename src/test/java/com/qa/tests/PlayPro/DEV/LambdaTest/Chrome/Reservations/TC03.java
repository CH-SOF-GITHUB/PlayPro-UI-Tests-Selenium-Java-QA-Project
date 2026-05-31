package com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.Reservations;

import com.qa.constants.Priority;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.tests.PlayPro.DEV.LoggedBaseTest;

import java.util.Objects;

public class TC03 extends LoggedBaseTest {
        /*
        Test Case 3: Verify that the user can navigate to one of Expériences grouped by Gategory VR
                    by clicking on button of the Experience.
                    Expérience:  Expert VR
                           URL:  https://demotenant.playpro.fr/discover/reservation/expert-vr
                            ID:  288

        Expected Result:
        The user should be successfully navigated to the Expérience VR.
        */

    @BeforeMethod(dependsOnMethods = {"loginBeforeEachTest"})
    public void AccessToCategoryVr() throws InterruptedException {
        // Test implementation goes here
        // click on Réservations link in navbar
        webReservationPage.clickRéserverLink();
        // scroll to category VR and click it
        webReservationPage.clickOnCategoryVR();
    }

    @Test(priority = Priority.P1)
    public void testNavigateToExpertVr() throws InterruptedException {
        // Test implementation goes here
        webEXP2Page.clickExpertVRCard();
        // checking load page and verify the results
        Wait.until(d -> Objects.equals(d.getCurrentUrl(), "https://demotenant.playpro.fr/discover/reservation/expert-vr"));
    }
}
