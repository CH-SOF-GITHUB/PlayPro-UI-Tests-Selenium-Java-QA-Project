package com.qa.api.tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ValidateRestfulBookerTest {
    // TC01: Booking - Status 200
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

    // TC02: Booking - GetBookingIds
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

    // TC03: Booking - GetBooking
    @Tag("api")
    @Test
    public void returnsTheBookingById() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given()
                .when()
                .get("/booking/2791")
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    // TC04: Booking - CreateBooking
    @Tag("api")
    @Test
    public void createNewBooking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        // Définition du corps de la requête (Payload au format JSON)
        String jsonContent = """
                        {
                        "firstname" : "Jim",
                        "lastname" : "Brown",
                        "totalprice" : 111,
                        "depositpaid" : true,
                        "bookingdates" : {
                            "checkin" : "2018-01-01",
                            "checkout" : "2019-01-01"
                        },
                "additionalneeds" : "Breakfast"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(jsonContent)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    // TC05: Auth - CreateToken
    @Tag("api")
    @Test
    public void createToken() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String jsonAuth = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }
                """;
        String token =
                given()
                        .header("Content-Type", "application/json")
                        .contentType(ContentType.JSON)
                        .body(jsonAuth)
                        .when()
                        .post("/auth")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath().getString("token");
        System.out.println("user authenticate with token : " + token);
    }

    // TC06: Booking - DeleteBooking
    @Tag("api")
    @Test
    public void deleteBooking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String jsonAuth = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }
                """;
        String token =
                given()
                        .header("Content-Type", "application/json")
                        .contentType(ContentType.JSON)
                        .body(jsonAuth)
                        .when()
                        .post("/auth")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath().getString("token");
        System.out.println("user authenticate with token : " + token);
        // Définition du corps de la requête (Payload au format JSON)
        given()
                .header("Content-Type", "application/json")
                .cookie("token", token)
                .when()
                .delete("/booking/1")
                .then()
                .statusCode(201);
    }

    // TC07: Booking - UpdateBooking
    @Tag("api")
    @Test
    public void UpdateExistingBooking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Étape 1 : Créer une réservation pour obtenir un ID valide à modifier
        String newBookingJson = """
                {
                    "firstname" : "Temporary",
                    "lastname" : "User",
                    "totalprice" : 100,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2026-01-01",
                        "checkout" : "2026-01-05"
                    },
                    "additionalneeds" : "None"
                }
                """;

        int bookingId = given()
                .contentType(ContentType.JSON)
                .body(newBookingJson)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getInt("bookingid");

        System.out.println("Réservation créée avec l'ID : " + bookingId);

        // Étape 2 : Préparer les nouvelles données de mise à jour
        String jsonToUpdate = """
                {
                    "firstname" : "Chaker",
                    "lastname" : "Brown",
                    "totalprice" : 111,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-01-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }
                """;

        // Étape 3 : Envoyer la requête PUT avec l'ID dynamique et l'authentification Basic
        given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(jsonToUpdate)
                .when()
                .put("/booking/" + bookingId) // Utilisation de l'ID généré dynamiquement
                .then()
                .statusCode(200)
                .log()
                .body();
    }
}
