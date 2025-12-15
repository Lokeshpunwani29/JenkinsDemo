node {

    checkout scm

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

    stage('Run App & Keep Alive') {
        bat '''
        echo ===============================
        echo Starting Spring Boot on port 9000
        echo ===============================

        rem Start app in background and capture PID
        start "" cmd /c ^
        "java -jar target\\JenkinsDemo-0.0.1-SNAPSHOT.jar --server.port=9000 > app.log 2>&1 & echo %%! > app.pid"

        echo Waiting 30 seconds for app startup...
        timeout /t 30

        echo ===============================
        echo Calling /print endpoint (NON-BLOCKING)
        echo ===============================

        curl http://localhost:9000/print
        if errorlevel 1 (
            echo WARNING: /print endpoint not reachable, continuing build...
        ) else (
            echo /print endpoint responded successfully
        )

        echo ===============================
        echo Keeping Tomcat alive for 120 seconds
        echo ===============================
        timeout /t 120

        echo ===============================
        echo Stopping Spring Boot safely
        echo ===============================
        for /f %%p in (app.pid) do taskkill /PID %%p /F

        echo Application stopped
        '''
    }
}