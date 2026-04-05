Feature: Buy Gift Voucher Outline
  As a user, I want to buy a gift voucher so that I can give it to someone as a present.

  Background:
    Given I open login page
    And I type email "demotenant2@yopmail.com"
    And I type pwd "Admin1234!"
    When I click on Login Btn

  Scenario Outline: Buying a gift voucher with differents Amounts
    Given Click on Btn Offer a gift voucher
    When Choose your gift card <AMOUNT> CHF
    And I click on Next Btn
    And I click on bank card stripe in cart
    When I click on payment btn
    Then Check that Voucher was buying With "<STATUS>"

    Examples:
      | AMOUNT | STATUS |
      | 250    | passed |
      | 200    | passed |
      | 100    | passed |
      | 50     | passed |
      | 25     | passed |
