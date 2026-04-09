package org.platform

class deployService {

    def script

    deployService(script) {
        this.script = script
    }

    def run(appName, port, envVars) {

        def envString = envVars.collect { k, v -> "-e ${k}=${v}" }.join(" ")

        script.sh """
        docker rm -f ${appName} || true
        docker run -d -p ${port}:${port} \
          ${envString} \
          --name ${appName} ${appName}
        """
    }
}