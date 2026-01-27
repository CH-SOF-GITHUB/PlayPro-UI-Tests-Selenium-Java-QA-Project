package tests.PlayPro.BackOfficeV3.Planning;


import config.LTConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.PlayPro.PageObject.BOLoginPage;
import tests.PlayPro.PageObject.BOPlanningPage;

public class TC01 extends LTConfig {
    // logging apache commons
    private static final Log log = LogFactory.getLog(TC01.class);
    RemoteWebDriver driver;
    // Page object 1
    BOLoginPage boLoginPage;
    // Page object 2
    BOPlanningPage boPlanningPage;

    @BeforeMethod
    public void setUp() {
        try {
            driver = getLTDriver();
            boLoginPage = new BOLoginPage(driver);
            boPlanningPage = new BOPlanningPage(driver);
            driver.get(boLoginPage.page_url);
            boLoginPage.enterBOEmail("chaker.nehos@gmail.com");
            boLoginPage.enterBOPassword("Admin1234!");
            boLoginPage.clickRememberMe();
            boLoginPage.clickBOLoginButton();
            // check the testng results
            Thread.sleep(5000);
            String ExpectedURL = "https://chakertestqa-bo.playpro.fr/back-office/planning";
            Assert.assertEquals(driver.getCurrentUrl(), ExpectedURL, "Login Failed!");
            log.info("WebDriver is setup and Automate-Test is lunched using Page Object ...\n");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    @Test(priority = 1)
    public void CheckPlanning01() {
        try {
            boPlanningPage.clickEspacesDropdown();
            boPlanningPage.clickToutcocherButton();
            // check the testng results
            int ExpectedNbOfEspaces = 41;
            int ActualNbOfEspaces = Integer.parseInt(boPlanningPage.getNbOfEspaces());
            Assert.assertEquals(ActualNbOfEspaces, ExpectedNbOfEspaces, "Counting of Espaces is incorrect!");
            Thread.sleep(3000);
            // get screenshot
            boLoginPage.TakeScreenshot("BO-Planning-Espaces-Toutcocher");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("WebDriver is closed for BO - Planning Test-Suite ...\n");
        }
    }
}
