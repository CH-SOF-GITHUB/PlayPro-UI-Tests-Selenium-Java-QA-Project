package tests.PlayPro.DEV.LambdaTest.Chrome.Reservations;

import Levels.Priority;
import configuration.LTConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC01 extends LTConfig {

    // declare the web driver to control the browser
    RemoteWebDriver driver = null;
    // define explicit wait for web elements
    WebDriverWait wait = null;
    // declare specific web elements
    Boolean HeaderTitle = null;
    // WebElement SUBHeaderTitle = null;

    // create a constructor
    public TC01() {

    }

    @BeforeTest
    public void setUp() throws Exception {
        // get the driver of LambdaTest
        driver = getLTDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        System.out.println("Driver setup successfully for Test Suite !\n");
    }

    @Test(priority = Priority.P1)
    public void ClickToNavigateToReservationsPage1() throws InterruptedException {
        // open the home page of Demo V3 PlayPro site
        driver.get("https://site.playpro.fr/");
        // define the Reservations Button in navbar and click it
        WebElement ReservationsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div[1]/ul/a[6]")));
        ReservationsLink.click();
        // check the redirection to reservations page
        Thread.sleep(5000);
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://site.playpro.fr/discover/reservation", "Client is not navigated to Reservations page !");
    }

    @Test(priority = Priority.P2)
    public void ClickToNavigateToReservationsPage2() {
        driver.get("https://site.playpro.fr/");
        // Vérifier que le titre existe sur la home
        By headerTitleLocator = By.xpath("(//h1[normalize-space()='Réserve ton Padel à Macon'])[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(headerTitleLocator));
        // Naviguer vers la page réservation
        driver.get("https://site.playpro.fr/discover/reservation");
        // Vérifier que le titre de la home N'EXISTE PLUS
        boolean isHeaderPresent = !driver.findElements(headerTitleLocator).isEmpty();
        Assert.assertFalse(isHeaderPresent,
                "Header title should NOT be present on reservation page");
    }

    @AfterTest
    public void tearDown() throws Exception {
        // close the driver
        if (driver != null) {
            driver.quit();
            System.out.println("\nTest Suite Reservations completed successfully !\n");
        }
    }
}
