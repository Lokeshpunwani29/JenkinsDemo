node {

    checkout scm

    def mvnHome = tool name: 'Maven3', type: 'maven'
    def javaHome = tool name: 'Java17', type: 'jdk'
    env.PATH = "${javaHome}\\bin;${mvnHome}\\bin;${env.PATH}"

    stage('Build') {
        bat 'mvn clean package'
    }

    stage('Run App for 60 seconds') {
        bat '''
        start "" java -jar target\\JenkinsDemo-0.0.1-SNAPSHOT.jar
        timeout /t 60
        taskkill /IM java.exe /F
        '''
    }
}