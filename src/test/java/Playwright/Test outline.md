WebSite of test: https://sqatools.in/automation-practice-page/

1. Check Playwright Version
   Run the Playwright CLI version command: npx playwright --version
2. Run: npx playwright test --headed
3. Run specific playwright TypeScript test: npx playwright test tests/SwagLabs/login.test.ts --headed
4. Désactiver le mode headless (pour voir si Firefox s'ouvre)
      Par défaut, Playwright lance Firefox en arrière-plan (headless). 
      Essayez de le lancer en mode visible (headed) pour voir si le navigateur s'ouvre ou si un message d'erreur s'affiche :
      Dans votre fichier de configuration (playwright.config.ts) ou dans votre test, forcez le lancement en mode non-headless.
5. Run exactement and directly one of tests in spec file:
   Méthode 1 : Utiliser le nom du test dans le terminalVous pouvez utiliser l'option -g (grep) pour filtrer 
               et lancer uniquement le test dont le texte correspond :
               Bash: npx playwright test -g "Connexion avec identifiants valides"
   Vérification : Le terminal indique qu'un seul test a été exécuté avec succès.
   Méthode 2 : Utiliser le nom du fichier et le numéro de lignePlaywright vous permet de cibler directement le fichier 
               et la ligne exacte du test (le test TC02 commence à la ligne 15) :   
               Bash: npx playwright test tests/SwagLabs/login.test.ts:15
6. --workers=1 indique à Playwright d'exécuter les tests séquentiellement (un seul processus / worker à la fois) 
    plutôt qu'en parallèle sur plusieurs workers simultanés.
Dans votre sortie terminal :
Playwright a lancé 3 tests (par exemple sur différents navigateurs ou projets configurés) 
avec un seul worker (Running 3 tests using 1 worker) et les 3 ont réussi (3 passed (34.3s)).
7. (À l'inverse, --workers=2 ou --workers=4 permet d'exécuter plusieurs tests en parallèle pour aller plus vite, 
    au prix d'une charge machine plus élevée pendant la mesure).
