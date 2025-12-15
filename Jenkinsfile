node {

    // 1️⃣ Checkout source code
    checkout scm

    // 2️⃣ Load Jenkins tools
    def mvnHome = tool name: 'Maven3', type: 'maven'
    def javaHome = tool name: 'Java17', type: 'jdk'
    env.PATH = "${javaHome}\\bin;${mvnHome}\\bin;${env.PATH}"

    stage('Verify Tools') {
        bat 'java -version'
        bat 'mvn -version'
    }

    stage('Build') {
        bat 'mvn clean package'
    }

    stage('Run App & Call API') {
        bat '''
        echo Starting Spring Boot application on port 9000...
        start "" java -jar target\\JenkinsDemo-0.0.1-SNAPSHOT.jar --server.port=9000

        echo Waiting for app to start...
        timeout /t 20

        echo Calling API endpoint...
        curl http://localhost:9000/print

        echo Keeping app running for some time...
        timeout /t 30

        echo Stopping application...
        taskkill /IM java.exe /F
        '''
    }
}