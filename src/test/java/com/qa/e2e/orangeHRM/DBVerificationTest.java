package com.qa.e2e.orangeHRM;

import org.qa.base.BaseClass;
import org.qa.dataprovider.DataProviders;
import org.qa.pages.orangeHRM.HomePage;
import org.qa.pages.orangeHRM.LoginPage;
import org.qa.utilities.DBConnection;
import org.qa.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class DBVerificationTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;


    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
    }

    @Test(dataProvider = "empDataById", dataProviderClass = DataProviders.class)
    public void verifyEmployeeNamesFromDB(String employee_id, String empName) {
        ExtentManager.logStep("Logging with valid credentials");
        loginPage.login("orangehrm_chaker", "AdminOrangeHRM12@!");
        ExtentManager.logStep("Click on PIM Tab");
        homePage.clickOnPIMTab();
        ExtentManager.logStep("Search for Employee ... ");
        homePage.searchOfEmployee(empName);
        ExtentManager.logStep("Get Employee First, Middle Name and Last Name from database ... ");
        // Fetch data into a map
        Map<String, String> empDetails = DBConnection.getEmployeeDetails(employee_id);
        Assert.assertNotNull(empDetails);

        String empFirstNameDB = empDetails.get("firstName");
        String empLastNameDB = empDetails.get("lastName");
        String empMiddleNameDB = empDetails.get("middleName");

        ExtentManager.logStep("Verify first& middle names data employee compare to database ... ");
        String expectedEmpFirstAndMiddleName = empFirstNameDB + " " + empMiddleNameDB;
        Assert.assertTrue(homePage.verifyEmpFirstAndMiddleName(expectedEmpFirstAndMiddleName), "Error: First & Middle Names are not matching");

        ExtentManager.logStep("Verify last name data employee compare to database ... ");
        Assert.assertTrue(homePage.verifyEmpLastName(empLastNameDB), "Error: Last Name is not matching");
    }


}
