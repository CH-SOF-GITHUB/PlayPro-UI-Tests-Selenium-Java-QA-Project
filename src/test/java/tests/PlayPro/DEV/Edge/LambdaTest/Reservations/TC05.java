package tests.PlayPro.DEV.Edge.LambdaTest.Reservations;

import config.LTConfigEdge;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC05 extends LTConfigEdge {
    private RemoteWebDriver driver;
    // declare status of Test
    static String status = "failed";
    // declare testname
    static String testname = "";

    // add a constructor for this class of test
    public TC05() {
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

    //TC05
    @Test(priority = 1)
    public void NavigateToReservationActiviteVR1() throws InterruptedException {
        try {
            testname = "TC05: Client should navigate to Odyssey VR T12 reservation page via Réserver button on navbar";
            driver.get("https://devsite.playpro.fr/discover/reservation");
            // wait for 5s
            Thread.sleep(5000);
            // declare the explicit wait for web elements
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            // declare the web elements
            WebElement OdysseyVRActivite = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Odyssey VR T12']")));
            OdysseyVRActivite.click();
            // wait for 5s
            Thread.sleep(5000);
            // declare the Expected URL of this activite Circuit VR
            String ExpectedActiviteURL = "https://devsite.playpro.fr/discover/reservation/odyssey-vr-t12";
            boolean isReservationOdysseyVrURL = driver.getCurrentUrl().equals(ExpectedActiviteURL);
            Assert.assertTrue(isReservationOdysseyVrURL, "error: reservation Odyssey VR URL is not displayed");
            String ActualActiviteTitle = driver.getTitle();
            Assert.assertEquals(ActualActiviteTitle, "Odyssey VR T12", "error: Activite title is not displayed");
            status = "passed";
            System.out.println("Test case : " + testname + " & status : " + status);
        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
        }
    }

    @Test(priority = 2, dependsOnMethods = {"NavigateToReservationActiviteVR1"})
    public void BilletsActiviteVR2() throws InterruptedException {
        try {
            testname = "TC05: Client should manage Billets section of Odyssey VR T12 reservation page";
            // declare the explicit wait for web elements
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            // locate and actions of selenium web elements
            WebElement NBParticipants = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='relative']//button[@id='menu-button']")));
            NBParticipants.click();
            status = "passed";
            System.out.println("Test case : " + testname + " & status : " + status);
        } catch (NoSuchElementException e) {
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
