package com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.Devis;

import com.qa.tests.PlayPro.DEV.LoggedBaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001 extends LoggedBaseTest {


    @Test
    @Epic("Devis")
    @Feature("Navigation Devis Entreprise")
    @Story("Devis Entreprise")
    @Description("This Test attempts to access to devis entreprise page and check if the page is loaded successfully.")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Devis Entreprise", url = "https://chakerqa.playpro.fr/")
    @Owner("Chaker Ben Said")
    public void OpenDevisEntreprisePage() throws InterruptedException {
        Allure.suite("Suite 005");
        step1();
        step2();
    }


    @Step("Step 1")
    public void step1() {
        webDevisEntreprise.clickOnOrderDevisEntrepriseButton();
    }

    @Step("Step 2: assertion for successful page load")
    public void step2() throws InterruptedException {
        Thread.sleep(7000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://chakerqa.playpro.fr/contact-us/company");
    }

    @Step("Step 3")
    public void step3() throws InterruptedException {
        webDevisEntreprise.FillLastName("Ben Said");
    }

    @Step("Step 4")
    public void step4() throws InterruptedException {
        webDevisEntreprise.FillFirstName("Chaker");
    }

    @Step("Step 5")
    public void step5() throws InterruptedException {
        webDevisEntreprise.FillTelephone("0679844353");
    }

    @Step("Step 6")
    public void step6() throws InterruptedException {
        webDevisEntreprise.FillEmail("chaker@gmail.com");
        webDevisEntreprise.SelectNbrParticipants();
    }

    @Step("Step 7")
    public void step7() throws InterruptedException {
        webDevisEntreprise.SelectNbrParticipants();
    }

    @Step("Step 8")
    public void step8() throws InterruptedException {
        webDevisEntreprise.FillStartDate();     // open First picker
    }

    @Step("Step 9")
    public void step9() throws InterruptedException {
        webDevisEntreprise.FillFinishDate();    // open Second picker
    }

    @Step("Step 10")
    public void step10() throws InterruptedException {
        webDevisEntreprise.FillDateEvent();
        subStep3();
        subStep4("12");
    }

    @Step("Sub-Step 2")
    public void subStep3() throws InterruptedException {
        webDevisEntreprise.ClickNextMonth();
    }

    @Step("Sub-Step 3")
    public void subStep4(String date) throws InterruptedException {
        webDevisEntreprise.SelectDateOption(date);
    }

    @Test
    @Epic("Devis")
    @Feature("Demande Devis Entreprise")
    @Story("Devis Entreprise")
    @Description("This Test attempts to fill devis entreprise form and send it successfully.")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Devis Entreprise", url = "https://chakerqa.playpro.fr/")
    @Owner("Chaker Ben Said")
    public void FillDeviseEntrepriseForm() throws InterruptedException {
        Allure.suite("Suite 005");
        step1();
        step3();
        step4();
        step5();
        step6();
        step7();
        step8();
        Thread.sleep(3000);
        step9();
        step10();
        Thread.sleep(5000);
    }
}
