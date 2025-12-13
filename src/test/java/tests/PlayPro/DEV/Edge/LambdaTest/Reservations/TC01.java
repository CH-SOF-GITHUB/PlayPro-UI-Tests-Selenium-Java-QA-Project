package tests.PlayPro.DEV.Edge.LambdaTest.Reservations;

import config.LTConfigEdge;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TC01 extends LTConfigEdge {
    private RemoteWebDriver driver;
    // declare status of Test
    String status = "failed";
    // declare testname
    String testname = "";

    // add a constructor for this class of test
    public TC01() {
    }

    @BeforeClass
    public void setup() throws InterruptedException {
        try {
            // Setup code here
            driver = getLTDriver();
            // open DEV PlayPro home page by default
            driver.get("https://devsite.playpro.fr/connexion");
            // locate and actions of selenium web elements
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement cookieCloseBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div/div[4]/div/button[1]")));
            cookieCloseBtn.click();
            //locate email input and type valid email
            WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
            emailInput.sendKeys("chaker.nehos@gmail.com");
            // locate password input and type valid password
            WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
            passwordInput.sendKeys("Admin1234!");
            // locate and click on login button
            WebElement SeconnecterBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/section/form/button")));
            SeconnecterBtn.click();
        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
        }
    }

    // Your test methods would go here
    @Test(priority = 1)
    public void NavigateToReservationPage01() {
        try {
            testname = "TC01: Client should navigate to Reservation page via Découvrir menu";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement découvrirMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[3]/div[1]/div/button/div/span")));
            découvrirMenu.click();
            WebElement RéserverLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[3]/div[1]/div/button/div/div/div/a[1]")));
            RéserverLink.click();
            // set the expected URL
            // wait for 3s
            Thread.sleep(3000);
            String expectedUrl = "https://devsite.playpro.fr/discover/reservation";
            boolean isReservationURL = driver.getCurrentUrl().equals(expectedUrl);
            Assert.assertTrue(isReservationURL, "error : reservation URL is not displayed");
            status = "passed";
            System.out.println("Test case : " + testname + " & status : " + status);
        } catch (NoSuchElementException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            // WE RUN OUR TESTS WITH GITHUB ACTIONS CI
            driver.quit();
        }
    }
}
