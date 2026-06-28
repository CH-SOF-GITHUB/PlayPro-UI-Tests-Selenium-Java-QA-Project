package com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.Reservations;

import io.qameta.allure.*;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.tests.PlayPro.DEV.LoggedBaseTest;

public class TC001 extends LoggedBaseTest {

    @Test
    @Epic("Web Interface")
    @Feature("Reservation features")
    @Story("Reservations Page Navigation")
    @Description("This test attempts to access to reservation page. if fail an error happens.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void ClickToNavigateToReservationsPage1() throws InterruptedException {
        // Test implementation goes here
        Allure.suite("Suite 002");
        step1();
        step2();
    }

    @Step("Step 1")
    public void step1() throws InterruptedException {
        webReservationPage.clickRéservationsLinkEnNavBar();
    }

    @Step("Step 2: assertion for successful navigation torReservation page")
    public void step2() throws InterruptedException {
        Thread.sleep(7000);
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://demotenant.playpro.fr/discover/reservation", "Client is not navigated to Reservations page !");
    }

    @Step("Step 3")
    public void step3() throws InterruptedException {
        webReservationPage.clickRéserverLink();
    }


    @Test
    @Epic("Web Interface")
    @Feature("Reservation features")
    @Story("Reservations Page Navigation")
    @Description("This test attempts to access to reservation page. if fail an error happens.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void ClickToNavigateToReservationsPage2() throws InterruptedException {
        // Test implementation goes here
        Allure.suite("Suite 002");
        step3();
        step2();
    }
}
