package tests.PlayPro.DEV.Edge.LambdaTest;

import config.LTConfigEdge;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class BackOfficeTest1 extends LTConfigEdge {
    RemoteWebDriver driver;
    // declare status of Test
    String status = "failed";
    // declare testname
    String testname = "";

    // add a constructor for this class of test
    public BackOfficeTest1() {

    }

    @BeforeTest
    public void setup() throws InterruptedException {
        try {
            // Setup code here;
            driver = getLTDriver();
            // open DEV PlayPro home page by default
            driver.get("https://dev-bo.playpro.fr/back-office/login");
            // locate and actions of selenium web elements
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            //locate email input and type valid email
            WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
            emailInput.sendKeys("contact@playpro.fr");
            // locate password input and type valid password
            WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
            passwordInput.sendKeys("admin");
            // locate and click on login button
            WebElement SeconnecterBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/main/div/div/div[1]/div/div/div/div/form/div[4]/div/button")));
            SeconnecterBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    // Your test methods would go here
    @Test(priority = 1)
    public void LoginBO() {
        try {
            // set name of test
            testname = "Login with valid email and pwd";
            // set the URL after login
            String BoLoginURL = driver.getCurrentUrl();
            boolean isLoginURL = BoLoginURL.equals("https://dev-bo.playpro.fr/back-office/planning");
            Assert.assertTrue(isLoginURL, "Login to Back Office failed, error in URL");
            status = "passed";
            // get the name of method test by .reflect
            System.out.println("Test case : " + testname + " and status: " + status);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Test(priority = 4)
    public void Logout() {
        try {
            // set name of test
            testname = "Logout from Back Office";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement AdminMenuBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/header/div/div[4]/div[1]/button")));
            AdminMenuBtn.click();
            WebElement MeDeconnecterLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/header/div/div[4]/div[1]/div/div[4]/a")));
            Assert.assertTrue(MeDeconnecterLink.isDisplayed(), "Logout link is not displayed");
            MeDeconnecterLink.click();
            // assert the URL after logout
            Assert.assertEquals(driver.getCurrentUrl(), "https://dev-bo.playpro.fr/back-office/login");
            // get the name of method test by .reflect & display the final results
            status = "passed";
            // get the name of method test by .reflect
            System.out.println("Test case : " + testname + " and status: " + status);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Test(priority = 2)
    public void BlockTabs() throws IOException {
        // set name of test
        testname = "Check the presence of block tabs in Back Office";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement ManageReservationsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-bs-title=\"Gestion de réservation\"]")));
        WebElement RHTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-bs-title=\"Ressources humaines\"]")));
        WebElement MarketingTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-bs-title=\"Marketing\"]")));
        WebElement ParametresTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-bs-title=\"Paramètres\"]")));
        // assert the presence of block tabs
        Assert.assertTrue(ManageReservationsTab.isDisplayed(), "Manage Reservations tab is not displayed");
        Assert.assertTrue(RHTab.isDisplayed(), "RH tab is not displayed");
        Assert.assertTrue(MarketingTab.isDisplayed(), "Marketing tab is not displayed");
        Assert.assertTrue(ParametresTab.isDisplayed(), "Parametres tab is not displayed");
        // get screenshot if needed
        getScreenshot(driver, ManageReservationsTab, "ManageReservationsTab.png");
        getScreenshot(driver, RHTab, "RHTab.png");
        getScreenshot(driver, MarketingTab, "MarketingTab.png");
        getScreenshot(driver, ParametresTab, "ParametresTab.png");
        // get the name of method test by .reflect & display the final results
        status = "passed";
        // get the name of method test by .reflect
        System.out.println("Test case : " + testname + " and status: " + status);
    }

    @Test(priority = 3)
    public void SuiviDeVentesToFactures() {
        // set name of test
        testname = "Check Suivi De Ventes page in Back Office";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement SuiviDeVentesTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='nav-main-link-name'])[4]")));
        SuiviDeVentesTab.click();
        WebElement FacturesLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='Factures'])[1]")));
        FacturesLink.click();
        // verify that the client is redirected to Factures page
        boolean isFacturesUrl = driver.getCurrentUrl().equals("https://dev-bo.playpro.fr/back-office/invoices");
        Assert.assertTrue(isFacturesUrl, "Facture page is not displayed");
        // get the name of method test by .reflect & display the final results
        status = "passed";
        // get the name of method test by .reflect
        System.out.println("Test case : " + testname + " and status: " + status);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            // WE RUN OUR TESTS WITH GITHUB ACTIONS CI
            driver.quit();
        }
    }
}
