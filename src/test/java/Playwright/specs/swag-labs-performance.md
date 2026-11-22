# SauceDemo — Plan de test de performance

## Périmètre et point de départ

**Cible :** [https://www.saucedemo.com/](https://www.saucedemo.com/)

**Référence du dépôt :** `tests/seed.spec.ts`. Le scénario de départ est une navigation vers une URL. Pour ce plan, l’URL de référence demandée est SauceDemo. Les scénarios utilisent un navigateur et un contexte propres, sauf indication contraire.

**Objectif :** mesurer la rapidité de chargement et la réactivité des parcours principaux de SauceDemo, repérer les régressions et produire des mesures reproductibles. Ce plan porte uniquement sur la performance ; les contrôles fonctionnels servent à atteindre les étapes à mesurer.

**Mesures à recueillir :** durée de navigation, temps jusqu’au DOM interactif lorsque disponible, Largest Contentful Paint (LCP), Cumulative Layout Shift (CLS), temps de réponse des interactions observées, durée des requêtes réseau et erreurs/échecs réseau. Noter le navigateur et sa version, la configuration de la machine, les conditions réseau, l’état du cache et la charge du service pour chaque série.

Aucun seuil de réussite n’est imposé faute d’objectif de service fourni. Établir d’abord une référence sur plusieurs exécutions, puis comparer les médianes et les percentiles (p95 pour les séries suffisamment fournies) à cette référence. Définir les seuils de régression après observation des résultats initiaux.

## Scénarios

### PERF-01 — Chargement initial de la page de connexion

**État initial :** contexte navigateur neuf, cache froid, navigation directe vers l’URL cible.

1. Ouvrir `https://www.saucedemo.com/`.
2. Mesurer le délai jusqu’à ce que le contenu principal soit visible et utilisable.
3. Recueillir les métriques de navigation et Web Vitals disponibles, ainsi que la durée et l’échec éventuel des requêtes.
4. Répéter dans des contextes neufs pour obtenir une série de référence.

**Résultat attendu :** les mesures sont enregistrées pour chaque exécution, sans erreur de chargement inexpliquée. Comparer médiane et dispersion entre exécutions ; relever les ressources ou requêtes qui dominent le chargement.

### PERF-02 — Chargement avec cache chaud

**État initial :** page déjà visitée dans le même contexte ; cache conservé.

1. Recharger la page de connexion.
2. Mesurer les mêmes indicateurs que dans PERF-01.
3. Comparer les résultats au chargement à cache froid.

**Résultat attendu :** l’écart entre cache froid et cache chaud est quantifié et reproductible. Signaler une dégradation au rechargement ou l’absence d’amélioration lorsque les ressources semblent réutilisables.

### PERF-03 — Réactivité de la connexion et affichage du catalogue

**État initial :** page de connexion chargée ; utiliser un compte de démonstration autorisé par l’environnement (par exemple `standard_user`) et le mot de passe de démonstration courant de SauceDemo, sans exposer les identifiants dans les rapports.

1. Saisir les identifiants et lancer la connexion.
2. Mesurer le temps entre l’action de connexion et l’affichage utilisable du catalogue.
3. Relever les durées des requêtes associées et toute erreur réseau.
4. Répéter plusieurs fois dans des sessions propres.

**Résultat attendu :** le temps de transition et sa variabilité sont consignés. Distinguer le temps d’attente côté serveur/réseau du délai de rendu lorsque les données le permettent.

### PERF-04 — Rendu et interaction avec le catalogue

**État initial :** utilisateur connecté et catalogue affiché.

1. Mesurer le temps jusqu’à l’affichage utilisable des produits et des commandes de tri.
2. Changer le tri et mesurer le délai jusqu’à la mise à jour visible de la liste.
3. Ajouter un produit au panier et mesurer le délai jusqu’à la mise à jour visible de l’indicateur du panier.

**Résultat attendu :** les temps de rendu et de réponse aux interactions sont enregistrés séparément. Aucune mise à jour ne doit rester bloquée ; toute latence notable ou fluctuation entre essais est consignée.

### PERF-05 — Navigation vers le panier et retour au catalogue

**État initial :** catalogue chargé avec au moins un article ajouté au panier.

1. Ouvrir le panier et mesurer le délai jusqu’à son affichage utilisable.
2. Revenir au catalogue et mesurer le délai jusqu’à son affichage utilisable.
3. Répéter dans plusieurs sessions pour comparer les mesures.

**Résultat attendu :** les temps de transition sont mesurés et comparables entre exécutions. Relever les chargements complets inattendus, les requêtes lentes et toute variation de contenu qui influence la mesure.

### PERF-06 — Parcours de commande jusqu’au récapitulatif

**État initial :** panier contenant un produit ; utiliser des données synthétiques non personnelles pour les champs de commande.

1. Démarrer le checkout et mesurer l’affichage de l’étape d’informations.
2. Soumettre les données synthétiques et mesurer l’affichage de l’étape de récapitulatif.
3. Recueillir les mesures de navigation, les durées d’interaction et les erreurs réseau sur chaque transition.

**Résultat attendu :** les temps de chaque étape sont consignés séparément afin de localiser une éventuelle régression dans le parcours. Ne pas inclure de données personnelles dans les résultats.

## Conditions d’exécution et rapport

- Exécuter les séries de référence dans un environnement stable, avec navigateur, machine et profil réseau consignés ; éviter de comparer des exécutions aux conditions différentes sans les signaler.
- Effectuer plusieurs répétitions par scénario et publier le nombre d’essais, la médiane, le minimum, le maximum et le p95 lorsque l’échantillon le permet.
- Séparer les résultats à cache froid et à cache chaud ; ne pas mélanger ces populations dans une même statistique.
- Ne lancer ni test de charge ni test de stress contre le site public sans autorisation explicite de l’exploitant. Les scénarios ci-dessus mesurent des parcours séquentiels à faible volume.
- Pour chaque anomalie, fournir l’identifiant du scénario, les conditions, la mesure observée, la référence de comparaison et les erreurs réseau ou console associées.
