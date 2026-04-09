package org.platform

def run(appName) {
    sh 'docker system prune -af || true'
    sh "DOCKER_BUILDKIT=0 docker build -t ${appName} ."
}