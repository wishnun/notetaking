pipeline{
    tools {
          maven 'Maven3'
     }
     environment {
             registry = "508043323580.dkr.ecr.us-east-1.amazonaws.com/wishnun-docker-repo"
      }

     agent any
     stages {
        stage('Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/wishnun/notetaking']])
            }
        }

        stage ('Build') {
          steps {
            sh 'mvn clean install'
           }
         }

         stage('Building image') {
           steps{
             script {
               dockerImage = docker.build registry
             }
           }
         }

         stage("Push to ECR") {
            steps{
                script{
                    sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 508043323580.dkr.ecr.us-east-1.amazonaws.com'
                    sh 'docker push 508043323580.dkr.ecr.us-east-1.amazonaws.com/wishnun-docker-repo:latest'
                }
            }
         }

         stage("EKS deploy") {
            steps{
                script{
                     withKubeConfig([credentialsId: 'K8S', serverUrl: '']) {
                         sh ('kubectl apply -f  eks')
                      }
                }
            }
         }

     }

}