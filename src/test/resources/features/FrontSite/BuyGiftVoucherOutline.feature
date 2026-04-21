Feature: Buy Gift Voucher Outline
  As a user, I want to buy a gift voucher so that I can give it to someone as a present.

  Background:  Client should be connected firstly
    Given I navigate to login page
    And I enter email "demotenant1@yopmail.com"
    And I enter pwd "Admin1234!"
    When I click Login Button

  @dev
  Scenario Outline: Buying a gift voucher with differents Amounts
    Given Click on Button Offer a gift voucher
    When Choose my gift card <AMOUNT> CHF
    And I click on Next Button
    And I click on bank card stripe
    When I click on payment button
    Then Check that Voucher was buying With "<STATUS>"

    Examples:
      | AMOUNT | STATUS |
      | 250    | passed |
      | 200    | passed |
      | 100    | passed |
      | 50     | passed |
      | 25     | passed |
      | 1      | fail   |
