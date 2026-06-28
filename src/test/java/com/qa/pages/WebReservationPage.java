package com.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


// page_url = https://chakertestqa.playpro.fr/connexion
public class WebReservationPage {
    /* web driver of POM class */
    private final WebDriver driver;

    private final WebDriverWait wait;

    public WebReservationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    /* web elements of POM class */
    @FindBy(xpath = "//a[contains(@class, 'h-8')]")
    private WebElement RéservationsLink;

    @FindBy(xpath = "//a[contains(@class, 'w-[7.938rem]')]")
    private WebElement RéserverLink;

    @FindBy(xpath = "//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' rounded-md ') and contains(concat(' ', normalize-space(@class), ' '), ' font-poppins ')]")
    private WebElement Offre_Link;
    /*
       Find web elements for Experiences, Activities, Events, and Categories (VR, Escape Game, etc.) if needed for future test cases.
     */
    @FindBy(xpath = "/html/body/div[1]/div/section[1]/section[1]/div[2]/a[1]/div/div[2]")
    private WebElement Category_VR_Link;

    /*
      Locate Success Message of Reservation
     */
    @FindBy(xpath = "//p[@class='font-poppins font-semibold text-black md:text-xl text-sm py-6 text-center leading-4']")
    private WebElement OrderConfirmationMessage;


    /* methods of POM class */
    public void ClickOffreMenuNavbar() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(Offre_Link));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Offre link clicked in navbar");
    }

    public void clickRéservationsLinkEnNavBar() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(RéservationsLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Réservations link clicked in navbar");
    }

    public void clickRéserverLink() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(RéserverLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Réserver link clicked in navbar");
    }

    public void clickOnCategoryVR() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(Category_VR_Link));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Category VR clicked in reservation page");
    }

    public String getOrderConfirmationMessage() throws InterruptedException {
        Thread.sleep(7000);
        WebElement message = wait.until(ExpectedConditions.visibilityOf(OrderConfirmationMessage));
        return message.getText();
    }
}
