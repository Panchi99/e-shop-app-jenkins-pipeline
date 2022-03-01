pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
    }

    stages {
        stage('Build Project') {
            steps {
                // Get some code from a GitHub repository
                
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'c0df9c79-c2d6-4f8a-9ad8-2c625cfcd09c', url: 'https://github.com/Panchi99/e-shop-app-jenkins-pipeline.git']]])

                // Run Maven on a Unix agent.
                bat "mvn clean package -DskipTests"
            }
        }
    
        stage('Docker Image Build'){
            steps{
            
                bat "docker build -t pancikrango/e-shop-app-image ."

            }
        }
        
        stage('Docker Image Push'){
            steps{
            withCredentials([string(credentialsId: '3613d505-50e4-4684-a1fc-02188155dfb2', variable: 'dockerhubcred')]) {
    
                bat "docker login -u pancikrango -p ${dockerhubcred}"
                
                bat "docker push pancikrango/e-shop-app-image"
                
            }
                

            }
        }
        
        stage("deploy to kubernetes"){
            steps{
                script{
                    kubernetesDeploy(configs: "deployment.yaml", kubeconfigId: "kubernetes")
                }
            }
        }
        
       stage('Deploying app to Kubernetes cluster'){
           steps{
               
                 bat "kubectl apply -f deployment.yaml"
            }
        }
        
    }
    
    
    
}
