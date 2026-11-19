package com.qa.aiTesting;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/** Data-driven login checks for the Swag Labs application. */
public class LoginDataDrivenTest extends SwagLabsTestBase {

    private static final String PASSWORD = "secret_sauce";

    @DataProvider(name = "loginScenarios")
    public Object[][] loginScenarios() {
        return new Object[][]{
                {"standard_user", PASSWORD, true, ""},
                {"locked_out_user", PASSWORD, false, "locked out"},
                {"standard_user", "mauvais_mdp", false, "Username and password do not match"},
                {"mauvais_user", PASSWORD, false, "Username and password do not match"}
        };
    }

    @Test(dataProvider = "loginScenarios", description = "Validate Swag Labs login scenarios")
    public void loginDisplaysExpectedResult(String username, String password,
                                            boolean expectedToSucceed, String expectedErrorText) {
        loginPage.login(username, password);
        if (expectedToSucceed) {
            Assert.assertTrue(inventoryPage.isLoaded(), "Successful login should display the products page");
            Assert.assertTrue(inventoryPage.isInventoryUrl(), "Successful login should navigate to inventory");
        } else {
            String actualError = loginPage.getErrorMessage();
            Assert.assertFalse(actualError.isBlank(), "A failed login should display an error message");
            if (!expectedErrorText.isBlank()) {
                Assert.assertTrue(actualError.toLowerCase().contains(expectedErrorText.toLowerCase()),
                        "Error message should contain: " + expectedErrorText);
            }
        }
    }
}
