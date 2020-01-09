pipeline {
    agent any
	tools {
	jdk 'JAVA_8'
	maven 'MAVEN 3.6.3'
	}
    stages {
        stage ('Compile Stage') {

            steps {
                    sh 'mvn clean compile'
            }
        }

        stage ('Testing Stage') {

            steps {
                    sh 'mvn test'
            }
        }


        stage ('Deployment Stage') {
            steps {
                    sh 'mvn deploy'
            }
        }
    }
} 
