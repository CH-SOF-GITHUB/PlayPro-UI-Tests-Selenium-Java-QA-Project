package com.qa.tests.ImplicitExplicitWaits;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClassTestDataImplicitWait {
    // define ThreadLocal of WebDriver
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @DataProvider(name = "detailsBookingData")
    public Object[][] detailsBookingData() {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
        getDriver().findElement(By.id("email")).sendKeys(email);
        getDriver().findElement(By.id("password")).sendKeys(password);

        WebElement signInButton = getDriver().findElement(By.xpath("(//button[normalize-space()='Sign In'])[1]"));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", signInButton);

        Reporter.log("User connected successfully");
    }

    public void clickOnBrowseEvents() {
        getDriver().findElement(By.xpath("//a//span[contains(text(),'Browse Events')]")).click();
        Reporter.log("Browse Events clicked");
    }

    public void clickOnBookNowById(int id) {
        WebElement bookNow = getDriver().findElement(By.xpath("(//a[@id='book-now-btn'])[" + id + "]"));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", bookNow);

        Reporter.log("Book Now clicked for article : " + id);
    }

    public void fillOutCardBookingDetails(String fullName, String email, String phone) {
        getDriver().findElement(By.id("customerName")).sendKeys(fullName);
        getDriver().findElement(By.id("customer-email")).sendKeys(email);
        getDriver().findElement(By.id("phone")).sendKeys(phone);

        WebElement confirmBooking = getDriver().findElement(By.id("confirm-booking"));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", confirmBooking);

        Reporter.log("Booking form submitted");
    }

    public void verifyBookingConfirmed(String expectedCustomer) {
        Assert.assertTrue(getDriver().findElement(By.xpath("//h3[contains(text(),'Booking Confirmed!')]")).isDisplayed(), "Booking confirmation message is not displayed");

        String actualCustomer = getDriver().findElement(By.cssSelector("div.bg-indigo-50 div:nth-child(2) span:nth-child(2)")).getText();

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
