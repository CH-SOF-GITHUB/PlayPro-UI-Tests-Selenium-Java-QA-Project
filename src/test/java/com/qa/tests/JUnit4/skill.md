---
name: creer-tests-selenium-junit4-de-user-story
description: génère des tests automatisés Java avec JUnit 4 et Selenium WebDriver à partir d'une user story ayant au moins un critère d'acceptation. Insère les tests dans les fichiers existants ou crée de nouveaux fichiers si nécessaire.
---

## Couverture
chaque critère d'acceptation doit être couvert par au moins un test

## Nommage fichiers
les classes de test sont nommées avec le suffixe Test, exemple LoginTest.java. Les classes Page Object sont créées si nécessaire, exemple LoginPage.java. Jamais d'accents dans les noms de fichiers, classes ou méthodes.

## Un seul Page Object par fonctionnalité
un seul fichier Page Object par fonctionnalité, contenant les locators Selenium et les méthodes d'interaction avec la page — créé s'il n'existe pas déjà.

## Interdits dans la classe de test
aucun locator CSS/XPath, aucune interaction directe avec Selenium WebDriver — uniquement des appels aux méthodes des Page Objects et des assertions JUnit 4.

## Accents
jamais dans les noms de classes, méthodes ou variables ; tolérés dans les commentaires et descriptions des tests.

## Assertions
utiliser les assertions JUnit 4 pour vérifier les résultats attendus de chaque critère d'acceptation.

## Popups natives du navigateur
quand une confirmation est une popup native du navigateur (window.confirm/alert/prompt), ce n'est pas un élément du DOM : ne jamais lui créer de locator XPath/CSS. La gérer via l'API Alert de Selenium WebDriver (accept, dismiss, getText), encapsulée dans une méthode du Page Object, jamais directement dans la classe de test.

## Tests destructifs sur environnement partagé
un test qui modifie l'état global de façon large et difficile à isoler (ex. suppression de toutes les données d'une liste) doit être exclu des exécutions et de la vérification de non-régression par défaut, et documenter la portée de son impact ainsi que la condition qui le rend sûr à relancer.
