describe("Lambdatest Login ",() => {
    it("Open the URL", () => {
        cy.visit(
            "https://ecommerce-playground.lambdatest.io/index.php?route=account/login"
        );
    });
    it("Login into the application", () => {
        cy.get('[id="input-email"]').type("lambdatest@yopmail.com");
        cy.get('[id="input-password"]').type("lambdatest");
        cy.get('[type="submit"]').eq(0).click();
    });
    it("Search the Product", () => {
        cy.get('[name="search"]').eq(0).type("VAIO");
        cy.get('[type="submit"]').eq(0).click();
    });
    it("Verify Product after search ", () => {
        cy.contains("Sony VAIO");
    });
});
