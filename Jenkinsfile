node {
    stage('Hello') {
        echo 'Pipeline is executing'
    }

    stage('Java Version') {
        bat 'java -version'
    }

    stage('Build') {
        bat 'mvn clean package'
    }

    stage('List Target') {
        bat 'dir target'
    }
}