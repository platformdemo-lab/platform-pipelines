def call() {
    pipeline {
        agent any

        stages {

            stage('Build') {
                steps {
                    sh 'pip install -r requirements.txt'
                }
            }

            stage('Docker Build') {
                steps {
                    sh 'docker build -t weather-app .'
                }
            }

            stage('Run Container') {
                steps {
                    sh 'docker rm -f weather-app || true'
                    sh 'docker run -d -p 5000:5000 --name weather-app weather-app'
                }
            }
        }
    }
}