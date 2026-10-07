pipeline {
    agent any

    // =========================================================
    // Parameters received from Xray Remote Job Trigger
    // =========================================================
    parameters {
        string(
            name: 'projectKey',
            defaultValue: 'XQDP',
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
                 * Poursuit le pipeline même si certains tests échouent
                 * afin de pouvoir importer les résultats dans Xray.
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
        // 5. Import results into Xray (Nouveau ticket systématique)
        // -----------------------------------------------------
        stage('Import Results to Xray') {
            steps {

                script {

                    echo '=========================================='
                    echo 'XRAY IMPORT'
                    echo '=========================================='

                    def effectiveProjectKey = params.projectKey?.trim() ? params.projectKey : 'XQDP'
                    echo "Project Key : ${effectiveProjectKey}"

                    // Import Xray : Crée une nouvelle exécution dans Jira à chaque fois (Mode Cucumber)
                    step([
                        $class: 'XrayImportBuilder',

                        // Cloud Server Xray configuré dans Jenkins
                        serverInstance:
                            'CLOUD-767e6712-2dd3-4e1a-9726-b929b7be49af',

                        // Endpoint TestNG
                        endpointName: '/testng',

                        // Rapport TestNG à importer
                        importFilePath:
                            'target/surefire-reports/testng-results.xml',

                        // Options d'import
                        importInParallel: 'false',
                        importToSameExecution: 'false', // 'false' garantit la création d'une nouvelle Test Execution dans le Backlog

                        // Clé du projet Jira
                        projectKey: effectiveProjectKey
                    ])

                    echo 'Xray import completed successfully.'
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

            // Publication JUnit/TestNG interne dans Jenkins
            junit(
                testResults: 'target/surefire-reports/*.xml',
                allowEmptyResults: true
            )

            // Conservation uniquement du rapport ExtentReports.html dans "Artefacts du build"
            archiveArtifacts(
                artifacts:
                    'src/test/resources/extentReports/ExtentReports.html',

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
            echo 'New Test Execution created in Xray Jira.'
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
