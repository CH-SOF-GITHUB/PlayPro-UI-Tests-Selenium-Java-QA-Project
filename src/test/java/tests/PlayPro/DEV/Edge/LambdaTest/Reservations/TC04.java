package tests.PlayPro.DEV.Edge.LambdaTest.Reservations;

import com.beust.ah.A;
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

public class TC04 extends LTConfigEdge {
    private RemoteWebDriver driver;
    // declare status of Test
    String status = "failed";
    // declare testname
    String testname = "";

    // add a constructor for this class of test
    public TC04() {
    }

    @BeforeTest
    public void setup() throws InterruptedException {
        try {
            // Setup code here
            driver = getLTDriver();
            // open DEV PlayPro home page by default [change to open home page instead of connexion page]
            driver.get("https://devsite.playpro.fr");
            // locate and actions of selenium web elements
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement cookieCloseBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div/div[4]/div/button[1]")));
            cookieCloseBtn.click();
            // locate in login Link to navigate to connexion page and click in it
            WebElement LoginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div[2]/a[2]")));
            LoginLink.click();
            //locate email input and type valid email
            WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
            emailInput.sendKeys("combofamille@yopmail.com");
            // locate password input and type valid password
            WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
            passwordInput.sendKeys("Admin123!");
            // locate and click on login button
            WebElement SeconnecterBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/section/form/button")));
            SeconnecterBtn.click();
        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
        }
    }

    // TC04: Client should navigate to Reservation page via Réserver button on navbar
    @Test()
    public void NavigateToReservationPageViaReservationBtn() {
        try {
            testname = "TC04: Update NavBar - Client should navigate to Reservation page via Réserver button on navbar";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            // locate and actions of selenium web elements
            WebElement ReserverLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div[1]/ul/a[5]")));
            ReserverLink.click();
            // wait for 10s
            Thread.sleep(10000);
            // verify the navigation to reservation page
            String expectedUrl = "https://devsite.playpro.fr/discover/reservation";
            boolean isReservationURL = driver.getCurrentUrl().equals(expectedUrl);
            Assert.assertTrue(isReservationURL, "error : reservation URL is not displayed");
            // verify the title of page
            String ActualTitle = driver.getTitle();
            System.out.println("Actual Title of Page : " + ActualTitle);
            boolean isTitleOfPage = ActualTitle.equals("Réservation");
            Assert.assertTrue(isTitleOfPage, "error : title of reservation page is not displayed");
            status = "passed";
            System.out.println("Test case : " + testname + " & status : " + status);
        } catch (NoSuchElementException | InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            // WE RUN OUR TESTS WITH GITHUB ACTIONS CI
            driver.quit();
        }
    }
}
