stage('Run App & Test Endpoint') {
    bat '''
    echo Starting Spring Boot app on port 9000...

    rem Start app and capture PID
    start "" cmd /c ^
    "java -jar target\\JenkinsDemo-0.0.1-SNAPSHOT.jar --server.port=9000 > app.log 2>&1 & echo %%! > app.pid"

    echo Waiting for application to start...
    timeout /t 25

    echo Calling API endpoint...
    curl -f http://localhost:9000/print || exit /b 1

    echo Keeping app alive for 20 seconds...
    timeout /t 20

    echo Stopping Spring Boot app safely...
    for /f %%p in (app.pid) do taskkill /PID %%p /F
    '''
}