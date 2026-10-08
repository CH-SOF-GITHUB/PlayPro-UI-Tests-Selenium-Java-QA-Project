package com.qa.cucumber.bdd.EVENTHUB;

import org.openqa.selenium.By;
import org.qa.actionDriver.ActionDriver;

public class BookNewEventSteps {

    private ActionDriver actionDriver;

    public BookNewEventSteps(ActionDriver actionDriver) {
        this.actionDriver = actionDriver;
    }

    // Locate the elements for booking a new event
    // For example:
    private static final By FullNameField = By.id("customerName");
    private static final By EmailField = By.id("customer-email");
    private static final By PhoneField = By.id("phone");

    private static final By MinusBtn = By.xpath("//button[normalize-space()='-']");
    private static final By PlusBtn = By.xpath("//button[normalize-space()='+']");

    private static final By ConfirmBookingButton = By.xpath("//button[@id='confirm-booking']");

    // Warning messages:
    private static final By FullNameWarning = By.xpath("//p[normalize-space()='Name must be at least 2 chars']");
    private static final By EmailWarning = By.xpath("//p[normalize-space()='Enter a valid email']");
    private static final By PhoneWarning = By.xpath("//p[normalize-space()='Enter a valid 10-digit phone']");

    // Total Price confirmation:
    private static final By TotalPriceConfirmation = By.cssSelector("body > main:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(4) > div:nth-child(4) > span:nth-child(2)");

    // Add methods for booking a new event
    public void fillOutBookingDetails(String fullName, String email, String phone) {
        actionDriver.enter(FullNameField, fullName);
        actionDriver.enter(EmailField, email);
        actionDriver.enter(PhoneField, phone);
    }

    public void clickConfirmBooking() {
        actionDriver.click(ConfirmBookingButton);
    }

    public void clickMinusButton() {
        actionDriver.click(MinusBtn);
    }

    public void clickPlusButton() {
        actionDriver.click(PlusBtn);
    }

    public boolean checkFullNameWarning(String expectedWarning) {
        return actionDriver.getText(FullNameWarning).equals(expectedWarning);
    }

    public boolean checkEmailWarning(String expectedWarning) {
        return actionDriver.getText(EmailWarning).equals(expectedWarning);
    }

    public boolean checkPhoneWarning(String expectedWarning) {
        return actionDriver.getText(PhoneWarning).equals(expectedWarning);
    }

    public boolean checkTotalPriceConfirmation(int expectedTotal) {
        // 1. Récupérer le texte brut de Selenium (ex: "$1,800")
        String rawText = actionDriver.getText(TotalPriceConfirmation);
        // 2. Nettoyer la chaîne en supprimant le "$" et la virgule ","
        // Le regex "[^0-9]" supprime TOUT ce qui n'est pas un chiffre
        String cleanedText = rawText.replaceAll("[^0-9]", "");
        // 3. Convertir la chaîne nettoyée ("1800") en entier (int)
        int actualTotal = Integer.parseInt(cleanedText);
        // 4. Comparer les deux nombres
        return actualTotal == expectedTotal;
    }
}
