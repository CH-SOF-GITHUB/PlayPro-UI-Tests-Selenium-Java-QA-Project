1. Le problème : Pourquoi l'IA écrit du vieux code Selenium ?
   La grande majorité des données sur lesquelles les grands modèles de langage (LLM) ont été entraînés 
   proviennent de tutoriels, de blogs et de forums datant de l'ère Selenium 2 et Selenium 3. 
   Par conséquent, lorsqu'on ne les recadre pas, les agents ont tendance à reproduire machinalement 
   des pratiques obsolètes, voire déconseillées :
   1.1 Utiliser des Thread.sleep() au lieu d'attentes explicites (la cause numéro un des tests instables/flaky). 
   1.2 Télécharger et gérer manuellement des fichiers binaires de drivers (comme chromedriver.exe) ou utiliser 
       des gestionnaires tiers obsolètes.
   1.3 Utiliser des sélecteurs XPath absolus ou des noms de classes générés qui cassent au moindre changement d'interface.
   1.4 Utiliser l'ancien protocole Chrome DevTools Protocol (CDP) au lieu du nouveau standard W3C WebDriver BiDi.
   1.5 Utiliser DesiredCapabilities au lieu des classes Options modernes.

2. La solution proposée par Selenium
   Pour éviter que l'IA ne devine ou ne pioche dans ses souvenirs obsolètes, la documentation recommande 
   de lui fournir un cadre strict, exactement comme on le ferait pour un nouveau développeur humain :

   2.1 Pointer vers l'index officiel (llms.txt) et le répertoire d'exemples officiels sur GitHub, qui contiennent 
       du code actualisé et testé en continu.
   2.2 Créer un fichier de règles pour le projet (comme CLAUDE.md, AGENTS.md ou .cursor/rules/) qui liste explicitement 
       les interdictions et les bonnes pratiques :
       2.2.1 Interdiction d'utiliser les sleeps (préférer les Explicit Waits).
       2.2.2 Utilisation native de Selenium Manager (intégré depuis Selenium 4.6+) pour gérer automatiquement 
             les navigateurs et drivers sans configuration de chemins.
       2.2.3 Privilégier les localisateurs stables (id, name, attributs data-test).
   2.3 Utiliser la version exacte de Selenium du projet (par exemple Selenium 4).

3. Comment interagir avec l'IA pour créer vos tests dans aiTesting ?
   Puisque vous travaillez maintenant en pur Selenium + TestNG dans le dossier aiTesting, vous pouvez donner 
   des instructions directes (prompts) à votre assistant en vous appuyant sur ses capacités.

   Voici des exemples de prompts ultra-efficaces à lui donner :
   Pour créer un test de zéro :
   "En te basant sur les règles du fichier AGENTS.md, écris une classe de test TestNG pure Java 
    dans le package com.qa.aiTesting qui teste le tri des produits de A à Z sur Swag Labs. 
    Utilise des attentes explicites et structure ton code proprement."

4. Pour exécuter le test ProductSortTest.java, vous pouvez simplement taper une instruction claire directement 
   dans le champ de discussion de l'agent à droite.
   Voici le type de message que vous pouvez lui écrire :
   "Exécute le test ProductSortTest via Maven en ciblant uniquement cette classe, puis montre-moi le résultat de l'exécution."
   L’exécution ciblée a réussi après correction du BOM dans le fichier Java.
   Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
   BUILD SUCCESS
   Selenium a signalé un avertissement CDP : Chrome utilise la version 157, tandis que Selenium a trouvé 
   une correspondance proche pour la version 155. Le test de tri A à Z a tout de même réussi.

5. Prompt pour la manipulation du POM Pages: 
   Example: "Je vais respecter l’encapsulation POM en plaçant les interactions et locators Swag Labs dans un petit objet de page dédié,"  

Je vais d’abord cartographier les locators et interactions utilisés par les huit tests, 
puis les déplacer dans six objets de page/composants réutilisables sous com.qa.aiTesting.pages. 
Je mettrai à jour les tests et la règle racine AGENTS.md, puis lancerai toute la suite aiTesting en vérifiant 
que chaque navigateur est maximisé.
Ran 1 command, used 1 tool

J’ai confirmé qu’il y a aussi CartManagementTest dans le dossier, en plus des classes citées. 
Je vais l’inclure dans le refactoring pour qu’aucun test du package ne conserve de locator Selenium en direct ; 
l’initialisation et la maximisation du navigateur seront aussi centralisées.
Ran 5 commands, edited 4 files, used 6 tools

Les tests n’utilisent plus directement By, findElement ou WebElement : ces détails sont maintenant dans les objets de page. 
Je complète la consigne racine pour rendre cette séparation obligatoire et mets à jour testng.xml 
pour inclure toutes les classes du dossier, y compris celles ajoutées depuis la création initiale de la suite.
Edited 2 files, used 2 tools

Les neuf classes du package figurent maintenant dans la suite XML. 
Le setup commun crée et maximise une nouvelle fenêtre Chrome à chaque méthode TestNG ; 
je lance à présent l’intégralité de cette suite via Maven.
