// AI and Playwright MCP (planner, generator and healer agents) //

Étape 1 : Mettre à jour Playwright et installer les agents
Bash : npm i -D @playwright/test@latest
       npx playwright init-agents

l'onglet actif dans IntelliJ est bien Codex (Codex | 6 Luna Medium | Default | Auto review connecté à chaker.nehos@gmail.com), 
et vous ne l'avez pas encore testé pour voir si vous avez du crédit disponible.   
Pour utiliser Codex dans IntelliJ avec les Playwright Test Agents, notez d'abord un détail important sur votre configuration actuelle :
Votre commande précédente (npx playwright init-agents --loop=claude) a généré un dossier .claude/agents/, 
qui est le format spécifique à Claude. Pour que Codex reconnaisse nativement les agents Playwright dans IntelliJ, 
il faut générer la configuration adaptée à Codex / OpenAI Agents.   

Initialiser les agents pour Codex Ouvrez votre terminal dans le dossier Playwright (src\test\java\Playwright) 
et exécutez : npx playwright init-agents --loop=codex

Étape 2 : Lancer le Planner (Génération du plan)
Bash : npx playwright agent plan --url "https://www.saucedemo.com/"
OU Ouvrir Chat, choisir Planner, envoyer request: generate a test plan for ...

Vérifier le fichier tests/seed.spec.tsAssurez-vous que votre fichier tests/seed.spec.ts contient bien l'URL 
de votre page de test :   TypeScript
import { test, expect } from '@playwright/test';
test.describe('Test group', () => {
test('seed', async ({ page }) => {
await page.goto('https://sqatools.in/automation-practice-page/');
});
});

Étape 3 : Tester Codex avec le 🎭 Planner
Dans la zone de texte Codex d'IntelliJ ("Describe the task for the agent"), 
envoyez votre demande pour lancer la première étape (le Planner) et vérifier par la même occasion 
si votre compte dispose de crédits actifs :   
Utilise l'agent playwright-test-planner. 
Explore [https://sqatools.in/automation-practice-page/](https://sqatools.in/automation-practice-page/) 
en utilisant tests/seed.spec.ts comme seed, et enregistre un plan de test complet dans specs/automation-practice.md.

Bash : npx playwright agent generate specs/checkout.md --output tests/SwagLabs/generated-checkout.spec.ts
Ce qu'il fait : Il écrit le code de test en utilisant les bonnes pratiques de Playwright (getByRole, getByText, typage strict TypeScript).
Open chat, choisir Generator, envoyer request: generate tests from test plan ...


Étape 4 : Lancer le 🎭 Generator
Validez le prompt pré-rempli dans Codex (cliquez sur la flèche d'envoi ↑ en bas à droite de la zone de texte).   
Conseil : Si le fichier contient beaucoup de scénarios, vous pouvez aussi préciser un sous-dossier cible, 
par exemple : "... et enregistre les fichiers de test dans tests/AutomationPractice/."
          ou: "Utilise l'agent playwright-test-generator pour créer les tests à partir de specs/automation-practice.md."
Laissez l'agent travailler : Le generator va lire chaque scénario (AP-01, AP-02, etc.) 
de specs/automation-practice.md, vérifier les sélecteurs réels sur la page via le navigateur MCP 
et générer les fichiers .spec.ts correspondants dans tests/.


Étape 5 : Exécution & 🎭 Healer
1. Une fois les fichiers .spec.ts créés dans tests/ :
Lancer la suite de tests (au choix) :Via Codex : Cliquez sur la flèche d'envoi (↑) 
pour valider le prompt pré-rempli : 
"Exécute les tests dans tests/AutomationPractice/ et corrige ceux qui échouent."
ou 
"Utilise l'agent playwright-test-healer pour exécuter les tests dans tests/AutomationPractice/ (avec PLAYWRIGHT_BROWSERS_PATH=0 si nécessaire)"

Ou directement dans votre terminal PowerShell pour voir le rapport détaillé :
PowerShell:
$env:PLAYWRIGHT_BROWSERS_PATH="0"
npx playwright test tests/AutomationPractice --project="Google Chrome"

Si un ou plusieurs tests échouent, enchaînez dans Codex avec le 3ᵉ agent (healer) :
2. Si un ou plusieurs tests échouent, enchaînez dans Codex avec le 3ᵉ agent (healer) :
"Utilise l'agent playwright-test-healer pour exécuter les tests en échec et réparer automatiquement les sélecteurs ou attentes."

3. Réparer les tests en échec avec le 🎭 Healer :
Si des tests échouent à cause de sélecteurs approximatifs, demandez à Codex :
"/Utilise l'agent playwright-test-healer. Exécute les tests dans tests/AutomationPractice/, 
analyse les échecs et corrige les sélecteurs ou assertions dans les fichiers .spec.ts jusqu'à ce que la suite passe./"
ou
"Utilise l'agent playwright-test-healer pour analyser et corriger uniquement les tests en échec dans tests/AutomationPractice/."
Bash : npx playwright test --heal
Ce qu'il fait : Si un test échoue (comme notre erreur de visibilité sur le bouton de déconnexion tout à l'heure), 
l'agent Healer analyse l'DOM actuel, corrige le sélecteur défectueux et met à jour votre code de test de manière autonome.
Open chat, envoyer request: fix and run tests again ...


Add Docs Link: https://www.testmuai.com/learning-hub/playwright-ai/
