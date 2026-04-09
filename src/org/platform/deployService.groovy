package org.platform

class deployService {
    static def run(appName, port, envVars) {

        def envString = envVars.collect { k, v -> "-e ${k}=${v}" }.join(" ")

        sh """
        docker rm -f ${appName} || true
        docker run -d -p ${port}:${port} \
          ${envString} \
          --name ${appName} ${appName}
        """
    }
}