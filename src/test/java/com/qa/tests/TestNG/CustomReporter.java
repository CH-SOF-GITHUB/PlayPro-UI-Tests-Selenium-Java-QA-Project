package com.qa.tests.TestNG;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CustomReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        // =========================================================
        // Console content
        // =========================================================
        //Iterate over each suite included in the test
        for (ISuite suite : suites) {
            // Following code gets suite name
            String suiteName = suite.getName();
            // Getting the results of the current suite
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                System.out.println("This is a custom Report on Console But we can create HTML page also if needed");
                System.out.println("Passed tests of suite'" + suiteName + "' is: " + tc.getPassedTests().getAllResults().size());
                System.out.println("Failed tests of suite'" + suiteName + "' is: " + tc.getFailedTests().getAllResults().size());
                System.out.println("Skipped tests of suite'" + suiteName + "' is: " + tc.getSkippedTests().getAllResults().size());
            }
        }


        // =========================================================
        // HTML content
        // =========================================================

        StringBuilder html = new StringBuilder();
        html.append("""
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Custom TestNG Report</title>
                
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 30px;
                            background-color: #f4f6f8;
                        }
                
                        h1 {
                            color: #333;
                        }
                
                        h2 {
                            color: #444;
                            margin-top: 30px;
                        }
                
                        table {
                            width: 100%;
                            border-collapse: collapse;
                            margin-top: 20px;
                            background-color: white;
                        }
                
                        th, td {
                            padding: 12px;
                            border: 1px solid #ddd;
                            text-align: left;
                        }
                
                        th {
                            background-color: #333;
                            color: white;
                        }
                
                        .passed {
                            color: green;
                            font-weight: bold;
                        }
                
                        .failed {
                            color: red;
                            font-weight: bold;
                        }
                
                        .skipped {
                            color: orange;
                            font-weight: bold;
                        }
                
                        .summary {
                            display: flex;
                            gap: 20px;
                            margin: 20px 0;
                        }
                
                        .card {
                            padding: 20px;
                            background-color: white;
                            border-radius: 8px;
                            min-width: 120px;
                            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                        }
                    </style>
                </head>
                
                <body>
                
                <h1>Custom TestNG Report</h1>
                """);

        // =========================================================
        // Global counters
        // =========================================================

        int totalPassed = 0;
        int totalFailed = 0;
        int totalSkipped = 0;

        // =========================================================
        // Iterate over suites
        // =========================================================

        for (ISuite suite : suites) {

            String suiteName = suite.getName();

            html.append("<h2>Suite: ")
                    .append(suiteName)
                    .append("</h2>");

            Map<String, ISuiteResult> suiteResults =
                    suite.getResults();

            // =====================================================
            // Iterate over suite results
            // =====================================================

            for (ISuiteResult suiteResult : suiteResults.values()) {

                ITestContext testContext = suiteResult.getTestContext();

                int passed = testContext.getPassedTests().getAllResults().size();

                int failed = testContext.getFailedTests().getAllResults().size();

                int skipped = testContext.getSkippedTests().getAllResults().size();

                int total = passed + failed + skipped;

                totalPassed += passed;
                totalFailed += failed;
                totalSkipped += skipped;

                // =================================================
                // Summary cards
                // =================================================

                html.append("""
                        <div class="summary">
                        """);

                html.append("<div class='card'>")
                        .append("<strong>Total</strong><br>")
                        .append(total)
                        .append("</div>");

                html.append("<div class='card'>")
                        .append("<strong>Passed</strong><br>")
                        .append("<span class='passed'>")
                        .append(passed)
                        .append("</span>")
                        .append("</div>");

                html.append("<div class='card'>")
                        .append("<strong>Failed</strong><br>")
                        .append("<span class='failed'>")
                        .append(failed)
                        .append("</span>")
                        .append("</div>");

                html.append("<div class='card'>")
                        .append("<strong>Skipped</strong><br>")
                        .append("<span class='skipped'>")
                        .append(skipped)
                        .append("</span>")
                        .append("</div>");

                html.append("</div>");

                // =================================================
                // Test results table
                // =================================================

                html.append("""
                        <table>
                            <tr>
                                <th>Test Method</th>
                                <th>Class</th>
                                <th>Status</th>
                            </tr>
                        """);

                // Passed
                for (ITestResult result :
                        testContext.getPassedTests().getAllResults()) {

                    addTestResult(
                            html,
                            result,
                            "PASSED",
                            "passed"
                    );
                }

                // Failed
                for (ITestResult result :
                        testContext.getFailedTests().getAllResults()) {

                    addTestResult(
                            html,
                            result,
                            "FAILED",
                            "failed"
                    );
                }

                // Skipped
                for (ITestResult result :
                        testContext.getSkippedTests().getAllResults()) {

                    addTestResult(
                            html,
                            result,
                            "SKIPPED",
                            "skipped"
                    );
                }

                html.append("</table>");
            }
        }

        // =========================================================
        // Global summary
        // =========================================================

        int totalTests =
                totalPassed + totalFailed + totalSkipped;

        html.append("<h2>Global Summary</h2>");

        html.append("""
                <table>
                    <tr>
                        <th>Total Tests</th>
                        <th>Passed</th>
                        <th>Failed</th>
                        <th>Skipped</th>
                    </tr>
                """);

        html.append("<tr>");

        html.append("<td>")
                .append(totalTests)
                .append("</td>");

        html.append("<td class='passed'>")
                .append(totalPassed)
                .append("</td>");

        html.append("<td class='failed'>")
                .append(totalFailed)
                .append("</td>");

        html.append("<td class='skipped'>")
                .append(totalSkipped)
                .append("</td>");

        html.append("</tr>");
        html.append("</table>");

        // =========================================================
        // HTML Footer
        // =========================================================

        html.append("""
                </body>
                </html>
                """);

        // =========================================================
        // Generate HTML file
        // =========================================================

        createHtmlReport(
                outputDirectory,
                html.toString()
        );
    }

    // =============================================================
    // Add test result to HTML
    // =============================================================

    private void addTestResult(
            StringBuilder html,
            ITestResult result,
            String status,
            String cssClass) {
        html.append("<tr>");

        html.append("<td>")
                .append(result.getMethod().getMethodName())
                .append("</td>");

        html.append("<td>")
                .append(result.getTestClass().getName())
                .append("</td>");

        html.append("<td class='")
                .append(cssClass)
                .append("'>")
                .append(status)
                .append("</td>");

        html.append("</tr>");
    }

    private void createHtmlReport(
            String outputDirectory,
            String htmlContent) {

        // AJOUT
        File reportDirectory = new File(
                System.getProperty("user.dir"),
                "src/test/resources/customReports"
        );

        // AJOUT
        if (!reportDirectory.exists()) {
            reportDirectory.mkdirs();
        }

        // MODIFICATION
        File reportFile = new File(
                reportDirectory,
                "CustomTestNGReport.html"
        );

        try (FileWriter writer = new FileWriter(reportFile)) {
            writer.write(htmlContent);
        } catch (IOException e) {
            throw new RuntimeException("Unable to generate HTML Report", e);
        }
    }

}
