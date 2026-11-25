describe("Swag Labs authentification - cy.prompt", () => {
    it("Se connecte puis se déconnecte et vérifie le retour à la page de connexion", () => {
        cy.prompt([
            'Visit https://www.saucedemo.com',
            'Type "standard_user" in the username field',
            'Type "secret_sauce" in the password field',
            'Click the Login button',
            'Verify the products page is displayed',
            'Open the burger menu in the top left corner',
            'Click the Logout link',
            'Verify the login page is displayed with the Login button visible'
        ]);
    });
});
