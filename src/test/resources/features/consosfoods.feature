@ScenarioComplet
Feature: La reservation de restaurant ("Resto") en Playpro site web

  Background:
    Given Le client en page d'accueil
    When Le client saisit un email valide
    When   Le client saisit un mot de passe valide
    And Le client clique sur le bouton "Se connecter"
    And Le client attend que la page d'accueil se charge complètement

  @NavigationToResto
  Scenario: Le client doit accéder à la page Resto
    Given Le client clique sur le bouton "Découvrir" de menu liste
    When il clique sur le lien "Resto"
    Then il est redirigé vers la page Resto avec succès
