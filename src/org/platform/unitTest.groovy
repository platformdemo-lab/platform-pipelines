package org.platform

class unitTest {

    def script

    unitTest(script) {
        this.script = script
    }

    def run() {
        script.sh '''
        python3 -m venv venv
        venv/bin/pip install -r requirements.txt
        venv/bin/pytest || true
        '''
    }
}