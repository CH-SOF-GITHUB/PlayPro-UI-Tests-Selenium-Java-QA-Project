pipeline {
    agent any

    // 1. Déclaration des paramètres envoyés par Xray / Jira
    parameters {
        string(name: 'projectKey', defaultValue: '', description: 'Clé du projet Jira/Xray')
        string(name: 'testExecKey', defaultValue: '', description: 'Clé du Test Execution dans Xray')
    }

    tools {
        maven 'MAVEN-3.9.9'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/CH-SOF-GITHUB/PlayPro-UI-Tests-Selenium-Java-QA-Project.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean test-compile -DskipTests'
            }
        }

        stage('Run UI Tests') {
            steps {
                // Utilisation de catchError pour poursuivre le pipeline vers l'import Xray et les rapports même si des tests échouent
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat 'mvn test -DsuiteXmlFile=src/test/resources/xml/testng.xml'
                }
            }
        }

        stage('Import Results to Xray') {
            steps {
                script {
                    // Vérifie si le pipeline a été déclenché depuis Xray avec une clé de Test Execution
                    if (params.testExecKey != '') {
                        echo "Exportation des résultats TestNG vers Xray pour l'exécution : ${params.testExecKey}"

                        step([
                            $class: 'XrayImportBuilder',
                            serverInstance: 'Xray Cloud',
                            endpointName: '/testng',
                            importFilePath: 'target/surefire-reports/testng-results.xml',
                            importToSameExecution: 'true',
                            testExecKey: params.testExecKey,
                            projectKey: params.projectKey
                        ])
                    } else {
                        echo "Exécution standard (non déclenchée via Xray)."
                    }
                }
            }
        }

        stage('Reports') {
             steps {
                 publishHTML(target: [
                       allowMissing: true,
                       alwaysLinkToLastBuild: true,
                       keepAll: true,
                       reportDir: 'src/test/resources/extentReports',
                       reportFiles: 'ExtentReports.html',
                       reportName: 'Extent Spark Report',
                       reportTitles: 'Selenium Test Results'
                 ])
             }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/src/test/resources/extentReports/*.html', fingerprint: true
        }
        success {
            echo 'UI Automation Tests executed successfully!'
            emailext (
                to: 'chakerbensaid1@gmail.com',
                subject: "SUCCESSFUL BUILD: Job '${env.JOB_NAME}' [Build #${env.BUILD_NUMBER}]",
                body: """
                    <p>Hello,</p>
                    <p>The UI Automation Test pipeline completed successfully!</p>
                    <ul>
                        <li><b>Job Name:</b> ${env.JOB_NAME}</li>
                        <li><b>Build Number:</b> ${env.BUILD_NUMBER}</li>
                        <li><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></li>
                    </ul>
                    <p><b>Last Commit Details:</b></p>
                    <blockquote style="background-color: #f9f9f9; padding: 10px; border-left: 4px solid #4CAF50;">
                        \${CHANGES}
                    </blockquote>
                    <p><b>Extent Report:</b> <a href="${env.JOB_URL}Extent_20Spark_20Report/">CLICK HERE</a></p>
                    <p>Best Regards,<br>
                    <b>CHAKER BEN SAID - Automation Team</b></p>
                """,
                mimeType: 'text/html',
                attachLog: true
            )
        }
        failure {
            echo 'UI Automation Tests failed.'
        }
    }
}
