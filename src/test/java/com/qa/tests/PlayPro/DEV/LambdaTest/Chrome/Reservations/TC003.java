package com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.Reservations;

import com.qa.pages.Activites.WebEXP1Page;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import com.qa.tests.PlayPro.DEV.LoggedBaseTest;

public class TC003 extends LoggedBaseTest {

    @Step("Step 1")
    public void step1() throws InterruptedException {
        webReservationPage.clickRéservationsLinkEnNavBar();
    }

    @Step("Step 2")
    public void step2() throws InterruptedException {
        webReservationPage.clickOnCategoryVR();
    }

    @Step("Step 3")
    public void step3() throws InterruptedException {
        webEXP1Page.clickVrPartyTestCard();
    }

    @Step("Step 4")
    public void step4() throws InterruptedException {
        webEXP1Page.clickButtonPlus();
        webEXP1Page.clickButtonMinus();
    }

    @Step("Step 5")
    public void step5() throws InterruptedException {
        webEXP1Page.clickSlot_45min();
    }

    @Step("Step 6")
    public void step6() throws InterruptedException {
        webEXP1Page.clickContinueButton();
    }

    @Step("Step 7")
    public void step7() throws InterruptedException {
        Thread.sleep(5000);
        webEXP1Page.clickDay_30_In_Calendar();
        webEXP1Page.clickSlot_20_00();
    }

    @Step("Step 8")
    public void step8() throws InterruptedException {
        webEXP1Page.clickConfirmMyReservationButton();
    }

    @Step("Step 9")
    public void step9() throws InterruptedException {
        webEXP1Page.clickWithoutOptionButton();
    }

    @Step("Step 10")
    public void step10() throws InterruptedException {
        webEXP1Page.clickConfirmMyReservationButton();
    }

    @Step("Step 11: assertion for successful navigation to panier page")
    public void step11() throws InterruptedException {
        Thread.sleep(7000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demotenant.playpro.fr/Panier");
    }

    @Step("Step 12")
    public void step12() throws InterruptedException {
        webCartPage.clickVisaCard4242Btn();
    }

    @Step("Step 13")
    public void step13() throws InterruptedException {
        webCartPage.clickPayNowBtn();
    }

    @Step("Step 14")
    public void step14() throws InterruptedException {
        String ExpectedMessage = "Merci pour votre réservation ! \uD83C\uDF89";
        String ActualMessage = webReservationPage.getOrderConfirmationMessage();
        Assert.assertEquals(ActualMessage, ExpectedMessage);
    }


    @Test
    @Epic("Web Interface")
    @Feature("Reservation features")
    @Story("Reservation activities")
    @Description("This test attempts to reserve an activity and pass by all steps. if fail an error happens.")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Play Pro V3 Site Web", url = "https://demotenant.playpro.fr/discover/reservation/vr-party-test")
    @Owner("Chaker Ben Said")
    public void ReservationOfActivityExpertVr() throws InterruptedException {
        // Test implementation goes here
        Allure.suite("Suite 004");
        step1();
        step2();
        step3();
        step4();
        step5();
        step6();
        step7();
        step8();
        step9();
        step10();
        step11();
        step12();
        step13();
        step14();
    }
}
