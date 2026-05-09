package tests.PlayPro.DEV.LambdaTest.Chrome.Reservations;

import Levels.Priority;
import PageObject.WebReservationPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.PlayPro.DEV.LoggedBaseTest;

public class TC01 extends LoggedBaseTest {

    // declare instance of the pages objects
    WebReservationPage webReservationPage = null;
    /**/

    @Test(priority = Priority.P1)
    public void ClickToNavigateToReservationsPage1() throws InterruptedException {
        // open the home page of Demo V3 PlayPro site
        webReservationPage = new WebReservationPage(driver);
        // click on Réservations link in navbar
        webReservationPage.clickRéservationsLinkEnNavBar();
        Thread.sleep(7000);
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://demotenant.playpro.fr/discover/reservation", "Client is not navigated to Reservations page !");
    }

    @Test(priority = Priority.P2)
    public void ClickToNavigateToReservationsPage2() throws InterruptedException {
        // open the home page of Demo V3 PlayPro site
        webReservationPage = new WebReservationPage(driver);
        // click on Réservations link in navbar
        webReservationPage.clickRéserverLink();
        Thread.sleep(7000);
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://demotenant.playpro.fr/discover/reservation", "Client is not navigated to Reservations page !");
    }
}
