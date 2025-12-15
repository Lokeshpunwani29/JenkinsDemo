pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Jenkins pipeline is running'
            }
        }

        stage('Java Version') {
            steps {
                bat 'java -version'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('List Target') {
            steps {
                bat 'dir target'
            }
        }
    }
}