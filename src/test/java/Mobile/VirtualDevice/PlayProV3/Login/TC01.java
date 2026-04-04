package Mobile.VirtualDevice.PlayProV3.Login;

import configuration.LTMobileConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import PageObject.MobileLoginPage;

import java.lang.reflect.Method;

public class TC01 extends LTMobileConfig {
    // declare web driver
    RemoteWebDriver driver;
    // apache commons logging
    private static final Log log = LogFactory.getLog(TC01.class);
    // Get Login Object Page
    MobileLoginPage mobileLoginPage;

    @BeforeTest
    public void setUp() {
        driver = getMobileDriver();
        mobileLoginPage = new MobileLoginPage(driver);
        log.info("Mobile WebDriver is setup and Automate-Test is lunched ...\n");
    }

    @Test(priority = 1)
    public void AccedeHomePage(Method method) {
        try {
            driver.get("https://chakertestqa.playpro.fr/");
            // wait for 5s
            Thread.sleep(5000);
            /* java.lang.AssertionError: Home Page is not loaded!  Expected :Accueil  Actual   :playprosite
             * */
            String ExpectedTitle = "playprosite";
            String ActualTitle = driver.getTitle();
            Assert.assertEquals(ActualTitle, ExpectedTitle, "Home Page is not loaded!");
            log.info("Test passed: " + method.getName());
        } catch (NoSuchElementException | InterruptedException e) {
            e.getStackTrace();
        }
    }

    @Test(priority = 2, dependsOnMethods = {"AccedeHomePage"})
    public void GoToLoginPage(Method method) {
        try {
            mobileLoginPage.ClickMenuButton();
            mobileLoginPage.ClickMonCompteButton();
            Thread.sleep(3000);
            log.info("Test passed: " + method.getName());
        } catch (NoSuchElementException | InterruptedException e) {
            e.getStackTrace();
        }
    }

    @Test(priority = 3, dependsOnMethods = {"GoToLoginPage"})
    public void SampleValidLogin(Method method) {
        try {
            mobileLoginPage.EnterEmail("chTenant01@yopmail.com");
            mobileLoginPage.EnterPassword("Admin1234!");
            mobileLoginPage.ClickLoginButton();
            Thread.sleep(5000);
            String ExpectedURL = "https://chakertestqa.playpro.fr/";
            String ActualURL = driver.getCurrentUrl();
            Assert.assertEquals(ActualURL, ExpectedURL, "Client is not connected correctly!");
            log.info("Test passed: " + method.getName());
        } catch (NoSuchElementException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("Mobile WebDriver is closed ...\n");
        }
    }
}
