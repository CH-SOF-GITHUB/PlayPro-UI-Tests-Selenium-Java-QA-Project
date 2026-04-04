@dev
Feature: sample login sur playpro demo v3 avec parameter type

  Rule: Client cannot login with invalid email or invalid password

    Scenario: Successful login with email and password valides using parameter type
      Given Client access to connexion page
      When Client type valid email "chaker-qa-playpro-pro@yopmail.com"
      And Client type valid password "Admin1234!"
      And Client clicks in login button
      Then Client login successfully and go to home page
