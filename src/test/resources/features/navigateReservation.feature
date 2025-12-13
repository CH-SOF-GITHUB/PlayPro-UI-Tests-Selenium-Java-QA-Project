@ScenarioComplet
Feature: Redirection vers la page de réservation

  Background:
    Given Le client en page d'accueil
    When Le client saisit un email valide
    When   Le client saisit un mot de passe valide
    And Le client clique sur le bouton "Se connecter"
    And Le client attend que la page d'accueil se charge complètement

  @NavigationToReservation
  Scenario: Accéder à la page de réservation
    When il clique sur le bouton "Réserver"
    Then il est redirigé vers la page de réservation avec succès

