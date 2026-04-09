import org.platform.unitTest
import org.platform.codeScan
import org.platform.buildImage
import org.platform.containerScan
import org.platform.deployService

def call(Map config = [:]) {

    def appName = config.appName ?: "app"
    def port = config.port ?: "5000"
    def envVars = config.envVars ?: [:]

    def runTests = config.runTests ?: false
    def runCodeScan = config.runCodeScan ?: false
    def runContainerScan = config.runContainerScan ?: false

    pipeline {
        agent any

        stages {

            stage('unitTest') {
                when { expression { runTests } }
                steps {
                    script {
                        new unitTest(this).run()
                    }
                }
            }

            stage('codeScan') {
                when { expression { runCodeScan } }
                steps {
                    script {
                        new codeScan(this).run()
                    }
                }
            }

            stage('build') {
                steps {
                    script {
                        new buildImage(this).run(appName)
                    }
                }
            }

            stage('containerScan') {
                when { expression { runContainerScan } }
                steps {
                    script {
                        new containerScan(this).run(appName)
                    }
                }
            }

            stage('deploy') {
                steps {
                    script {
                        new deployService(this).run(appName, port, envVars)
                    }
                }
            }
        }
    }
}