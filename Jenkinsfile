stage('Run App & Keep Alive') {
    bat '''
    echo ======================================
    echo Starting Spring Boot on port 9000
    echo ======================================

    rem Start app in background and capture PID
    start /b java -jar target\\JenkinsDemo-0.0.1-SNAPSHOT.jar --server.port=9000 > app.log 2>&1
    for /f "tokens=2 delims=," %%a in ('tasklist /fi "imagename eq java.exe" /fo csv /nh') do echo %%~a > app.pid

    echo Waiting for application to start...
    ping 127.0.0.1 -n 10 > nul

    echo ======================================
    echo Checking /print endpoint (retry loop)
    echo ======================================

    set TRY=0
    :RETRY
    curl http://localhost:9000/print && goto SUCCESS
    set /a TRY+=1
    if %%TRY%% GEQ 5 goto FAILED
    echo App not ready yet, retrying...
    ping 127.0.0.1 -n 5 > nul
    goto RETRY

    :FAILED
    echo WARNING: /print endpoint not reachable, continuing build

    :SUCCESS
    echo Endpoint check completed

    echo ======================================
    echo Keeping Tomcat alive for 120 seconds
    echo ======================================
    ping 127.0.0.1 -n 120 > nul

    echo ======================================
    echo Stopping Spring Boot safely
    echo ======================================
    for /f %%p in (app.pid) do taskkill /PID %%p /F

    echo Application stopped
    '''
}