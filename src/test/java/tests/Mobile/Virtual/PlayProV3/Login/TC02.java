package tests.Mobile.Virtual.PlayProV3.Login;

import config.LTMobileConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import tests.PlayPro.PageObject.BasePage;
import tests.PlayPro.PageObject.MobileLoginPage;

import java.io.IOException;
import java.lang.reflect.Method;

public class TC02 extends LTMobileConfig {
    // declare web driver
    RemoteWebDriver driver;
    // apache commons logging
    private static final Log log = LogFactory.getLog(TC02.class);
    // Get Login Object Page
    MobileLoginPage mobileLoginPage;
    BasePage basePage;

    @BeforeTest
    public void setUp() {
        driver = getMobileDriver();
        mobileLoginPage = new MobileLoginPage(driver);
        basePage = new BasePage(driver);
        log.info("Mobile WebDriver is setup and Automate-Test is lunched ...\n");
    }

    @Test(priority = 1)
    public void CheckVisibilityPassword(Method method) {
        try {
            mobileLoginPage.GoToLoginPage();
            Thread.sleep(5000);
            mobileLoginPage.EnterEmail("chTenant01@yopmail.com");
            mobileLoginPage.EnterPassword("Admin1234!");
            mobileLoginPage.ClickShowPwdButton();
            Thread.sleep(3000);
            basePage.TakeScreenshot("password-eye-visible");
            log.info("Test passed: " + method.getName());
        } catch (NoSuchElementException | InterruptedException | IOException e) {
            e.getStackTrace();
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
