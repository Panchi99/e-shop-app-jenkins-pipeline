# e-shop-app-jenkins-pipeline

This pipeline is built using a Jenkins automation server.
As a base project, I am using an e-shop Spring boot web application bound with Postgresql as a database.

The pipeline consists of 5 steps:

 1. Project Build      -> Maven build phase for creating a new jar file containing all the previous changes.
 2. Docker Image Build -> phase for building new Docker Image based on the existing Dockerfile. 
 3. Docker Image Push  -> for pushing the last created Docker Image to the Docker Hub.
 4. Deploy to Kubernetes Cluster -> for pulling the Docker image from Docker Hub and deploying to the specified Kubernetes Cluster Provider.
 
The pipeline is triggered on every push to this repository (e-shop-app-jenkins-pipeline) using GitHub WebHooks.
