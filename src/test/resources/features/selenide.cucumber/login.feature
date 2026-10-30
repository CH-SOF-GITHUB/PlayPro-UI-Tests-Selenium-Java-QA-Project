Feature: Selenide - Playpro - Login System

  Scenario: Selenide - Client should login with valid email and password
    Given I am on the login page
    When I accept cookie
    And I enter valid email and password
    And I click the login button
    Then I should be logged in successfully
