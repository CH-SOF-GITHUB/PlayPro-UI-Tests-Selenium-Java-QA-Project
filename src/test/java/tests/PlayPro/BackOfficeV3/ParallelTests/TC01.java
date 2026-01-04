package tests.PlayPro.BackOfficeV3.ParallelTests;

import Levels.Priority;
import config.LTConfigParallelTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TC01 extends LTConfigParallelTest {

    /* Test to be implemented in testng.xml to execute in parallel */
    // define the web driver
    WebDriver driver = null;
    // define explicit wait for web elements conditions
    WebDriverWait wait = null;

    @BeforeTest()
    //@Parameters({"browser", "version", "platform"})
    public void setUp() throws Exception {
        // get the LT driver for parallel tests
        driver = getLTParallelDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        System.out.println("🚀 Parallel Testing is running...\n");
    }

    @Test(priority = Priority.P1)
    public void GoToPlanning() throws InterruptedException {
        driver.get("https://demo.playpro.fr/back-office/login");
        System.out.println("PARALLEL TEST CASE - STEP 1 - Admin opens Login Page");
        // define and enter email valide
        WebElement EmailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        EmailInput.clear();
        EmailInput.sendKeys("bchaker28@yahoo.com");
        System.out.println("PARALLEL TEST CASE - STEP 2 - Admin types a valid email");
        // define and enter password valide
        WebElement PasswordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        PasswordInput.clear();
        PasswordInput.sendKeys("admin");
        System.out.println("PARALLEL TEST CASE - STEP 3 - Admin types a valid password");
        // click on login button
        WebElement LoginButton = driver.findElement(By.cssSelector("button[class='btn-login poppinsRegular']"));
        LoginButton.click();
        System.out.println("PARALLEL TEST CASE - STEP 4 - Admin clicks in login button");
        // wait 5s for load dashboard page
        Thread.sleep(5000);
        // check that admin redirects automatically to planning page after login
        String CurrentURL = driver.getCurrentUrl();
        Assert.assertEquals(CurrentURL, "https://demo.playpro.fr/back-office/planning", "planning page is not loaded");
        WebElement CalendrierLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.sidebarLink[href='/back-office/planning']")));
        String classes = CalendrierLink.getAttribute("class");
        Assert.assertTrue(classes.contains("active"), "Le menu Planning n'est PAS actif dans la sidebar");
    }

    @Test(priority = Priority.P2, dependsOnMethods = {"GoToPlanning"})
    public void FilterBookingByFourJ() throws InterruptedException {
        WebElement FourJBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='4j']")));
        Assert.assertTrue(FourJBtn.isEnabled(), "Le bouton 4j n'est pas cliquable");
        FourJBtn.click();
        // wait for 2 s
        Thread.sleep(2000);
    }

    @Test(priority = Priority.P3, dependsOnMethods = {"FilterBookingByFourJ"})
    public void FilterBookingBySevenJ() throws InterruptedException {
        WebElement SevenJBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='7j']")));
        Assert.assertTrue(SevenJBtn.isEnabled(), "Le bouton 7j n'est pas cliquable");
        SevenJBtn.click();
        // wait for 2 s
        Thread.sleep(2000);
    }


    @Test(priority = Priority.P4, dependsOnMethods = {"FilterBookingBySevenJ"})
    public void GetFiltresDefaultValue() {
        WebElement FiltresBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='filterButton ppv2-flex ppv2-items-center ppv2-gap-3 ppv2-px-4 ppv2-py-2 ppv2-rounded-xl ppv2-border ppv2-border-solid ppv2-border-black ppv2-text-black hover:ppv2-bg-opacity-80 ppv2-transition-all'])[2]")));
        Assert.assertTrue(FiltresBtn.isEnabled(), "Le bouton Filtres n'est pas cliquable");
        String ActualFiltresValue = FiltresBtn.getText();
        String ExpectedFiltresValue = "Filtres";
        Assert.assertEquals(ActualFiltresValue, ExpectedFiltresValue, "La valeur par défaut du bouton Filtres n'est pas correcte");
    }

    @AfterTest()
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("\n🚀 Parallel Testing is TearDown...");
        }
    }

}
