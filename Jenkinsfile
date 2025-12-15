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

        rem Kill any old Spring Boot process on port 9000
        for /f "tokens=5" %%a in ('netstat -ano ^| findstr :9000') do taskkill /PID %%a /F >nul 2>&1

        rem Start Spring Boot in background
        start /b java -jar target\\JenkinsDemo-0.0.1-SNAPSHOT.jar --server.port=9000 > app.log 2>&1

        echo Waiting for app to start...
        ping 127.0.0.1 -n 20 > nul

        echo ===============================
        echo Calling /print endpoint
        echo ===============================
        curl http://localhost:9000/print || echo WARNING: endpoint not reachable

        echo ===============================
        echo Keeping app alive for 120 seconds
        echo ===============================
        ping 127.0.0.1 -n 60 > nul

        echo ===============================
        echo Stopping Spring Boot
        echo ===============================
        for /f "tokens=5" %%a in ('netstat -ano ^| findstr :9000') do taskkill /PID %%a /F >nul 2>&1

        echo Application stopped
        '''
    }
}