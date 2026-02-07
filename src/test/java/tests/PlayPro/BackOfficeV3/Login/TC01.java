package tests.PlayPro.BackOfficeV3.Login;

import config.LTConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import PageObject.BOLoginPage;

public class TC01 extends LTConfig {

    // apache commons logging
    private static final Log log = LogFactory.getLog(TC01.class);
    RemoteWebDriver driver;
    BOLoginPage boLoginPage;

    @BeforeMethod
    public void setUp() {
        driver = getLTDriver();
        boLoginPage = new BOLoginPage(driver);
        driver.get(boLoginPage.page_url);
        log.info("WebDriver is setup and Automate-Test is lunched using Page Object ...\n");
    }

    @Test
    public void Login01() {
        try {
            boLoginPage.enterBOEmail("chaker.nehos@gmail.com");
            boLoginPage.enterBOPassword("Admin1234!");
            boLoginPage.clickRememberMe();
            boLoginPage.clickBOLoginButton();
            // check the testng results
            Thread.sleep(5000);
            String ExpectedURL = "https://chakertestqa-bo.playpro.fr/back-office/planning";
            Assert.assertEquals(driver.getCurrentUrl(), ExpectedURL, "Login Failed!");
            // get screenshot
            // boLoginPage.TakeScreenshot("BO-Login-Success");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("WebDriver is closed for BO - Login Test-Suite ...\n");
        }
    }
}
