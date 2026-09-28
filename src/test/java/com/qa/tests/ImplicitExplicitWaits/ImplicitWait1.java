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

public class ImplicitWait1 {
    WebDriver driver;

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
        // define implicit wait for the synchronization of web elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    // @AfterMethod: The annotation method will be run after each test method.
    @AfterMethod
    public void end() {
        driver.close();
    }

    public void signIn(String email, String password) {
        driver.findElement(By.id("email")).sendKeys(email);
        Reporter.log("Email entered: " + email);
        driver.findElement(By.id("password")).sendKeys(password);
        Reporter.log("Password entered: " + password);
        WebElement SignInButton = driver.findElement(By.xpath("(//button[normalize-space()='Sign In'])[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SignInButton);
        Reporter.log("Clicked on 'Sign In' button");
    }

    public void clickOnMyBookings() {
        driver.findElement(By.xpath("//button[normalize-space()='My Bookings']")).click();
        Reporter.log("Clicked on 'My Bookings' button");
    }

    public void clickOnBrowseEvents() {
        driver.findElement(By.xpath("//a//span[normalize-space()='Browse Events →']")).click();
        Reporter.log("Clicked on 'Browse Events' button");
    }

    public void clickOnBookNowById(int id) {
        WebElement BookNow = driver.findElement(By.xpath("//body[1]/main[1]/div[1]/div[3]/article[1]/div[2]/div[2]/a[" + id + "]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", BookNow);
        Reporter.log("Clicked on 'Book Now' button of Article: " + id);
    }

    public void FillOutCardBookingDetails(String fullName, String email, String phone) {
        driver.findElement(By.id("customerName")).sendKeys(fullName);
        driver.findElement(By.id("customer-email")).sendKeys(email);
        driver.findElement(By.id("phone")).sendKeys(phone);
        WebElement BookConfirmNow = driver.findElement(By.xpath("//button[@id='confirm-booking']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", BookConfirmNow);
        Reporter.log("Clicked on 'Confirm Booking' button of Article with Details: " + fullName + " - " + email + " - " + phone);
    }

    public void verifyBookingConfirmed() {
        Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'Booking Confirmed! \uD83C\uDF89')]")).isDisplayed());
        Reporter.log("Booking Confirmed! \uD83C\uDF89");
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='text-gray-500 text-sm mb-5']")).isDisplayed());
        Reporter.log("Your tickets are reserved.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div[class='bg-indigo-50 border border-indigo-100 rounded-xl p-4 mb-5 text-left space-y-2'] div:nth-child(2) span:nth-child(2)")).getText(), "Chaker Ben Said", "Custom name does not match the expected value");
        Reporter.log("Customer : " + driver.findElement(By.cssSelector("div[class='bg-indigo-50 border border-indigo-100 rounded-xl p-4 mb-5 text-left space-y-2'] div:nth-child(2) span:nth-child(2)")).getText());
    }

    @Test(invocationCount = 5)
    public void test1() {
        driver.get("https://eventhub.rahulshettyacademy.com/");
        signIn("bchaker28@yahoo.com", "Q5n@j!i!QnZQmYm");
        clickOnBrowseEvents();
        clickOnBookNowById(1);
        FillOutCardBookingDetails("Chaker Ben Said", "bchaker28@yahoo.com", "21623400811");
        verifyBookingConfirmed();
    }
}
