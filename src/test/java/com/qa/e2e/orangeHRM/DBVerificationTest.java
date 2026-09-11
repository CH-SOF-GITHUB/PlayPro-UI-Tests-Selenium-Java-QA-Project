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
import org.testng.asserts.SoftAssert;

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

        // Implement Soft Assert for multiple assertions before failing
        SoftAssert softAssert = getSoftAsserts();


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

        // Validation 1 for first & middle names
        ExtentManager.logStep("Verify first & middle names data employee compare to database ... ");
        String expectedEmpFirstAndMiddleName = empFirstNameDB + " " + empMiddleNameDB;
        softAssert.assertTrue(homePage.verifyEmpFirstAndMiddleName(expectedEmpFirstAndMiddleName), "Error: First & Middle Names are not matching");

        // Validation 2 for last name
        ExtentManager.logStep("Verify last name data employee compare to database ... ");
        softAssert.assertTrue(homePage.verifyEmpLastName(empLastNameDB), "Error: Last Name is not matching");

        ExtentManager.logStep("DB Validation Terminated Successfully !");

        // Mandatory collect all information
        softAssert.assertAll();
    }


}
