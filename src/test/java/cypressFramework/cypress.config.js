import {defineConfig} from "cypress";
import {allureCypress} from "allure-cypress/reporter";

export default defineConfig({
    projectId: "txbgdo",
    video: true,  // enable video in cypress cloud
    retries: {    // enable Test Retries
        runMode: 2, // 2 retry in CLI/CI
        openMode: 2 // 2 retry in User Interface
    },
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
            // Call allureCypress to initialize the plugin from the setupNodeEvents function in cypress.config.js
            allureCypress(on, config);

            return config;
        },
    },
});
