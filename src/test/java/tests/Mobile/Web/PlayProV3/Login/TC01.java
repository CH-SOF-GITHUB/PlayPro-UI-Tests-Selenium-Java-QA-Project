package tests.Mobile.Web.PlayProV3.Login;

import config.LTMobileConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC01 extends LTMobileConfig {
    // declare web driver
    RemoteWebDriver driver;
    // apache commons logging
    private static final Log log = LogFactory.getLog(TC01.class);

    @BeforeMethod
    public void setUp() {
        driver = getMobileDriver();
        log.info("Mobile WebDriver is setup and Automate-Test is lunched ...\n");
    }

    @Test
    public void AccedeLoginPage() {
        try{
            driver.get("https://chakertestqa.playpro.fr/");
            // wait for 5s
            Thread.sleep(5000);
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null) {
            driver.quit();
            log.info("Mobile WebDriver is closed ...\n");
        }
    }
}
