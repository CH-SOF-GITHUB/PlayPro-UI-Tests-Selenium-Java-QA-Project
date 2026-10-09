package com.qa.cucumber.bdd.EVENTHUB;

import com.qa.cucumber.bdd.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;

public class BookNewEventPage {

    private ActionDriver actionDriver;

    public BookNewEventPage(WebDriver driver) {
        this.actionDriver = Hooks.getActionDriver();
    }

    // Locate the elements for booking a new event
    // For example:
    private static final By FullNameField = By.id("customerName");
    private static final By EmailField = By.id("customer-email");
    private static final By PhoneField = By.id("phone");
    // (+) and (-) tickets buttons
    private static final By MinusBtn = By.xpath("//button[normalize-space()='-']");
    private static final By PlusBtn = By.xpath("//button[normalize-space()='+']");
    // Confirm a Booking Button
    private static final By ConfirmBookingButton = By.xpath("//button[@id='confirm-booking']");

    // Warning messages:
    private static final By FullNameWarning = By.xpath("//p[normalize-space()='Name must be at least 2 chars']");
    private static final By EmailWarning = By.xpath("//p[normalize-space()='Enter a valid email']");
    private static final By PhoneWarning = By.xpath("//p[normalize-space()='Enter a valid 10-digit phone']");

    // Details Confirm Bookings Element Card

    private static final By BookingConfirmMsg = By.xpath("//h3[contains(text(),'Booking Confirmed! \uD83C\uDF89')]");
    private static final By TicketsReservedMg = By.xpath("//p[@class='text-gray-500 text-sm mb-5']");

    private static final By BookingRef = By.xpath("//span[@class='booking-ref font-mono font-bold text-indigo-600']");
    private static final By CustomerField = By.cssSelector("div[class='bg-indigo-50 border border-indigo-100 rounded-xl p-4 mb-5 text-left space-y-2'] div:nth-child(2) span:nth-child(2)");
    private static final By NumberOfTicketsField = By.cssSelector("body > main:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(4) > div:nth-child(3) > span:nth-child(2)");

    // Total Price confirmation:
    private static final By TotalPriceConfirmation = By.cssSelector("body > main:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(4) > div:nth-child(4) > span:nth-child(2)");

    // View My Bookings Button
    private static final By ViewMyBookingsBtn = By.xpath("//button[normalize-space()='View My Bookings']");

    /**** Add methods for booking a new event ****/
    // Click on Minus Button
    public void clickMinusButton() {
        actionDriver.clickUsingJS(MinusBtn);
    }

    // Click on Plus Button
    public void clickPlusButton() {
        actionDriver.clickUsingJS(PlusBtn);
    }

    public void fillOutBookingDetails(String fullName, String email, String phone) {
        actionDriver.enter(FullNameField, fullName);
        actionDriver.enter(EmailField, email);
        actionDriver.enter(PhoneField, phone);
    }

    // Click on Confirm Booking Button
    public void clickConfirmBooking() {
        actionDriver.clickUsingJS(ConfirmBookingButton);
    }

    /**** Error Warnings messages ****/
    public boolean checkFullNameWarning(String expectedWarning) {
        return actionDriver.getText(FullNameWarning).equals(expectedWarning);
    }

    public boolean checkEmailWarning(String expectedWarning) {
        return actionDriver.getText(EmailWarning).equals(expectedWarning);
    }

    public boolean checkPhoneWarning(String expectedWarning) {
        return actionDriver.getText(PhoneWarning).equals(expectedWarning);
    }

    /**** Check information details about Booking Event ****/
    public boolean checkBookingConfirmMsg(String expectedMsg) {
        return actionDriver.getText(BookingConfirmMsg).equals(expectedMsg);
    }

    public String getBookingConfirmMsg() {
        return actionDriver.getText(BookingConfirmMsg);
    }

    public boolean checkTicketsReservedMsg(String expectedMsg) {
        return actionDriver.getText(TicketsReservedMg).equals(expectedMsg);
    }

    public boolean checkBookingRef(String expectedValue) {
        return actionDriver.getText(BookingRef).equals(expectedValue);
    }

    public String getBookingRef() {
        return actionDriver.getText(BookingRef);
    }

    public boolean checkCustomerNameField(String expectedValue) {
        return actionDriver.getText(CustomerField).equals(expectedValue);
    }

    public String getCustomerName() {
        return actionDriver.getText(CustomerField);
    }

    public boolean checkNumberOfTicketsField(String expectedValue) {
        return actionDriver.getText(NumberOfTicketsField).equals(expectedValue);
    }

    public String getNumberOfTickets() {
        return actionDriver.getText(NumberOfTicketsField);
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

    /**** click on View My Bookings button ****/
    public void clickOnViewMyBookingsBtn() {
        actionDriver.click(ViewMyBookingsBtn);
    }
}
