/* Requires the Docker Pipeline plugin */
pipeline {
    agent any
    tools { maven 'mvn-3.9.6' }
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('test') {
                    steps {
                        sh 'mvn clean test'
                    }
                }
    }
}
