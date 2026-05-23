# PlayPro-UI-Tests-Selenium-Java

**Description :**  
Projet de tests automatisés de l'interface utilisateur pour le site PlayPro utilisant Selenium WebDriver, Cucumber, et Maven.

**Remarque :**  
Ce projet est lancé et développé pour être utilisé dans IntelliJ IDEA.

---

## Objectif

Ce projet vise à automatiser les tests de l'interface utilisateur du site PlayPro afin d'assurer la qualité et la stabilité des fonctionnalités web.

## Technologies utilisées

- **Selenium WebDriver** : Automatisation des navigateurs web
- **Cucumber** : Framework BDD pour la rédaction des scénarios de tests
- **Maven** : Gestionnaire de dépendances et build
- **IntelliJ IDEA** : Environnement de développement intégré (IDE)

## Prérequis

- Java JDK 11 ou supérieur
- IntelliJ IDEA (ou autre IDE compatible avec Maven)
- Maven

## Installation

1. Cloner ce dépôt :

```bash
git clone https://github.com/CH-SOF-GITHUB/PlayPro-UI-Tests-Selenium-Java.git

## Architecture Cloud et Exécution des tests sur le Cloud (Cross-Browser Testing)

Selon la configuration utilisée, les tests peuvent être exécutés :
- En local (navigateur local)
- Sur LambdaTest Cloud
- Sur TestingBot Cloud
- Exécution des tests sur différents navigateurs (Chrome, Edge, Firefox, etc.)
- Support de plusieurs systèmes d’exploitation (Windows, macOS)
- Gestion des tests à distance via RemoteWebDriver
- Visualisation des vidéos, logs et screenshots dans le dashboard LambdaTest

➡️ Idéal pour valider la compatibilité multi-navigateurs et paralléliser les tests.
➡️ La sélection de la plateforme se fait via des classes java de configuration dédiées


## 
Commandes pour lancer localement (Git Bash / MINGW64) et voir le navigateur :  
export HEADLESS=false && node quickStart.js
Sous Windows (cmd) :  
set HEADLESS=false && node quickStart.js
Sous PowerShell :  
$env:HEADLESS = "false"; node quickStart.js
En CI garder HEADLESS=true (ou ne pas définir) et utiliser xvfb/headless.


# Mettre à jour les refs distantes
git fetch origin

# Basculer sur main pour vérifier l'existence du dossier
git checkout main
ls -la src/test/java/js

# Si le dossier existe sur main, revenir sur ta branche de travail
git checkout circleci-project-setup

# Copier le dossier `src/test/java/js` depuis la branche main vers la branche courante
git checkout main -- src/test/java/js

# Ajouter, committer et pousser les fichiers sur origin/circleci-project-setup
git add src/test/java/js
git commit -m "Import JS Selenium tests from main"
git push -u origin circleci-project-setup


# Ensuite, tu pourras créer une Pull Request pour
Merge 'circleci-project-setup' into 'main' 



# mvn clean test -Dtest=Runners.TestRunner -Dcucumber.filter.tags="@SampleGoogleSearch"
# TestRunner: run: mvn -B -Dtest=Runners.TestRunner test  OR  mvn -B clean test -Pdev -Dtest=Runners.TestRunner
# -B à la commande Maven dans ton workflow. Cela garantit que Maven fonctionne en mode non interactif,
# ce qui est recommandé dans les environnements CI comme GitHub Actions.
# mvn -B test -Dsurefire.suiteXmlFiles=/home/runner/work/PlayPro-UI-Tests-Selenium-Java/PlayPro-UI-Tests-Selenium-Java/src/test/resources/suites/testng.xml
# mvn -B -Dtest=tests.PlayPro.DEV.Edge.LambdaTest.Reservations.TC03 test
# Optional: désactivé car provoque 403
# - name: Update dependency graph
#   uses: advanced-security/maven-dependency-submission-action@571e99aab1055c2e71a1e2309b9691de18d6b7d6
#   env:
#     GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
