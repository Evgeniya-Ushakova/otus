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

 * minikube start (по дефолту стартует с драйвером докер - если запущен докер десктоп) - запускает кластер
 * kubectl create namespace m
 * helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ 
 * helm repo update
 * helm install nginx ingress-nginx/ingress-nginx --namespace m -f kustomize/nginx-ingress.yaml 
  (создает неймспейс и в неа запускает ингресс контроллер хелмом)
 * kubectl apply -f kustomize/base -n m - применить все манифесты разом (если наймспейс не дефолтный добавить -n namespaceName)
 * прописать в opt/etc/hosts ip миникуба (minikube ip) - добавить днс arch.homework
   * (для mac m1 нужно использовать туннель) - minikube service otus - выведет урл по которому можно достучаться до сервиса


### More useful command

* kubectl scale --replicas=0 deployment/nginx-ingress-nginx-controller-admission -n m - опустить ресурс до нужного количества replicas=count 
* kubectl delete namespace m - удадить неймспейс

