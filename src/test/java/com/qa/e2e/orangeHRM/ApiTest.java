package com.qa.e2e.orangeHRM;

import io.restassured.response.Response;
import org.qa.base.BaseClass;
import org.qa.utilities.ApiUtility;
import org.qa.utilities.ExtentManager;
import org.qa.utilities.RetryAnalyser;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ApiTest {

    // delete annotation: retryAnalyzer = RetryAnalyser.class; we use transform method of IAnnotationTransformer in TestListener


    @Test
    public void verifyGetUserAPI() {

        // Create an object of SoftAssert
        SoftAssert softAssert = BaseClass.getSoftAsserts();

        // STEP 1: Define api endpoint
        String endpoint = "https://jsonplaceholder.typicode.com/users/1";
        ExtentManager.logStep("API Endpoint: " + endpoint);
        // STEP 2: call the method to send Get request
        ExtentManager.logStep("Sending Get Request to the API ...");
        Response response = ApiUtility.sendGetRequest(endpoint);
        // STEP 3: Validate status code
        ExtentManager.logStep("Validating Status Code ...");
        boolean isStatusCodeOK = ApiUtility.validateStatusCode(response, 200);
        softAssert.assertTrue(isStatusCodeOK, "Error: Status code does not 200ok");
        if (isStatusCodeOK) {
            ExtentManager.logStepForApi("Status code is OK");
        } else {
            ExtentManager.logFailureForApi("Status code is not OK");
        }

        // STEP 4: Validate username from JSON response
        ExtentManager.logStep("Validating response body for username");
        String actualResponseUsername = ApiUtility.getJsonValue(response, "username");
        String expectedResponseUsername = "Bret";
        boolean isUsernameOK = actualResponseUsername.equals(expectedResponseUsername);
        softAssert.assertTrue(isUsernameOK, "Error: username from response body does not match");
        if (isUsernameOK) {
            ExtentManager.logStepForApi("Username is OK");
        } else {
            ExtentManager.logFailureForApi("Username is not OK");
        }
        // STEP 4: Validate username from JSON response
        ExtentManager.logStep("Validating response body for email");
        String actualResponseEmail = ApiUtility.getJsonValue(response, "email");
        String expectedResponseEmail = "Sincere@april.biz";
        boolean isEmailOK = actualResponseEmail.equals(expectedResponseEmail);
        softAssert.assertEquals(actualResponseEmail, expectedResponseEmail, "Error: User Email from response body does not match");
        if (isEmailOK) {
            ExtentManager.logStepForApi("Email is OK");
        } else {
            ExtentManager.logFailureForApi("Email is not OK");
        }

        ExtentManager.logStepForApi("Validation Username & Email for Get USER Request terminated successfully !");
        softAssert.assertAll();
    }
}
