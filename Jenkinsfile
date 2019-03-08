pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('QualityTest')
        {
        	agent {
              	docker {
               		image 'maven:3-alpine'
              }
            }
        	steps {
        	 sh'(mvn sonar:sonar -Dsonar.projectKey=com.crocode:CrowdCoding -Dsonar.organization=naralas-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=a4a83ea9cccc5f9269bc6f342d3ebccbceaefc92)'
        	}
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}