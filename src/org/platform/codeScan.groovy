package org.platform

class codeScan {

    def script

    codeScan(script) {
        this.script = script
    }

    def run() {
        script.sh 'pip3 install bandit'
        script.sh 'bandit -r . || true'
    }
}