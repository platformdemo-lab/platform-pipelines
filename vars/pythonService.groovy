def call(Map config = [:]) {

    def appName = config.appName ?: "app"
    def port = config.port ?: "5000"
    def envVars = config.envVars ?: [:]

    pipeline {
        agent any

        stages {

            stage('Cleanup') {
                steps {
                    sh 'docker system prune -af || true'
                }
            }

            stage('Docker Build') {
                steps {
                    sh """
                    DOCKER_BUILDKIT=0 docker build -t ${appName} .
                    """
                }
            }

            stage('Run Container') {
                steps {
                    script {

                        // Convert env map to docker flags
                        def envString = envVars.collect { k, v -> "-e ${k}=${v}" }.join(" ")

                        sh """
                        docker rm -f ${appName} || true
                        docker run -d -p ${port}:${port} \
                          ${envString} \
                          --name ${appName} ${appName}
                        """
                    }
                }
            }
        }
    }
}