Feature: User Empty Bookings Management

  Scenario: User can view empty bookings
    Given The user is logged into the EventHUB Application
    When User navigates to the My Bookings section
    Then User should see a message indicating no bookings are available
    And User should see the subDescription Text below the message
