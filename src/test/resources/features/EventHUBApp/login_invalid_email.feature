@XQDP-68
Feature: User login validation

  # CORRECTIF : Le tag doit être placé exactement ici pour lier le rapport JSON au ticket Xray
  Scenario: login validation of invalid format email
    Given User is on EventHUB login page
    When User enters a invalid email "bchaker28."
    And User enters a valid password "Q5n@j!i!QnZQmYm"
    And User clicks on SignIn Button
    Then Email Validation error message should display below Email field
