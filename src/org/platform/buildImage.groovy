package org.platform

class buildImage {

    def script

    buildImage(script) {
        this.script = script
    }

    def run(appName) {
        script.sh 'docker system prune -af || true'
        script.sh "DOCKER_BUILDKIT=0 docker build -t ${appName} ."
    }
}