@allure.label.parentSuite:Cucumber
@allure.label.suite:Authentification
@allure.label.epic:Custom_Portal
@allure.label.feature:User_Authentification
@allure.label.story:Login_with_valid_Credentials
@allure.label.owner:Chaker_Ben_Said
@allure.label.severity:Critical
Feature: Login to system PlayPro

  Scenario: As I customer, I want to log into my PlayPro account, So that I can manage my reservations.
    Given I am on the authentification page
    When I enter valid email
    And I enter valid password
    And I click on login button
    Then I should see the welcome message
