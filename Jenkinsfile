node {

    // Checkout source code (VERY IMPORTANT)
    checkout scm

    // Load Jenkins tools
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

    stage('Run App & Test Endpoint') {
        bat '''
        echo Starting Spring Boot app on port 9000...

        rem Start app in background and capture PID
        start "" cmd /c ^
        "java -jar target\\JenkinsDemo-0.0.1-SNAPSHOT.jar --server.port=9000 > app.log 2>&1 & echo %%! > app.pid"

        echo Waiting for app to start...
        timeout /t 30

        echo Calling API endpoint...
        curl -f http://localhost:9000/print || exit /b 1

        echo App response received successfully

        echo Keeping app alive for 20 seconds...
        timeout /t 20

        echo Stopping Spring Boot app safely...
        for /f %%p in (app.pid) do taskkill /PID %%p /F

        echo Application stopped
        '''
    }
}