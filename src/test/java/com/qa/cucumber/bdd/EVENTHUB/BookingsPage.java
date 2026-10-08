package com.qa.cucumber.bdd.EVENTHUB;

import com.qa.cucumber.bdd.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;
import org.testng.Reporter;

public class BookingsPage {

    private ActionDriver actionDriver;
    private static final String PAGE_URL = "https://eventhub.rahulshettyacademy.com/bookings";

    /**
     * PROBLÈME INITIAL (NPE / Échec d'instanciation Cucumber) :
     * Cucumber instancie les classes de Step Definitions (et donc leurs objets membres)
     * AVANT d'exécuter la méthode @Before des Hooks.
     *
     * Si l'on fait 'this.actionDriver = BaseClass.getActionDriver()' dans un constructeur
     * sans argument, le ThreadLocal 'actionDriver' est encore NULL au moment où Java
     * charge cette classe, ce qui fait planter l'exécution BDD.
     *
     * SOLUTION :
     * On passe 'WebDriver driver' en paramètre au constructeur (ou on instancie la page
     * directement à l'intérieur des méthodes de Steps) pour différer la récupération
     * de l'ActionDriver APRÈS l'exécution du @Before des Hooks.
     */
    public BookingsPage(WebDriver driver) {
        this.actionDriver = Hooks.getActionDriver();
    }

    // Locate web elements of bookings page
    private static By MyBookingsH1 = By.xpath("//h1[normalize-space()='My Bookings']");
    private static By MyBookingsSubText = By.xpath("//p[@class='text-gray-500 mt-1']");

    private static By CleanAllBookingsButton = By.xpath("//button[normalize-space()='Clear all bookings']");

    private static By NoBookingsMessage = By.xpath("//h3[normalize-space()='No bookings yet']");
    private static By NoBookingsSubText = By.xpath("//p[@class='text-sm text-gray-500 max-w-sm mb-6 leading-relaxed']");

    private static By BrowserEventsButton = By.xpath("//button[normalize-space()='Browse Events']");

    // Define methods to interact with the bookings page elements
    public boolean CheckMyBookingsH1Text(String expectedText) {
        return actionDriver.getText(MyBookingsH1).equals(expectedText);
    }

    public boolean CheckSubBookingsText(String expectedText) {
        return actionDriver.getText(MyBookingsSubText).equals(expectedText);
    }

    public void ClickCleanAllBookingsButton() {
        actionDriver.click(CleanAllBookingsButton);
    }

    public boolean CheckNoBookingsMessage(String expectedText) {
        return actionDriver.getText(NoBookingsMessage).equals(expectedText);
    }

    public boolean CheckNoBookingsSubText(String expectedText) {
        return actionDriver.getText(NoBookingsSubText).equals(expectedText);
    }

    public void clickBrowserEventsButton() {
        actionDriver.click(BrowserEventsButton);
    }

    public boolean checkUserInBookingsPage() {
        return actionDriver.getCurrentURL().equals(PAGE_URL);
    }

    public void clickOnBookNowById(int id) {
        WebElement BookNow = actionDriver.waitForElementToBeClickable(By.xpath("//body[1]/main[1]/div[1]/div[3]/article[1]/div[2]/div[2]/a[" + id + "]"));
        actionDriver.clickUsingJS((By) BookNow);
    }
}
