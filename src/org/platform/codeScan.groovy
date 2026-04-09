package org.platform

class codeScan {
    static def run() {
        sh 'pip3 install bandit'
        sh 'bandit -r . || true'
    }
}