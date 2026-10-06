Feature: EventHub Login

  @XQDP-76
  Scenario: Successful login with valid credentials
    Given I am on the EventHub login page
    When I enter valid login credentials
    And I click the Sign In button
    Then I should be successfully logged in
