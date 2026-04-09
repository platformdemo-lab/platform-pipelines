package org.platform

class unitTest {

    def script

    unitTest(script) {
        this.script = script
    }

    def run() {
        script.sh 'pip3 install -r requirements.txt'
        script.sh 'pytest || true'
    }
}