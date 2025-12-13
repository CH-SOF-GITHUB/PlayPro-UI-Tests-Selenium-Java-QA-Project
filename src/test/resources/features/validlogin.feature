Feature: Authentification du client sur PlayPro

  @SampleValidLogin
  Scenario: Connexion client valide
    Given Je suis sur le site playpro dev
    When Je clique sur le bouton Se connecter en navbar
    Then Je suis redirigé vers la page de connexion
    When Je saisis un email "bchaker390@yopmail.com"
    And Je saisis  un mot de passe "Admin123!"
    And Je clique sur le bouton Se connecter
    Then Je suis redirigé vers la page d'accueil
    And Je vois le message "Bienvenue Chaker" sur le bouton de profile
    And je prends une image d'écran nommée pour le test réussi
