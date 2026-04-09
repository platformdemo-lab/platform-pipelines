# Platform Pipelines

Shared Jenkins library for standard CI/CD workflows.

Provides a reusable pipeline for Python services with the following stages:
- unitTest
- codeScan
- build
- containerScan
- deploy

---

## Usage:

1. Configure as a Jenkins shared library:
   ```
   Name: platform-pipelines
   Branch: main
   ```

2. Use in Jenkinsfile:

    ```groovy
   @Library('platform-pipelines') _

   pythonService(
     appName: "service-name",
     port: "5000",
     runTests: true,
     runCodeScan: true,
     runContainerScan: true
   )
   ```

3. With secrets:

   ```groovy
   withCredentials([
     string(credentialsId: 'key-id', variable: 'API_KEY')
   ]) {
     pythonService(
       appName: "service-name",
       envVars: [
         "API_KEY": API_KEY
       ]
     )
   }
   ```

---

## Notes:
- Uses Docker for build and run
- Uses venv for test and scan
- Code scan runs only on `src/`

---