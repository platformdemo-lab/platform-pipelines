package org.platform

class codeScan {

    def script

    codeScan(script) {
        this.script = script
    }

    def run() {
        script.sh '''
        python3 -m venv venv
        venv/bin/pip install bandit
        venv/bin/bandit -r . || true
        '''
    }
}