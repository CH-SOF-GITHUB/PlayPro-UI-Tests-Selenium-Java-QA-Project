Feature: Vérification du message lors que le restaurant est fermé

  Background:  Client should be connected firstly
    Given I navigate to login page
    And I enter email "demotenant4@yopmail.com"
    And I enter pwd "Admin1234!"
    When I click Login Button

  @dev
  Scenario: Affichage du message restaurant fermé
    Given je clique sur offre lien en navbar
    When je clique sur Resto lien
    Then un message d'alerte s'affiche "L'établissement est fermé pour le moment."
