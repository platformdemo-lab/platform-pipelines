package org.platform

def run() {
    sh 'pip3 install bandit'
    sh 'bandit -r . || true'
}