Feature: Redirection du client vers la page de connexion

  @SampleAccessLogin
  Scenario: Client doit accéder à la page de connexion
    Given client est dans la page d'accueil
    When client clique sur le bouton Se connecter en navbar
    And client attend 5000s pour le chargement de la page de connexion
    Then client est redirigé vers la page de connexion
