package tests.PlayPro.DEV.Edge.LambdaTest.Reservations;

import config.LTConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TC03 extends LTConfig {
    private RemoteWebDriver driver;
    // declare status of Test
    String status = "failed";
    // declare testname
    String testname = "";

    // add a constructor for this class of test
    public TC03() {
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

    @Test(priority = 1)
    public void GoToMesAchatsEtReservations() {
        try {
            testname = "TC03: Client should navigate to Mes Achats et Réservations page via user menu";
            // locate and actions of selenium web elements
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement WelcomeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[4]/div/div/button")));
            WelcomeBtn.click();
            WebElement MesAchatsEtReservationsBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/section[1]/div[2]/button[1]")));
            boolean AchatsReservationsBtnIsDisplayed = MesAchatsEtReservationsBtn.isDisplayed();
            String AchatsReservationsBtnText = MesAchatsEtReservationsBtn.getText();
            Assert.assertTrue(AchatsReservationsBtnIsDisplayed, "Le bouton 'Mes Achats & Réservations' n'existe pas !");
            Assert.assertEquals(AchatsReservationsBtnText, "Mes Achats & Réservations", "Le texte du bouton 'Mes Achats & Réservations' est incorrect !");
            // add more assertions
            WebElement HeaderText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/section[1]/div[3]/div/div/h1")));
            String HeaderTextValue = HeaderText.getText();
            Assert.assertTrue(HeaderText.isDisplayed());
            Assert.assertEquals(HeaderTextValue, "Mes Achats & Réservations", "Le texte de l'en-tête est incorrect !");
            // add more assertions
            WebElement AVenirBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/section[1]/div[3]/div/div/div[2]/button[1]/button")));
            String AVenirBtnText = AVenirBtn.getText();
            Assert.assertTrue(AVenirBtn.isDisplayed());
            Assert.assertEquals(AVenirBtnText, "A venir", "Le texte du bouton 'À venir' est incorrect !");
            // add more assertions
            WebElement HistoriqueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/section[1]/div[3]/div/div/div[2]/button[2]/buttoon")));
            String HistoriqueBtnText = HistoriqueBtn.getText();
            Assert.assertTrue(HistoriqueBtn.isDisplayed());
            Assert.assertEquals(HistoriqueBtnText, "Historique", "Le texte du bouton 'Historique' est incorrect !");
            status = "passed";
            System.out.println("Test case : " + testname + " & status : " + status);
        } catch (java.util.NoSuchElementException e) {
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
