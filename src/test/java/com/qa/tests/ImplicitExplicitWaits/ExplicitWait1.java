package com.qa.tests.ImplicitExplicitWaits;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExplicitWait1 {
    WebDriver driver;
    // define explicit wait web driver
    WebDriverWait wait;

    // @BeforeSuite: The annotation method will be run before all tests in this suite have run.
    @BeforeSuite
    public void configureLogs() {
        // AJOUT : masquer les logs Selenium INFO/WARNING
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket").setLevel(Level.SEVERE);
    }

    // @BeforeMethod: The annotation method will be run before each test method.
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // @AfterMethod: The annotation method will be run after each test method.
    @AfterMethod
    public void end() {
        driver.close();
    }

    public void signIn(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(email);
        Reporter.log("Email entered: " + email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
        Reporter.log("Password entered: " + password);
        WebElement SignInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Sign In'])[1]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SignInButton);
        Reporter.log("Clicked on 'Sign In' button");
    }

    public void clickOnMyBookings() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='My Bookings']"))).click();
        Reporter.log("Clicked on 'My Bookings' button");
    }

    public void clickOnBrowseEvents() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//span[normalize-space()='Browse Events →']"))).click();
        Reporter.log("Clicked on 'Browse Events' button");
    }

    public void clickOnBookNowById(int id) {
        WebElement BookNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body[1]/main[1]/div[1]/div[3]/article[1]/div[2]/div[2]/a[" + id + "]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", BookNow);
        Reporter.log("Clicked on 'Book Now' button of Article: " + id);
    }

    public void FillOutCardBookingDetails(String fullName, String email, String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customerName"))).sendKeys(fullName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer-email"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys(phone);
        WebElement BookConfirmNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='confirm-booking']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", BookConfirmNow);
        Reporter.log("Clicked on 'Confirm Booking' button of Article with Details: " + fullName + " - " + email + " - " + phone);
    }

    public void verifyBookingConfirmed() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Booking Confirmed! \uD83C\uDF89')]"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-gray-500 text-sm mb-5']"))).isDisplayed());
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='bg-indigo-50 border border-indigo-100 rounded-xl p-4 mb-5 text-left space-y-2'] div:nth-child(2) span:nth-child(2)"))).getText(), "Chaker Ben Said", "Custom name does not match the expected value");
    }

    @Test
    public void test1() {
        driver.get("https://eventhub.rahulshettyacademy.com/");
        signIn("bchaker28@yahoo.com", "Q5n@j!i!QnZQmYm");
        clickOnBrowseEvents();
        clickOnBookNowById(1);
        FillOutCardBookingDetails("Chaker Ben Said", "bchaker28@yahoo.com", "21623400811");
        verifyBookingConfirmed();
    }
}
