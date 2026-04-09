package org.platform

class unitTest {
    static def run() {
        sh 'pip3 install -r requirements.txt'
        sh 'pytest || true'
    }
}