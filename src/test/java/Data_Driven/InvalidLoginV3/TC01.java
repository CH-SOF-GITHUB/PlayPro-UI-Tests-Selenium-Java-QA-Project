package Data_Driven.InvalidLoginV3;


import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestTag;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.dataprovider.IAbstractDataProvider;
import config.LTConfig;
import dataproviders.DataprovidersSampleTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC01 extends LTConfig implements IAbstractTest, IAbstractDataProvider {
    // declare the web driver to control the browser
    // define explicit wait for web elements
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

    public TC01() {
        super();
    }

    @BeforeTest
    public void setUp() throws Exception {
        // get the driver of LambdaTest
        System.out.println("Driver setup successfully for Data-Driver Testing !\n");
    }

    @Test(
            dataProvider = "dataprovider",
            dataProviderClass = DataprovidersSampleTest.class
    )
    @TestPriority(value = Priority.P1)
    @MethodOwner(owner = "chaker.nehos")
    @TestTag(name = "feature", value = "web")
    public void DisplayErrorMessages(String TUID, String email, String pwd, String ExpectedErrorMsg) throws InterruptedException {
        System.out.println("This is Test Case 01: Display Error Messages for Invalid Login Using Data Provider\n");
        // Write the code of test case here
        driver.get("https://site.playpro.fr/connexion");
        System.out.println("Data-Driven STEP 1: client access to login page");
        WebElement EmailInputV3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='Adresse email'])[1]")));
        EmailInputV3.clear();
        EmailInputV3.sendKeys(email);
        System.out.println("Data-Driven STEP 2: client enter valid email");
        WebElement PasswordInputV3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='Mot de passe'])[1]")));
        PasswordInputV3.clear();
        PasswordInputV3.sendKeys(pwd);
        System.out.println("Data-Driven STEP 3: client enter valid password");
        WebElement LoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Me connecter'])[1]")));
        LoginButton.click();
        System.out.println("Data-Driven STEP 4: client click in login button");
        // sleep for 2 s to check results
        Thread.sleep(2000);
        // verify the error messages of invalid login sent by data provider
        WebElement ErrorMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='text-xs leading-[0.625rem] font-normal text-[#DA1414]']")));
        String ActualErrorMsg = ErrorMsgElement.getText();
        Assert.assertEquals(ActualErrorMsg, ExpectedErrorMsg, "error message not matched ! and test data-driven failed. ⛔");
        System.out.println("Data-Driven Carina TUID: " + TUID + " => error message matched successfully. ✅\n");
    }

    @AfterTest
    public void tearDown() throws Exception {
        // close the driver
        if (driver != null) {
            driver.quit();
            System.out.println("\nData-Driven Test completed successfully !\n");
        }
    }
}
