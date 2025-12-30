Feature: sample login sur playpro demo v3

  Rule: Client cannot login with invalid email or invalid password

    Scenario: Successful login with email and password valides
      Given Client access to login page
      When Client enter valid email
      And Client enter valid password
      And Client click in login button
      Then Client login successfully and redirects automatically to home page
