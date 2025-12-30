package tests.PlayPro.DEV.Edge.LambdaTest.Reservations;

import config.LTConfigEdge;
import org.openqa.selenium.By;
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
            testname = "TC05 - STEP 1: Client should navigate to Odyssey VR T12 reservation page via Réserver button on navbar";
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
    public void ReservationOfBillets() throws InterruptedException {
        try {
            testname = "TC05 - STEP 2: Client should manage Billets section of Odyssey VR T12 reservation page";
            // declare the explicit wait for web elements
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            // locate and actions of selenium web elements
            WebElement NBParticipantsDropDown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@id='menu-button'])[1]")));
            NBParticipantsDropDown.click();
            Thread.sleep(2000);
            // Select Nombre de participants = 3
            WebElement SelectNBParticipants = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='3 participants'])[1]")));
            SelectNBParticipants.click();
            // click in Option (Durée/Prix) to open the dropdown menu
            WebElement OptionDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='menu-button'])[2]")));
            OptionDropdown.click();
            Thread.sleep(2000);
            // Select Option = 120 minutes / 10.00€
            WebElement SelectOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'02h00 à 10.00 €')])[1]")));
            SelectOption.click();
            Thread.sleep(2000);
            // Click in "Suivant" Button
            WebElement Step1SuivantBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
            Step1SuivantBtn.click();
            status = "passed";
            System.out.println("Test case : " + testname + " & status : " + status);
        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
        }
    }

    @Test(priority = 3, dependsOnMethods = {"ReservationOfBillets"})
    public void ReservationOfCreneau() throws InterruptedException {
        try {
            testname = "TC05 - STEP 3: Client should manage Heure et Date section of Odyssey VR T12 reservation page";
            // declare the explicit wait for web elements
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            // locate and actions of selenium web elements
            /*Error:  Failures:
              Error:    TC05.ReservationOfCreneau:123 » Timeout Expected condition failed:
              waiting for element to be clickable: By.xpath: (//p[@translate='no'])[5] (tried for 25 second(s) with 500 milliseconds interval)
            * */
            WebElement Creneau1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@translate='no'])[23]")));
            Creneau1.click();
            status = "passed";
            System.out.println("Test case : " + testname + " & status : " + status);
        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
        }
    }

    @Test(priority = 4, dependsOnMethods = {"ReservationOfCreneau"})
    public void FinaliseOfReservation() throws InterruptedException {
        try {
            testname = "TC05 - STEP 4: Client should manage Final Step 'Paiement'' of Odyssey VR T12 reservation page";
            // declare the explicit wait for web elements
            /*Comment: when I locate questions web elements, for example Input test Long, The "Finaliser Ma Réservation" button is not clickable and
                      I receive white page with error msg: Connexion page failed.... */
            // click to finalise all Steps
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
            WebElement FinaliseReservationBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Finaliser Ma réservation']")));
            FinaliseReservationBtn.click();
            status = "passed";
            System.out.println("Test case : " + testname + " & status : " + status);
        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
        }
    }

    @Test(priority = 5, dependsOnMethods = {"FinaliseOfReservation"})
    public void PanierAndPaiement() throws InterruptedException {
        try {
            testname = "TC05 - STEP 5: Client should Navigate Panier and Paiement of Odyssey VR T12 reservation page";
            // declare the explicit wait for web elements
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            // wait of 5s
            Thread.sleep(5000);
            // check the URL of panier
            String ExpectedPanierURL = "https://devsite.playpro.fr/Panier";
            boolean isPanierURL = driver.getCurrentUrl().equals(ExpectedPanierURL);
            Assert.assertTrue(isPanierURL, "error: Panier URL is not displayed");
            WebElement ReservationTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/section/div[2]/div[2]/div/div[1]/div/span")));
            // check the title of activité
            String ActualReservationTitle = ReservationTitle.getText();
            Assert.assertEquals(ActualReservationTitle, "Odyssey VR T12", "error: Paiement Paragraph Wording is not displayed");
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
