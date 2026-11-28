Feature: Swag Labs authentication
  As a shopper
  I want to sign in to Swag Labs
  So that I can browse the available products

  Scenario: A valid shopper signs in
    Given the standard shopper is on the Swag Labs sign-in page
    When they sign in to their account
    Then the product catalog is displayed

  Scenario: A shopper with an invalid password is rejected
    Given the standard shopper is on the Swag Labs sign-in page
    When they sign in with password "secret_sa"
    Then the sign-in error "Epic sadface: Username and password do not match any user in this service" is displayed

  Scenario: A shopper with an locked user
    Given the standard shopper is on the Swag Labs sign-in page
    When they sign in with username "locked_out_user"
    Then the sign-in error "Epic sadface: Sorry, this user has been locked out." is displayed

  Scenario: A shopper with an empty user
    Given the standard shopper is on the Swag Labs sign-in page
    When they sign in with username ""
    Then the sign-in error "Epic sadface: Username is required" is displayed

  Scenario: A shopper with an empty password
    Given the standard shopper is on the Swag Labs sign-in page
    When they sign in with password ""
    Then the sign-in error "Epic sadface: Password is required" is displayed

  @acceptedUsers
  Scenario Outline: A valid shopper signs in with every accepted username
    Given the standard shopper is on the Swag Labs sign-in page
    When they sign in with username "<username>"
    Then the product catalog is displayed

    Examples:
      | username                |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |
