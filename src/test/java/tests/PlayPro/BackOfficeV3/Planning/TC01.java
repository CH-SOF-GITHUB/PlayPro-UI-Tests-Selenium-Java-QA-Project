package tests.PlayPro.BackOfficeV3.Planning;


import config.LTConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import tests.PlayPro.PageObject.BOLoginPage;
import tests.PlayPro.PageObject.BOPlanningPage;
import tests.PlayPro.PageObject.BasePage;

public class TC01 extends LTConfig {
    // logging apache commons
    private static final Log log = LogFactory.getLog(TC01.class);
    RemoteWebDriver driver;
    // Page object 1
    BOLoginPage boLoginPage;
    // Page object 2
    BOPlanningPage boPlanningPage;
    // Page object 3
    // BasePage boBasePage;

    @BeforeTest
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
        } catch (NoSuchElementException | InterruptedException e) {
            e.getStackTrace();
        }
    }


    @Test(priority = 1)
    public void CheckPlanning01() {
        try {
            boPlanningPage.clickEspacesDropdown();
            boPlanningPage.clickToutcocherButton();
            // check the testng results
            String ExpectedNbOfEspaces = "(43)";
            String ActualNbOfEspaces = boPlanningPage.getNbOfEspaces();
            Assert.assertEquals(ActualNbOfEspaces, ExpectedNbOfEspaces, "Counting of Espaces is incorrect!");
            Thread.sleep(3000);
            // get screenshot
        } catch (NoSuchElementException | InterruptedException e) {
            e.getStackTrace();
        }
    }

    @Test(priority = 2, dependsOnMethods = {"CheckPlanning01"})
    public void CheckPlanning02() {
        try {
            // Delete all espaces
            boPlanningPage.clickEffacerButton();
            Thread.sleep(3000);
        } catch (NoSuchElementException | InterruptedException e) {
            e.getStackTrace();
        }
    }

    @Test(priority = 3, dependsOnMethods = {"CheckPlanning02"})
    public void CheckPlanning03() {
        try {
            // open Liste filtres espaces
            boPlanningPage.ClickFiltreButton();
            Thread.sleep(3000);
            String ExpectedFiltreBtn = "Filtres";
            String ActualFiltreBtn = boPlanningPage.GetFiltreButtonText();
            Assert.assertEquals(ActualFiltreBtn, ExpectedFiltreBtn, "Filtre Button is not correct!");
            // get screenshot
        } catch (NoSuchElementException | InterruptedException e) {
            e.getStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("WebDriver is closed for BO - Planning Test-Suite ...\n");
        }
    }
}
