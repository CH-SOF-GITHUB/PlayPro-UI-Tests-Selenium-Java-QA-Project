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
            description: 'Xray Test Execution Key (Ex: XQDP-74). Laissez vide pour créer un nouveau ticket dans le Backlog.'
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
        // 5. Import results into Xray (Existant ou Nouveau)
        // -----------------------------------------------------
        stage('Import Results to Xray') {
            steps {

                script {

                    echo '=========================================='
                    echo 'XRAY IMPORT'
                    echo '=========================================='

                    def effectiveProjectKey = params.projectKey?.trim() ? params.projectKey : 'XQDP'

                    def xrayConfig = [
                        $class: 'XrayImportBuilder',
                        serverInstance: 'CLOUD-767e6712-2dd3-4e1a-9726-b929b7be49af',
                        endpointName: '/testng',
                        importFilePath: 'target/surefire-reports/testng-results.xml',
                        importInParallel: 'false',
                        projectKey: effectiveProjectKey
                    ]

                    // Si une clé d'exécution est fournie
                    if (params.testExecKey?.trim()) {
                        echo "Mise à jour directe du Test Execution : ${params.testExecKey}"
                        xrayConfig['testExecKey'] = params.testExecKey
                        xrayConfig['importToSameExecution'] = 'true'
                    } else {
                        echo "Aucun Test Execution fourni. Création d'un NOUVEAU ticket dans le Backlog..."
                        xrayConfig['importToSameExecution'] = 'false'
                    }

                    step(xrayConfig)

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

            // Publication des résultats de tests dans Jenkins
            junit(
                testResults: 'target/surefire-reports/*.xml',
                allowEmptyResults: true
            )

            // Conservation uniquement du rapport visual ExtentReports.html
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
            echo 'Xray import completed.'
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
