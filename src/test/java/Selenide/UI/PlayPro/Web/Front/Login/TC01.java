package Selenide.UI.PlayPro.Web.Front.Login;

import Selenide.Base;
import Selenide.UI.PlayPro.Web.Front.Pages.CookiePage;
import Selenide.UI.PlayPro.Web.Front.Pages.LoginPage;
import Selenide.UI.PlayPro.Web.Front.Pages.LogoutPage;
import Selenide.UI.PlayPro.Web.Front.Pages.UserProfilePage;
import org.junit.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class TC01 extends Base {
    // define an instances of page objects
    LoginPage loginPage = new LoginPage();
    LogoutPage logoutPage = new LogoutPage();
    CookiePage cookiePage = new CookiePage();
    UserProfilePage userProfilePage = new UserProfilePage();

    @Test(priority = 1)
    public void UserCanLoginWithValidCredentials() throws InterruptedException {
        // Step 1: Open the login page
        loginPage.openLoginPage();
        // Step 2: Click on the accept button of the cookie banner
        cookiePage.clickAcceptButton();
        // Step 2: Enter valid username and password
        loginPage.setEmail("demotenant3@yopmail.com");
        loginPage.setPassword("Admin1234!");
        // Step 3: Click on the login button
        loginPage.clickLoginButton();
        // Step 4: Verify that the user is successfully logged in and redirected to the dashboard
        Thread.sleep(5000);
        String expectedUrl = "https://demotenant.playpro.fr/";
        String actualUrl = getWebDriver().getCurrentUrl();
        // Junit assertion
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test(priority = 2, dependsOnMethods = "UserCanLoginWithValidCredentials")
    public void Logout() throws InterruptedException {
        // Step 1: Click on the user profile icon
        userProfilePage.clickUserProfileIcon();
        // Step 2: Click on the logout button
        logoutPage.clickLogoutButton();
        // Step 3: Verify that the user is successfully logged out and redirected to the login page
        Thread.sleep(3000);
        String expectedUrl = "https://demotenant.playpro.fr/";
        String actualUrl = getWebDriver().getCurrentUrl();
        // Junit assertion
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}
