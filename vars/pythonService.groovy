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
                        org.platform.unitTest.run()
                    }
                }
            }

            stage('codeScan') {
                when { expression { runCodeScan } }
                steps {
                    script {
                        org.platform.codeScan.run()
                    }
                }
            }

            stage('build') {
                steps {
                    script {
                        org.platform.buildImage.run(appName)
                    }
                }
            }

            stage('containerScan') {
                when { expression { runContainerScan } }
                steps {
                    script {
                        org.platform.containerScan.run(appName)
                    }
                }
            }

            stage('deploy') {
                steps {
                    script {
                        org.platform.deployService.run(appName, port, envVars)
                    }
                }
            }
        }
    }
}