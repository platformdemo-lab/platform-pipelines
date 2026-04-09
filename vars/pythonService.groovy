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
                        unitTest.run()
                    }
                }
            }

            stage('codeScan') {
                when { expression { runCodeScan } }
                steps {
                    script {
                        codeScan.run()
                    }
                }
            }

            stage('build') {
                steps {
                    script {
                        buildImage.run(appName)
                    }
                }
            }

            stage('containerScan') {
                when { expression { runContainerScan } }
                steps {
                    script {
                        containerScan.run(appName)
                    }
                }
            }

            stage('deploy') {
                steps {
                    script {
                        deployService.run(appName, port, envVars)
                    }
                }
            }
        }
    }
}