package Selenide.UI.PlayPro.Web.Front.Login;

import Selenide.Base;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TC01 extends Base {


    @Test(priority = 1)
    public void UserCanLoginWithValidCredentials() throws InterruptedException {
        // Step 1: Open the login page
        open("/connexion");
        $(By.xpath("//*[@data-testid = 'cookie-banner-footer-sub-group']//*[@data-testid = 'cookie-banner-accept-button']")).click();
        // Step 2: Enter valid username and password
        $("input[name=email]").setValue("demotenant3@yopmail.com");
        $("input[name=password]").setValue("Admin1234!");
        // Step 3: Click on the login button
        $("button[type=submit]").click();
        // Step 4: Verify that the user is successfully logged in and redirected to the dashboard
        Thread.sleep(5000);
        String expectedUrl = "https://demotenant.playpro.fr/";
        String actualUrl = getWebDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test(priority = 2, dependsOnMethods = "UserCanLoginWithValidCredentials")
    public void Logout() throws InterruptedException {
        // Step 1: Click on the user profile icon
        $(By.xpath("(//div[contains(@class,'relative')])[3]")).click();
        // Step 2: Click on the logout button
        $(By.xpath("(//button[normalize-space()='Me déconnecter'])[1]")).click();
        // Step 3: Verify that the user is successfully logged out and redirected to the login page
        Thread.sleep(3000);
        String expectedUrl = "https://demotenant.playpro.fr/";
        String actualUrl = getWebDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}
