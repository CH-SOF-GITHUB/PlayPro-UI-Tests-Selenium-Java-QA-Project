package com.qa.aiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

/** Verifies the About and Logout options in the Swag Labs burger menu. */
public class BurgerMenuTest extends SwagLabsTestBase {

    @Test(description = "About opens Sauce Labs and Logout returns to the login page")
    public void aboutRedirectsToSauceLabsAndLogoutReturnsToLogin() {
        loginAsStandardUser();
        menu.open();
        menu.clickAbout();
        Assert.assertTrue(menu.isAboutPage(), "About should redirect to the official Sauce Labs website");

        inventoryPage.navigateBackToInventory();
        menu.open();
        menu.logout();
        Assert.assertTrue(loginPage.isLoginPageUrl(), "Logout should return to the Swag Labs login page");
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form should be displayed after logout");
    }
}
