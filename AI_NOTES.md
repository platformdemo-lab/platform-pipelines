# Platform Pipelines

Shared Jenkins library.

Main entry:
pythonService()

Stages:
- unitTest
- codeScan
- build
- containerScan
- deploy

Used by all services via Jenkinsfile.