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
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run UI Tests') {
            steps {
                // 2. Utilisation de catchError pour poursuivre le pipeline en cas d'échec des tests
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat 'mvn test'
                }
            }
        }

        stage('Import Results to Xray') {
            steps {
                script {
                    // Vérifie si le pipeline a été déclenché depuis Xray avec une clé de Test Execution
                    if (params.testExecKey != '') {
                        echo "Export des résultats vers Xray pour l'exécution : ${params.testExecKey}"
                        // Exemple d'appel API Xray (Cloud) pour importer le rapport JUnit surefire
                        /*
                        withCredentials([string(credentialsId: 'xray-client-secret', variable: 'XRAY_CLIENT_SECRET')]) {
                            // Commande curl pour poster target/surefire-reports/*.xml vers Xray
                        }
                        */
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
