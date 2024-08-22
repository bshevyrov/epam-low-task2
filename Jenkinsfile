pipeline {
    agent any

    tools {
        maven 'Maven 3.8.4'  // Ensure Maven is installed and configured
        jdk 'JDK 11'  // Ensure JDK is installed and configured
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone the repository
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Build the application using Maven
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Run tests and generate reports
                sh 'mvn test'
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('SonarQube Analysis') {
            environment {
                scannerHome = tool 'SonarScanner'  // Refer to the name you gave SonarScanner
            }
            steps {
                withSonarQubeEnv('SonarQube') {  // Refer to the name you gave the SonarQube server
                    sh "${scannerHome}/bin/sonar-scanner"
                }
            }
        }

        stage('Deploy') {
            steps {
                // Deploy to a server or container
                // Example: Deploy with Docker
//                 sh 'docker build -t my-spring-boot-app .'
//                 sh 'docker run -d -p 8080:8080 my-spring-boot-app'
            }
        }
    }

    post {
        success {
            // Notify of successful build or deployment
            echo 'Pipeline completed successfully!'
        }
        failure {
            // Notify of failed build or deployment
            echo 'Pipeline failed!'
        }
    }
}
