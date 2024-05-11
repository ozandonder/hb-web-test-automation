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
       post {
        always {
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])
            script {
                BUILD_DATA = sh(returnStdout: true, script: "echo \$(cat < $WORKSPACE/allure-report/history/history-trend.json | jq -r '.[] | select(.buildOrder==$BUILD_NUMBER)' )")
                TOTAL_SCENARIO = sh(returnStdout: true, script: "echo \$(echo '$BUILD_DATA' | jq -r .data.total)")
                TOTAL_PASSED = sh(returnStdout: true, script: "echo \$(echo '$BUILD_DATA' | jq -r .data.passed)")
                TOTAL_FAILED = sh(returnStdout: true, script: "echo \$(($TOTAL_SCENARIO - $TOTAL_PASSED))")
                SUCCESS_RATE = sh(returnStdout: true, script: "echo \$(( (($TOTAL_PASSED * 100) / $TOTAL_SCENARIO) + ( ($TOTAL_PASSED * 100) % $TOTAL_SCENARIO > 0 ) ))")
                }
        }
    }   
}
}
