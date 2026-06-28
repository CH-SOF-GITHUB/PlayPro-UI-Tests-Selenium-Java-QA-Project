package com.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = https://chakertestqa.playpro.fr/connexion
public class WebCartPage {
    // define static web driver
    protected WebDriver driver;
    // define Explicit time out for web elements
    protected WebDriverWait wait;

    public WebCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    /**
     *  Card & Payment Buttons
     */
    @FindBy(xpath = "(//span[@class='md:w-5 w-4 md:h-5 h-4 border-2 border-black rounded-full'])[2]")
    private WebElement VisaCard4242Btn;

    @FindBy(xpath = "(//button[normalize-space()='Payer maintenant'])[1]")
    private WebElement PayNowBtn;

    /** Methods of POM class: Card & Payment Buttons
     */
    public void clickVisaCard4242Btn() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(VisaCard4242Btn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Visa Card 4242 clicked");
    }

    public void clickPayNowBtn() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(PayNowBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Pay Now button clicked");
    }

}
