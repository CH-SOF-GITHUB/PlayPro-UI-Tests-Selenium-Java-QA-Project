Feature: Swag Labs authentication
  As a shopper
  I want to sign in to Swag Labs
  So that I can browse the available products

  Scenario: A valid shopper signs in
    Given the standard shopper is on the Swag Labs sign-in page
    When they sign in to their account
    Then the product catalog is displayed
