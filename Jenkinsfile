pipeline {
    agent any

    // =========================================================
    // Parameters received from Xray Remote Job Trigger
    // =========================================================
    parameters {
        string(
            name: 'projectKey',
            defaultValue: '',
            description: 'Jira/Xray Project Key'
        )

        string(
            name: 'testExecKey',
            defaultValue: '',
            description: 'Xray Test Execution Key'
        )
    }

    // =========================================================
    // Tools
    // =========================================================
    tools {
        maven 'MAVEN-3.9.9'
    }

    // =========================================================
    // Stages
    // =========================================================
    stages {

        // -----------------------------------------------------
        // 1. Checkout
        // -----------------------------------------------------
        stage('Checkout') {
            steps {
                git(
                    branch: 'main',
                    url: 'https://github.com/CH-SOF-GITHUB/PlayPro-UI-Tests-Selenium-Java-QA-Project.git'
                )
            }
        }

        // -----------------------------------------------------
        // 2. Build
        // -----------------------------------------------------
        stage('Build') {
            steps {
                bat 'mvn clean test-compile -DskipTests'
            }
        }

        // -----------------------------------------------------
        // 3. Run Selenium + TestNG
        // -----------------------------------------------------
        stage('Run UI Tests') {
            steps {

                /*
                 * Continue the pipeline even if one or more
                 * automated tests fail.
                 *
                 * This is important because we still need
                 * to send the TestNG results to Xray.
                 */
                catchError(
                    buildResult: 'SUCCESS',
                    stageResult: 'FAILURE'
                ) {

                    bat '''
                        mvn test ^
                        -Dsurefire.suiteXmlFiles=src/test/resources/xml/testng.xml
                    '''
                }
            }
        }

        // -----------------------------------------------------
        // 4. Verify TestNG result
        // -----------------------------------------------------
        stage('Verify TestNG Results') {
            steps {

                bat '''
                    echo ==========================================
                    echo SUREFIRE REPORTS
                    echo ==========================================

                    dir target\\surefire-reports

                    echo.
                    echo ==========================================
                    echo CHECK TESTNG RESULT
                    echo ==========================================

                    if exist target\\surefire-reports\\testng-results.xml (
                        echo testng-results.xml FOUND
                    ) else (
                        echo testng-results.xml NOT FOUND
                        exit /b 1
                    )
                '''
            }
        }

        // -----------------------------------------------------
        // 5. Import results into Xray
        // -----------------------------------------------------
        stage('Import Results to Xray') {
            steps {

                script {

                    echo '=========================================='
                    echo 'XRAY IMPORT'
                    echo '=========================================='

                    echo "Project Key   : ${params.projectKey}"
                    echo "Test Exec Key : ${params.testExecKey}"

                    if (params.testExecKey?.trim()) {

                        echo 'Test Execution key detected.'
                        echo 'Importing TestNG results into Xray...'

                        step([
                            $class: 'XrayImportBuilder',

                            // Xray Cloud server configured in Jenkins
                            serverInstance:
                                'CLOUD-767e6712-2dd3-4e1a-9726-b929b7be49af',

                            // Xray TestNG endpoint
                            endpointName: '/testng',

                            // TestNG result file
                            importFilePath:
                                'target/surefire-reports/testng-results.xml',

                            // Import configuration
                            importInParallel: 'false',
                            importToSameExecution: 'true',

                            // Xray parameters
                            testExecKey: params.testExecKey,
                            projectKey: params.projectKey
                        ])

                        echo 'Xray import completed successfully.'

                    } else {

                        echo 'WARNING: testExecKey is empty.'
                        echo 'Xray import skipped.'
                        echo 'Run the job with Xray parameters.'
                    }
                }
            }
        }

        // -----------------------------------------------------
        // 6. Publish Extent Report
        // -----------------------------------------------------
        stage('Publish Reports') {
            steps {

                publishHTML(
                    target: [
                        allowMissing: true,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,

                        reportDir:
                            'src/test/resources/extentReports',

                        reportFiles:
                            'ExtentReports.html',

                        reportName:
                            'Extent Spark Report',

                        reportTitles:
                            'Selenium Test Results'
                    ]
                )
            }
        }
    }

    // =========================================================
    // Post actions
    // =========================================================
    post {

        // -----------------------------------------------------
        // Always execute
        // -----------------------------------------------------
        always {

            echo '=========================================='
            echo 'PUBLISH TESTNG RESULTS'
            echo '=========================================='

            // Jenkins TestNG/JUnit result publication
            junit(
                testResults: 'target/surefire-reports/*.xml',
                allowEmptyResults: true
            )

            // Archive TestNG result for verification
            archiveArtifacts(
                artifacts:
                    'target/surefire-reports/testng-results.xml',

                allowEmptyArchive: true,

                fingerprint: true
            )

            // Archive Extent report
            archiveArtifacts(
                artifacts:
                    'src/test/resources/extentReports/*.html',

                allowEmptyArchive: true,

                fingerprint: true
            )
        }

        // -----------------------------------------------------
        // Success
        // -----------------------------------------------------
        success {

            echo '=========================================='
            echo 'SUCCESS'
            echo '=========================================='

            echo 'UI Automation Tests executed successfully!'
            echo 'TestNG results were generated.'
            echo 'Xray import completed if testExecKey was provided.'
        }

        // -----------------------------------------------------
        // Failure
        // -----------------------------------------------------
        failure {

            echo '=========================================='
            echo 'FAILURE'
            echo '=========================================='

            echo 'UI Automation Tests failed.'
            echo 'Check the Jenkins console output and reports.'
        }
    }
}
