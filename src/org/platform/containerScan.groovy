package org.platform

class containerScan {
    static def run(appName) {
        sh "trivy image ${appName} || true"
    }
}