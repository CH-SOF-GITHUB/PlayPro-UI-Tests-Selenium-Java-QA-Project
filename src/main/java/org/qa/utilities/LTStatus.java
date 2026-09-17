package org.qa.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LTStatus {

    /**
     * Envoie un contexte d'étape (Step Context) à LambdaTest pour structurer les logs sur le Dashboard.
     *
     * @param driver   L'instance WebDriver courante
     * @param stepName Le message/description de l'étape
     * @param level    Le niveau de log ("info", "warn", "error")
     */
    public static void addLambdaStepContext(WebDriver driver, String stepName, String level) {
        if (driver == null || stepName == null) return;
        try {
            String jsonPayload = String.format(
                    "lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"%s\", \"level\": \"%s\"}}",
                    stepName, (level == null || level.isEmpty()) ? "info" : level
            );
            ((JavascriptExecutor) driver).executeScript(jsonPayload);
        } catch (Exception e) {
            System.err.println("Impossible d'envoyer le StepContext à LambdaTest: " + e.getMessage());
        }
    }

    /**
     * Surcharge simplifiée avec niveau "info" par défaut.
     */
    public static void addLambdaStepContext(WebDriver driver, String stepName) {
        addLambdaStepContext(driver, stepName, "info");
    }

    /**
     * Marque le test sur LambdaTest via JavascriptExecutor (exécuté dans la session browser).
     * Usage: appeler après l'exécution du test (avant driver.quit()).
     */
    public static void markTestStatusViaJS(WebDriver driver, String status, String remark) {
        if (driver == null) return;
        try {
            /* String status = passed ? "passed" : "failed"; */
            String jsCommand = String.format("lambda-status=%s", status);
            // Certaines intégrations acceptent aussi un message via console log
            ((JavascriptExecutor) driver).executeScript(jsCommand);
            if (remark != null && !remark.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript("console.log(arguments[0])", remark);
            }
        } catch (Exception e) {
            // ne pas casser le flow de test si le marquage échoue
            System.err.println("Impossible de marquer le test via JS With Status: " + e.getMessage());
        }
    }
}
