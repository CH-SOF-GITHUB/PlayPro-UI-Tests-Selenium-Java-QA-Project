package com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.Reservations;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.tests.PlayPro.DEV.LoggedBaseTest;

public class TC002 extends LoggedBaseTest {

    /**/
    @Test
    @Epic("Web Interface")
    @Feature("Reservation features")
    @Story("Activities by categories")
    @Description("This test attempts to access to reservation page of activities by category. if fail an error happens.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void NavigateToReservationsPageByCategory() throws InterruptedException {
        // Test implementation goes here
        Allure.suite("Suite 003");
        step1();
        step2();
        step3();
    }

    @Step("Step 1")
    public void step1() throws InterruptedException {
        webReservationPage.clickRéservationsLinkEnNavBar();
    }

    @Step("Step 2")
    public void step2() throws InterruptedException {
        webReservationPage.clickOnCategoryVR();
    }

    @Step("Step 3: assertion for successful navigation to reservation page by category")
    public void step3() throws InterruptedException {
        Thread.sleep(7000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demotenant.playpro.fr/discover/tag/vr-categorie-test-renomme?id=1");
    }

    @Step("Step 4")
    public void step4() throws InterruptedException {
        subStep1();
        subStep2();
    }

    @Step("Sub-Step 1")
    public void subStep1() throws InterruptedException {
        webEXP1Page.clickVrPartyTestCard();
    }

    @Step("Sub-Step 2: assertion for successful navigation to activity: Vr Party Test")
    public void subStep2() throws InterruptedException {
        Thread.sleep(7000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demotenant.playpro.fr/discover/reservation/vr-party-test");
    }

    @Test
    @Epic("Web Interface")
    @Feature("Reservation features")
    @Story("Activities by categories")
    @Description("This test attempts to access to reservation page of activity by category. if fail an error happens.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Chaker Ben Said")
    public void NavigateToActivityVrPartyTest() throws InterruptedException {
        // Test implementation goes here
        Allure.suite("Suite 003");
        step1();
        step2();
        step4();
    }
}
