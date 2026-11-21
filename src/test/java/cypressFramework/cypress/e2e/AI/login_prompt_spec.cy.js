describe("Swag Labs - cy.prompt", () => {
    it('Se connecte et vérifier la page des produits', () => {
        cy.prompt(
            [
                'Visit https://www.saucedemo.com',
                'Type "standard_user" in the username field',
                'Type "secret_sauce" in the password field',
                'Click the Login button',
                'Verify the products page is displayed',
            ]
        )
    })
})
