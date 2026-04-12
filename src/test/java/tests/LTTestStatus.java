// java
package tests;

import configuration.LTConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class LTTestStatus {

    /**
     * Marque le test sur LambdaTest via JavascriptExecutor (exécuté dans la session browser).
     * Usage: appeler après l'exécution du test (avant driver.quit()).
     */
    public static void markTestStatusViaJS(WebDriver driver, boolean passed, String remark) {
        if (driver == null) return;
        try {
            String status = passed ? "passed" : "failed";
            String jsCommand = String.format("lambda-status=%s", status);
            // Certaines intégrations acceptent aussi un message via console log
            ((JavascriptExecutor) driver).executeScript(jsCommand);
            if (remark != null && !remark.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript("console.log(arguments[0])", remark);
            }
        } catch (Exception e) {
            // ne pas casser le flow de test si le marquage échoue
            System.err.println("Impossible de marquer le test via JS: " + e.getMessage());
        }
    }

    /**
     * Met à jour le statut de la session LambdaTest via l'API REST.
     * Nécessite l'ID de session (récupéré depuis RemoteWebDriver.getSessionId()).
     * Exemple de body envoyé: { "status": "passed", "remark": "All good" }
     */
    public static void markTestStatusViaREST(RemoteWebDriver driver, boolean passed, String remark) {
        if (driver == null) return;
        try {
            SessionId sessionId = driver.getSessionId();
            if (sessionId == null) return;

            String id = sessionId.toString();
            String apiUrl = "https://api.lambdatest.com/automation/api/v1/sessions/" + id;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            String auth = LTConfig.Username + ":" + LTConfig.AccessKey;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);

            String status = passed ? "passed" : "failed";
            String json = String.format("{\"status\":\"%s\",\"remark\":\"%s\"}", status, escapeJson(remark));

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                System.err.println("LambdaTest REST update failed, HTTP code: " + responseCode);
            }

            conn.disconnect();
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour via REST: " + e.getMessage());
        }
    }

    // simple escape pour les guillemets/newlines dans le remark
    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
}
