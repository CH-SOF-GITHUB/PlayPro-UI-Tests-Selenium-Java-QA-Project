package org.qa.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LTStatus {

    /**
     * Envoie un contexte d'étape à LambdaTest.
     *
     * @param driver   Instance WebDriver
     * @param stepName Nom/description de l'étape
     * @param level    Niveau : info, warn ou error
     */
    public static void addLambdaStepContext(WebDriver driver, String stepName, String level) {

        if (driver == null || stepName == null || stepName.isEmpty()) {
            return;
        }

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String safeStepName = escapeJavaScript(stepName);
            String safeLevel = escapeJavaScript(
                    (level == null || level.isEmpty()) ? "info" : level
            );

            String script =
                    "window.lambdatest_executor = " +
                            "{action: 'stepcontext', arguments: {" +
                            "data: '" + safeStepName + "', " +
                            "level: '" + safeLevel + "'" +
                            "}};";

            js.executeScript(script);

        } catch (Exception e) {
            System.err.println(
                    "Impossible d'envoyer le StepContext à LambdaTest: "
                            + e.getMessage()
            );
        }
    }

    /**
     * Surcharge avec niveau info par défaut.
     */
    public static void addLambdaStepContext(WebDriver driver, String stepName) {
        addLambdaStepContext(driver, stepName, "info");
    }

    /**
     * Marque le statut du test sur LambdaTest.
     *
     * @param driver Instance WebDriver
     * @param status passed / failed / skipped
     * @param remark Message associé au résultat
     */
    public static void markTestStatusViaJS(WebDriver driver, String status, String remark) {
        if (driver == null || status == null || status.isEmpty()) return;
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

    /**
     * Échappe les caractères pouvant casser le JavaScript.
     */
    private static String escapeJavaScript(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\r", "\\r")
                .replace("\n", "\\n");
    }
}
