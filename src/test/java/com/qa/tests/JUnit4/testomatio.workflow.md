Workflow réutilisable pour les prochains tests
Voici l'ordre à suivre à chaque exécution.
1. Exécuter le test Maven
   mvn clean test -Pjunit4 "-Dtest=NomDeTaClasse"

Vérifier que Maven génère le XML et que les tests s'exécutent.

2. Créer une copie nettoyée du XML
Utiliser le script de l'étape 4 en adaptant $source et $clean au nom du rapport de la classe exécutée.

3. Importer dans Testomat.io
   npx @testomatio/reporter xml "CHEMIN_DU_XML_NETTOYE" --lang java --java-tests "src/test/java"

4. Vérifier le rapport
Contrôler le statut passed ou failed, le nombre de tests et les résultats individuels.

# Procédure : JUnit 4 + Maven + Testomat.io
## Étape 1 — Vérifier la configuration JUnit 4
Ton projet Maven utilise le profil junit4. Nous avons vérifié que les tests s'exécutent avec ce profil.
Commande utilisée :
mvn test -Pjunit4 "-Dtest=JunitInventoryTests1"

Résultat attendu :
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS

Pour les prochains tests, remplace JunitInventoryTests1 par le nom de ta classe de test.

## Étape 2 — Identifier le test dans Testomat.io
Dans ton fichier :
src/test/java/com/qa/tests/Junit4/JunitInventoryTests1.java

Le test concerné est :
@TestId("043d1f7a")
@Test
public void testEachProductHasButtonAddToCart() {
// Code du test
}

L'identifiant 043d1f7a doit correspondre au cas de test concerné dans Testomat.io.

Important : les logs ont confirmé que le reporter retrouvait bien le nom du test et cet identifiant. 
Nous n'avons donc pas eu besoin de modifier l'annotation pour résoudre le problème final.

## Étape 3 — Générer le rapport XML avec Maven
Pour générer le rapport XML du test :
mvn clean test -Pjunit4 "-Dtest=JunitInventoryTests1"

Le rapport est créé à cet emplacement :
target/surefire-reports/TEST-com.qa.tests.JUnit4.Junit4Inventory2Tests.xml

Le nom du fichier dépend du nom complet de la classe de test.

## Étape 4 — Nettoyer une copie du rapport XML
C'est l'étape importante qui a permis de contourner le problème ENOENT rencontré avec le reporter sous Windows.
L'erreur précédente indiquait :
ENOENT: no such file or directory, open 'E:\...\PlayPro-UI-Tests-Selenium-Java-QA-Project\C'

Nous avons créé une copie du XML original, sans les éléments <system-err> et <system-out>, afin de vérifier 
si les sorties de diagnostic contenaient un chemin problématique.

Commande PowerShell utilisée :
$source = "target/surefire-reports/TEST-com.qa.tests.JUnit4.Junit4Inventory2Tests.xml"
$clean  = "target/surefire-reports/TEST-JunitInventoryTests1-clean.xml"

[xml]$xml = Get-Content -LiteralPath $source -Raw

$xml.SelectNodes("//system-err | //system-out") |
ForEach-Object {
[void]$_.ParentNode.RemoveChild($_)
}

$xml.Save((Join-Path (Get-Location) $clean))

Write-Host "XML original conservé : $source"
Write-Host "Copie de test créée : $clean"


### Résultat :
- XML original conservé.
- Copie nettoyée créée.
- Les résultats des tests restent dans le rapport.
  Ce nettoyage a permis de réussir l'import dans notre cas. Il reste à confirmer si les deux éléments retirés sont bien la cause exacte de l'erreur ENOENT.
  Étape 5 — Importer le XML nettoyé dans Testomat.io
  Nous avons utilisé la commande suivante :
  npx @testomatio/reporter xml "target/surefire-reports/TEST-JunitInventoryTests1-clean.xml" --lang java --java-tests "src/test/java"

Explication des paramètres :
Paramètre	Utilité
xml	Importe un rapport XML
Chemin du XML	Indique le rapport à importer
--lang java	Précise le langage des tests
--java-tests "src/test/java"	Indique où retrouver les sources Java

La commande crée un nouveau rapport dans Testomat.io.



#### Import Junit5 Tests / Import Project from Source Code
curl -L -O -z testomatio.jar https://github.com/testomatio/java-check-tests/releases/latest/download/testomatio.jar && set TESTOMATIO=tstmt_PIYAdyOv5Zm3b6bY4WOw_vk49NLsdZ-HiQ1793099007&& java -jar testomatio.jar sync

🛠️ La commande adaptée pour PowerShell :
Exécutez ce bloc complet dans votre terminal PowerShell :
curl.exe -L -O -z testomatio.jar https://github.com/testomatio/java-check-tests/releases/latest/download/testomatio.jar; $env:TESTOMATIO="tstmt_PIYAdyOv5Zm3b6bY4WOw_vk49NLsdZ-HiQ1793099007"; java -jar testomatio.jar sync
