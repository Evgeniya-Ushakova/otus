# Getting Started

### Build docker image

 * docker build -t otus .  
   * you can use also docker build  --platform linux/amd64 -t otus .  for build without errors in m1 proc 
 * docker tag otus:latest dockerHubAccountName/otus:07.07 

### Push docker image to docker hub
 
 * docker push  dockerHubAccountName/otus:07.07

### Run docker container

* docker run -d --name otus -p 8000:8000 dockerHubAccountName/otus:tag


### Start in kube

* minikube start (по дефолту стартует с драйвером докер) - запускает кластер
* kubectl apply -f kustomize/base - применить все манифесты разом (если наймспейс не ждефолтный добавить -n namespaceName)
* прописать а opt/etc/hosts ip мниникуба (minikube ip) - добавить днс arch.homework
   * (для mac m1 нужно использовать туннель) - minikube service otus - выведет урл по которому можно достуаться до сервиса
