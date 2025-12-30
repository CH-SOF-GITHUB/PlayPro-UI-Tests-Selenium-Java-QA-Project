Feature: User search in google

  Scenario: user search for a keyword in google
    Given I open Google
    When I search for "Google"
    Then I should see results
