/* Requires the Docker Pipeline plugin */
pipeline {
    agent any
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