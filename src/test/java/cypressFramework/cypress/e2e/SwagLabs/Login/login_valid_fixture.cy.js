import loginData from "../../../fixtures/swaglabs_login.json"

// Regrouper la validation de la connexion à partir de la fixture.
describe("Swag Labs Login fixture validation", () => {
    // Ouvrir la page de connexion avant le scénario.
    beforeEach(() => {
        cy.visit('/');
    });

    // Fermer la session après le test avec la commande partagée.
    afterEach(() => {
        cy.logout();
    });

    // Définir le scénario de connexion valide avec les identifiants de la fixture.
    loginData.forEach((credentials) => {
        it("se connecte avec username, password attendus", () => {
            // Vérifier que la fixture contient les identifiants valides attendus.
            expect(credentials, 'identifiants standard valides dans la fixture').to.exist;
            // Saisir le nom d'utilisateur fourni par la fixture.
            cy.get('[data-test="username"]').type(credentials.username);
            // Saisir le mot de passe fourni par la fixture.
            cy.get('[data-test="password"]').type(credentials.password);
            // Soumettre le formulaire de connexion.
            cy.get('[data-test="login-button"]').click();
            // Vérifier la redirection vers la page d'inventaire.
            cy.location('pathname').should('eq', '/inventory.html');
            // Vérifier que le titre Products est visible après la connexion.
            cy.get('[data-test="title"]').should('be.visible').and('have.text', 'Products');
        });
    });
});
