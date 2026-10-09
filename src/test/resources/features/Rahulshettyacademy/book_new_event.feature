Feature: Event Booking Management

  Scenario: User can book an event successfully
    Given The User is logged into the EventHUB Application
    When User navigates to the Events section
    And User selects an available event "1"
    And User selects the number of tickets to book
    And User enters the booking details
    And User clicks the Confirm Booking button
    Then User should see a booking confirmation message
    And User should see the booking reference
    And User should see the customer name and number of tickets
    And User should see the correct total booking amount
