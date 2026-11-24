import {defineConfig} from "cypress";

export default defineConfig({
    projectId: "txbgdo",
    reporter: "mochawesome",
    reporterOptions: {
        reportDir: "cypress/results",
        overwrite: false,
        html: false,
        json: true,
    },
    // run Webkit (safari) in cypress
    experimentalWebKitSupport: true,
    e2e: {
        baseUrl: "https://www.saucedemo.com/",
        setupNodeEvents(on, config) {
            // implement node event listeners here
        },
    },
});
