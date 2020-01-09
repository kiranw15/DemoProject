pipeline {
    agent any
	tools {
	jdk 'JDK8'
	maven 'MAVEN 3_6_3'
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
