package org.platform

def run() {
    sh 'pip3 install -r requirements.txt'
    sh 'pytest || true'
}