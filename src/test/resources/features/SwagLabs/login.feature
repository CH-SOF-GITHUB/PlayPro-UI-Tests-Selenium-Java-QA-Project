@swagLabsLogin
Feature: Swag Labs (SauceDemo)
  As a User,
  I want to log in to the swag labs application,
  so that I can see the inventory page.

  Background:
    Given The user is on the login page

  @smoke
  Scenario: Valid Login
    When The user enters username "standard_user" and password "secret_sauce"
    And clicks the Login button
    Then The inventory page is displayed

  @negative
  Scenario: Wrong Password
    When The user enters username "standard_user" and password "secret_sa"
    And clicks the Login button
    Then The error message "Epic sadface: Username and password do not match any user in this service" is shown

  @inventory
  Scenario: Inventory Page displays 6 Products
    When The user enters username "standard_user" and password "secret_sauce"
    And clicks the Login button
    Then The inventory page displays 6 products

  @outline @errorMessages
  Scenario Outline: Login error messages - <case>
    When The user enters username "<username>" and password "<password>"
    And clicks the Login button
    Then The error message "<message>" is shown

    Examples:
      | case            | username        | password     | message                                                                   |
      | wrong password  | standard_user   | secret_sa    | Epic sadface: Username and password do not match any user in this service |
      | wrong username  | invalid_user    | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | empty username  |                 | secret_sauce | Epic sadface: Username is required                                        |
      | empty password  | standard_user   |              | Epic sadface: Password is required                                        |
      | both empty      |                 |              | Epic sadface: Username is required                                        |
      | locked out user | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
