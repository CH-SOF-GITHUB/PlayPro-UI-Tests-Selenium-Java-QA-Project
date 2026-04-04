@dev
Feature: Navigation vers la page Réservations

  Background:
    Given Le client est sur la page d'accueil
    And Le client accepte les cookies

  Scenario: Naviguer vers la page Réservations via le bouton "Réserver" sur la home page
    When Le client clique sur le bouton Réserver de la section Nos activités
    Then La page Réservations s'affiche

  Scenario: Naviguer vers la page Réservations via le bouton "Réservations" dans la navbar
    When Le client clique sur le bouton Réservations dans la barre de navigation
    Then La page Réservations s'affiche
