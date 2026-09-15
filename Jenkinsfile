pipeline {
    agent any

    tools {
        // Must match the Maven tool name configured in Jenkins global tool configuration
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
                bat 'mvn clean install'
            }
        }

        stage('Run UI Tests') {
            steps {
                bat 'mvn clean test'
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
