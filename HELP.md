# Getting Started

### Build docker image

 * docker build -t otus .  
   * you can use also docker build  --platform linux/amd64 -t otus .  for build without errors in m1 proc 
 * docker tag otus:latest dockerHubAccountName/otus:07.07 

### Push docker image to docker hub
 
 * docker push  dockerHubAccountName/otus:07.07

### Run docker container

* docker run -d --name otus -p 8000:8080 dockerHubAccountName/otus:tag


