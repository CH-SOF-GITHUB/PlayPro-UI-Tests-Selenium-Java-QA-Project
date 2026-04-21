Feature: PlayPro - Reservation - Buy a new gift voucher

  Background:  Client should be connected firstly
    Given I navigate to login page
    And I enter email "demotenant1@yopmail.com"
    And I enter pwd "Admin1234!"
    When I click Login Button

  @dev
  Scenario: Buy a new gift voucher 250 CHF (for example) and send it to a friend
    Given Click on Btn Offer a gift voucher
    When Choose your gift card 250 CHF
    And I click on Next Btn
    And I click on bank card stripe in cart
    When I click on payment btn
    Then Check that Voucher was buying successfully
