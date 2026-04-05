Feature: Buy Gift Voucher Outline
  As a user, I want to buy a gift voucher so that I can give it to someone as a present.

  Background:
    Given I open the login page
    And I type an email "demotenant2@yopmail.com"
    And I type a pwd "Admin1234!"
    When I click on Login Button

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
