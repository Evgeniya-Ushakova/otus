# Getting Started

### Build docker image

 * docker build -t otus .  
 * docker tag otus:latest dockerHubAccountName/otus:07.07

### Push docker image to docker hub
 
 * docker push  dockerHubAccountName/otus:07.07

### Run docker container

* docker run -d --name otus -p 8000:8080 otus


