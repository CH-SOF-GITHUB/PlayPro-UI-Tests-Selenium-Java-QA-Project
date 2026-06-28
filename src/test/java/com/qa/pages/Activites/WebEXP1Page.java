package com.qa.pages.Activites;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = about:blank
public class WebEXP1Page {
    // define static web driver
    protected WebDriver driver;
    // define Explicit time out for web elements
    protected WebDriverWait wait;

    public WebEXP1Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    /**
     *  Locator Experience Card : Vr Party Test
     */
    @FindBy(xpath = "//a[@href='/discover/reservation/vr-party-test']//div//div//div//div//div[contains(text(),'Réserver')]")
    private WebElement Vr_Party_Test;

    /**
     *  STEP 1: Locators Of Number of participants and Choices of Duration by price
     */
    @FindBy(xpath = "(//div[contains(@role,'button')])[7]")
    private WebElement ButtonPlus;

    @FindBy(xpath = "(//div[contains(@role,'button')])[6]")
    private WebElement ButtonMinus;

    @FindBy(xpath = "(//p[normalize-space()='45 min'])[1]")
    private WebElement Slot_45min;

    @FindBy(xpath = "(//p[normalize-space()='90 min'])[1]")
    private WebElement Slot_90min;

    /**
     * STEP 1: Locator of Continue Button
     */
    @FindBy(xpath = "/html/body/div[1]/div/div/div/div[2]/div[1]/div[5]/div/button")
    private WebElement ContinueButton;

    /**
     *  STEP 2: Locator of Reservation Date and Choice of Slots
     */
    @FindBy(xpath = "(//button[@id='date-btn-2026-06-30'])[1]")
    private WebElement Day_30_In_Calendar;

    @FindBy(xpath = "(//button[contains(@type,'button')])[2]")
    private WebElement Slot_20_00;

    @FindBy(xpath = "(//button[contains(@type,'button')])[3]")
    private WebElement Slot_20_45;

    @FindBy(xpath = "(//button[contains(@class,'opacity-100 relative w-full h-[50px] rounded-md max-md:px-[15px] lg:text-base gap-[10px] opacity-100 font-poppins font-medium text-[15px] leading-[21px] tracking-[0px] text-center border bg-primary')])[1]")
    private WebElement btn_confirm_my_reservation;

    /**
     * STEP 3: Locator of supplements Options
     */
    @FindBy(xpath = "(//button[contains(@class,'opacity-100 relative w-full h-[50px] rounded-md max-md:px-[15px] lg:text-base gap-[10px] opacity-100 font-poppins font-medium text-[15px] leading-[21px] tracking-[0px] text-center border bg-primary')])[1]")
    private WebElement btn_without_option;

    /**
     * STEP 4: Locator of Finish reservation
     */
    @FindBy(xpath = "(//button[contains(@class,'opacity-100 relative w-full h-[50px] rounded-md max-md:px-[15px] lg:text-base gap-[10px] opacity-100 font-poppins font-medium text-[15px] leading-[21px] tracking-[0px] text-center border bg-primary')])[1]")
    private WebElement btn_finish_reservation;

    /**
     *  Method of POM class: click on Vr Party Test card
     */

    public void clickVrPartyTestCard() throws InterruptedException {
        Thread.sleep(1000);
        WebElement card = wait.until(ExpectedConditions.visibilityOf(Vr_Party_Test));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", card);
        System.out.println("Vr Party Test card clicked");
    }

    /**
     *  Methods of POM class: STEP 1
     */
    public void clickButtonPlus() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(ButtonPlus));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Button Plus clicked");
    }

    public void clickButtonMinus() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(ButtonMinus));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Button Minus clicked");
    }

    public void clickSlot_45min() throws InterruptedException {
        Thread.sleep(2000);
        WebElement slot = wait.until(ExpectedConditions.visibilityOf(Slot_45min));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", slot);
        System.out.println("Slot 45 min clicked");
    }

    public void clickSlot_90min() throws InterruptedException {
        Thread.sleep(2000);
        WebElement slot = wait.until(ExpectedConditions.visibilityOf(Slot_90min));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", slot);
        System.out.println("Slot 90 min clicked");
    }

    public void clickContinueButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(ContinueButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Continue Button clicked");
    }

    /**
     *  Methods of POM class: STEP 2
     */
    public void clickDay_30_In_Calendar() throws InterruptedException {
        Thread.sleep(2000);
        WebElement day = wait.until(ExpectedConditions.visibilityOf(Day_30_In_Calendar));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", day);
        System.out.println("Day 30 in Calendar clicked");
    }

    public void clickSlot_20_00() throws InterruptedException {
        Thread.sleep(2000);
        WebElement slot = wait.until(ExpectedConditions.visibilityOf(Slot_20_00));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", slot);
        System.out.println("Slot 20:00 clicked");
    }

    public void clickSlot_20_45() throws InterruptedException {
        Thread.sleep(2000);
        WebElement slot = wait.until(ExpectedConditions.visibilityOf(Slot_20_45));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", slot);
        System.out.println("Slot 20:45 clicked");
    }

    public void clickConfirmMyReservationButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(btn_confirm_my_reservation));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Confirm My Reservation Button clicked");
    }

    /**
     * Methods of POM class: STEP 3
     */
    public void clickWithoutOptionButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(btn_without_option));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Without Option Button clicked");
    }

    /**
     * Methods of POM class: STEP 4
     */
    public void clickFinishReservationButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(btn_finish_reservation));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Finish Reservation Button clicked");
    }
}
