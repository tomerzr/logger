pipeline {
    agent any
    tools {
        maven 'mvn3.6.3'
        jdk 'jdk9'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn clean'
                sh 'mvn compile'
            }
        }
        stage('UnitTest') {
            steps {
                echo 'UnitTesting..'
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                echo 'packaging....'
                sh 'mvn package -DskipTests'
            }
        }
        stage('IntegrationTest') {
            steps {
                echo 'verifying....'
                sh 'mvn verify -DskipTests'
            }
        }
        stage('CodeCoverage') {
            steps {
                echo 'CodeCoverage....'
            }
        }
        stage('StaticCodeAnalysis') {
            steps {
                echo 'StaticCodeAnalysis....'
            }
        }
        stage('UploadArtifact') {
            steps {
                echo 'UploadArtifact....'
            }
        }
    }
}