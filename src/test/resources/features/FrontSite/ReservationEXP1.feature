Feature:  PlayPro Fo - Reservation Experience/Activites

  Background:  Client should be connected firstly
    Given I navigate to login page
    And I enter email "demotenant1@yopmail.com"
    And I enter pwd "Admin1234!"
    When I click Login Button

  @dev
  Scenario:  Reservation Experience Named: "Vr Party Test"
    Given I click on Experience Card
    And I select Nbre of participants 4
    And I select duration price by min 45
    And I click on Continue Btn
    And I select time slot "20:00"
    And I click on Confirm Btn
    And I click on Continue without option Btn
    And I click on bank card by stripe
    When I click on payment now button
    Then Check that reservation was made with "passed"
