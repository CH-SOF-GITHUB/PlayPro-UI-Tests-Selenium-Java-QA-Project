package com.qa.api.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ValidateRestfulBookerTest {

    @Tag("api")
    @Test
    public void latestListings_shouldReturn200() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200);
    }

    @Tag("api")
    @Test
    public void returnsTheIdsOfAllTheBookings() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .log()
                .body();
    }
}
