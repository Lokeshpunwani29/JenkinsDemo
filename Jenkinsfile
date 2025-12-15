node {
    checkout scm

    def mvnHome = tool name: 'Maven3', type: 'maven'
    def javaHome = tool name: 'Java17', type: 'jdk'
    env.PATH = "${javaHome}\\bin;${mvnHome}\\bin;${env.PATH}"

    stage('Hello') {
        echo 'Pipeline is executing'
    }

    stage('Java Version') {
        bat 'java -version'
    }

    stage('Maven Version') {
        bat 'mvn -version'
    }

    stage('Build') {
        bat 'mvn clean package'
    }

    stage('List Target') {
        bat 'dir target'
    }
}