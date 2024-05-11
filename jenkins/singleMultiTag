pipeline {
  agent any
  tools {
    maven 'mvn-3.9.6'
  }
  parameters {
    string(name: 'TagName', defaultValue: "@smoke", description: 'Multiple Tag--> @verify_search_bar_default_value,@verify_all_footer_text , Single Tag--> @smoke')
    choice(name: 'Headless', choices: ['false', 'true'], description: '')
    choice(name: 'Browser', choices: ['chrome', 'remote-chrome', 'firefox'], description: '')
    string(name: 'ProjectEnv', defaultValue: 'prod', description: '')
    string(name: 'ThreadCount', defaultValue: '1', description: '')
    string(name: 'Lang', defaultValue: 'tr', description: '')
    string(name: 'Branch', defaultValue: 'master', description: '')
            gitParameter name: 'BRANCH_TAG',
                  type: 'PT_BRANCH',
                  defaultValue: 'master',
                  selectedValue: 'DEFAULT',
                  quickFilterEnabled: true,
                  sortMode: 'DESCENDING_SMART',
                  tagFilter: '*',
                  branchFilter: 'origin/(.*)',
                  useRepository: '.*.git',
                  description: 'Select your branch'
  }
  stages {
    stage('Checkout') {
      steps {
        checkout([$class: 'GitSCM',
          branches: [
            [name: "${params.BRANCH_TAG}"]
          ],
          doGenerateSubmoduleConfigurations: false,
          extensions: [],
          gitTool: 'default',
          submoduleCfg: [],
          userRemoteConfigs: [
            [credentialsId: 'ozan-github', url: 'https://github.com/ozandonder/hp-web-test-automation.git']
          ]
        ])
      }
    }
    stage('Running Test') {
      steps {
          sh """
             mvn clean test -Dtest="CucumberRunnerTest" -D"cucumber.filter.tags=${TagName}" -DthreadCount=${ThreadCount} -Dbrowser=${Browser} -DprojectEnv=${ProjectEnv} -Dheadless=${Headless} -Dlang=${Lang}
             """
      }
      post {
        always {
          allure([
            includeProperties: false,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [
              [path: 'target/allure-results']
            ]
          ])
        }
      }
    }
  }
}