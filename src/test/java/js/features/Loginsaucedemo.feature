@login
Feature: Login

    @regression
    Scenario: User can login with valid credentials
        Given I am on the login page
        When I login with a valid credentials
        When  I am logged in
        Then I am taken into the products page