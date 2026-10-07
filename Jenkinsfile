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
            description: 'Xray Test Execution Key (Ex: XQDP-74). Si renseigné, met à jour ce ticket ET crée une entrée dans le Backlog.'
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
        // 5. Import results into Xray (Double Import)
        // -----------------------------------------------------
        stage('Import Results to Xray') {
            steps {

                script {

                    echo '=========================================='
                    echo 'XRAY IMPORT'
                    echo '=========================================='

                    def effectiveProjectKey = params.projectKey?.trim() ? params.projectKey : 'XQDP'

                    def baseConfig = [
                        $class: 'XrayImportBuilder',
                        serverInstance: 'CLOUD-767e6712-2dd3-4e1a-9726-b929b7be49af',
                        endpointName: '/testng',
                        importFilePath: 'target/surefire-reports/testng-results.xml',
                        importInParallel: 'false',
                        projectKey: effectiveProjectKey
                    ]

                    if (params.testExecKey?.trim()) {

                        // 1. Injection dans le Test Execution ciblé (ex: XQDP-74)
                        echo "1/2 : Injection dans le Test Execution spécifié : ${params.testExecKey}"
                        def configTarget = baseConfig.clone()
                        configTarget['testExecKey'] = params.testExecKey
                        configTarget['importToSameExecution'] = 'true'
                        step(configTarget)

                        // 2. Création automatique d'une nouvelle exécution dans le Backlog
                        echo "2/2 : Création d'un nouveau ticket Execution Results dans le Backlog..."
                        def configNew = baseConfig.clone()
                        configNew['importToSameExecution'] = 'false'
                        step(configNew)

                    } else {
                        echo "Aucun testExecKey fourni. Création d'une nouvelle Test Execution dans le Backlog..."
                        def configNew = baseConfig.clone()
                        configNew['importToSameExecution'] = 'false'
                        step(configNew)
                    }

                    echo 'Xray import completed successfully.'
                }
            }
        }

        // -----------------------------------------------------
        // 6. Publish Extent Report
        // -----------------------------------------------------
        stage('Publish Reports') {
            steps {
                // 1. Publication HTML Extent Report
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
                // 2. Publication HTML Emailable Report
                publishHTML(
                    target: [
                        allowMissing: true,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,

                        reportDir:
                            'target/surefire-reports',

                        reportFiles:
                            'emailable-report.html',

                        reportName:
                            'TestNG Emailable Report',

                        reportTitles:
                            'TestNG Emailable Report'
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

            // Publication native TestNG dans "Résultats des tests" de Jenkins
            testng(
                reportFilenamePattern: '**/target/surefire-reports/testng-results.xml',
                allowEmptyResults: true
            )

            // Conservation uniquement du rapport ExtentReports.html dans "Artefacts du build"
            archiveArtifacts(
                artifacts: 'src/test/resources/extentReports/ExtentReports.html, target/surefire-reports/emailable-report.html',
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
