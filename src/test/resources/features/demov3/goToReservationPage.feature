@dev
Feature: Go to Reservation Page in PlayPro Demo V3

  Background:
    Given Client opens the home page
    And Client accepts cookies

  Scenario: scenario 1: Navigate to the reservation page from the home page
    Given Client clicks on the Reservations Button
    Then Client should be redirected to the reservation page
    And The reservation page title should be displayed

  Scenario: scenario 2: Access the reservation page via URL
    Given Client enters the reservation page URL directly
    Then Client should see the reservation page content
    And The home page title should not be displayed
