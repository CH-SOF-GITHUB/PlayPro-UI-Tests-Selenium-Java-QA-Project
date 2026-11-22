import {defineConfig} from "cypress";

export default defineConfig({
    projectId: "txbgdo",
    reporter: 'mochawesome',
    reporterOptions: {
        reportDir: 'cypress/results',
        overwrite: false,
        html: false,
        json: true,
    },
    e2e: {
        baseUrl: "https://example.cypress.io",
        setupNodeEvents(on, config) {
            // implement node event listeners here
        },
    },
});
