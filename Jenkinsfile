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
        post {
        always {
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])
        }
    } 
}  
    }
}
