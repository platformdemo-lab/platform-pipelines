package org.platform

class containerScan {

    def script

    containerScan(script) {
        this.script = script
    }

    def run(appName) {
        script.sh "trivy image ${appName} || true"
    }
}