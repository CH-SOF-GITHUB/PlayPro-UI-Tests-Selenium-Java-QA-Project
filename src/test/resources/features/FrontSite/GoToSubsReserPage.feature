Feature:
  As a user
  I want to go to the subscription reservation page
  So that I can make a reservation for a subscription

  Background:  Client should be connected firstly
    Given I navigate to login page
    And I enter email "demotenant1@yopmail.com"
    And I enter pwd "Admin1234!"
    When I click Login Button

  @dev
  Scenario: Go to the subscription reservation page
    Given I click on Offre menu button
    When I click on the Abonnements link
    Then I should be on the subscription reservation page
