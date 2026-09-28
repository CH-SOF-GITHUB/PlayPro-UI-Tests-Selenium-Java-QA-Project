package com.qa.tests.ImplicitExplicitWaits;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClassTestDataExplicitWait {
    /*
    ThreadLocal<WebDriver> est utilisé pour que chaque thread possède sa propre instance de WebDriver.
    Sans ThreadLocal, si tu lances tes tests en parallèle, Tous les threads partagent même objet driver.
    * */
    public static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    WebDriverWait wait;

    @DataProvider
    public static Object[][] detailsBookingData() {
        return new Object[][]{
                {1, "John Doe", "john.doe@example.com", "+919876543210"},
                {2, "Sarah Connor", "sarah.connor@gmail.com", "+15550192831"},
                {3, "Alex Smith", "alex.smith@test.com", "+919876541111"}
        };
    }

    @BeforeSuite(alwaysRun = true)
    public void configureLogs() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket").setLevel(Level.SEVERE);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get("https://eventhub.rahulshettyacademy.com/");
        driverThreadLocal.set(driver);
        Reporter.log("Browser launched successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
        driverThreadLocal.remove();
    }

    // =========================================
    // BUSINESS METHODS
    // =========================================

    public void signIn(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);

        WebElement SignInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Sign In'])[1]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", SignInButton);

        Reporter.log("User connected successfully");
    }

    public void clickOnBrowseEvents() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//span[normalize-space()='Browse Events →']"))).click();
        Reporter.log("Browse Events clicked");
    }

    public void clickOnBookNowById(int id) {
        WebElement BookNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@id='book-now-btn'])[" + id + "]")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", BookNow);

        Reporter.log("Book Now clicked for article : " + id);
    }

    public void fillOutCardBookingDetails(String fullName, String email, String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customerName"))).sendKeys(fullName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer-email"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys(phone);

        WebElement BookConfirmNow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='confirm-booking']")));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", BookConfirmNow);

        Reporter.log("Booking form submitted");
    }

    public void verifyBookingConfirmed(String expectedCustomer) {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Booking Confirmed! \uD83C\uDF89')]"))).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-gray-500 text-sm mb-5']"))).isDisplayed());

        String actualCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.bg-indigo-50 div:nth-child(2) span:nth-child(2)"))).getText();
        Assert.assertEquals(actualCustomer, expectedCustomer, "Customer name does not match");
        Reporter.log("Customer verified : " + actualCustomer);
    }

    // =========================================
    // TEST
    // =========================================
    @Test(dataProvider = "detailsBookingData", groups = {"smoke"})
    public void FillOutDetailsBooking(int id, String fullName, String email, String phoneNumber) {
        signIn("bchaker28@yahoo.com", "Q5n@j!i!QnZQmYm");
        clickOnBrowseEvents();
        clickOnBookNowById(id);
        fillOutCardBookingDetails(fullName, email, phoneNumber);
        verifyBookingConfirmed(fullName);
    }
}
