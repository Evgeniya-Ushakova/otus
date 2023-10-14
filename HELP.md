# Getting Started

### Build docker image

 * docker build -t otus .  
   * you can use also docker build  --platform linux/amd64 -t otus .  for build without errors in m1 proc 
 * docker tag otus:latest dockerHubAccountName/otus:02.10amd64

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
   * (для mac m1 нужно использовать туннель) - minikube service otus - выведет урл по которому можно достучаться до сервиса - его тоже можно прописать в хостс

### Start app in kube with postgresql

* все команды в инструкции расчитаны на выполнение из корневой директории проекта /otus в неймспейсе evg
* kubectl create namespace n - создание неймспейса
*  установка ингресс контроллера, если его еще нет:
   * helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/
   * helm repo update
   * helm install nginx ingress-nginx/ingress-nginx --namespace evg -f kustomize/nginx-ingress.yaml

* helm install evgpostgresdb -f kustomize/postgre-values.yaml oci://registry-1.docker.io/bitnamicharts/postgresql -n evg
* kubectl apply -f kustomize/base/initDb.yaml -f kustomize/base/secret.yaml -f kustomize/base/deployment.yaml -f  kustomize/base/service.yaml -f  kustomize/base/ingress.yaml -n evg 


### Start with traefik

* Все команды в инструкции расчитаны на выполнение из корневой директории проекта /otus в неймспейсе evg

* kubectl create namespace evg
* helm install evgpostgresdb -f kustomize/postgre-values.yaml oci://registry-1.docker.io/bitnamicharts/postgresql -n evg
* kubectl apply -f kustomize/base/initDb.yaml -f kustomize/base/secret.yaml -f kustomize/base/deployment.yaml -f  kustomize/base/service.yaml  -n evg
* kubectl apply -f kustomize/auth-service/secret.yaml -f kustomize/auth-service/deployment.yaml -f  kustomize/auth-service/service.yaml  -n evg
* helm repo add traefik https://traefik.github.io/charts
* helm repo update
* helm install traefik traefik/traefik -f apigw/traefik/traefik.yaml -n evg
* kubectl apply -f apigw/traefik/routes.yaml -f apigw/traefik/auth.yaml  -n evg
* minikube service -n evg traefik 

### Start with ambassador

* helm repo add datawire https://www.getambassador.io
* helm repo update
* kubectl apply -f https://app.getambassador.io/yaml/edge-stack/3.5.1/aes-crds.yaml -n emissary-system
* kubectl wait --timeout=90s --for=condition=available deployment emissary-apiext -n emissary-system
* helm install edge-stack --namespace evg datawire/edge-stack --set emissary-ingress.createDefaultListeners=true  \
   --set emissary-ingress.agent.cloudConnectToken=<cloudToken>
* kubectl apply -f apigw/ambassador/routes.yaml -n evg
* kubectl apply -f apigw/ambassador/auth.yaml -n evg

### More useful commands

* kubectl scale --replicas=0 deployment/nginx-ingress-nginx-controller-admission -n evg - опустить ресурс до нужного количества replicas=count 
* kubectl delete namespace evg
* kubectl port-forward --namespace evg svc/evgpostgresdb-postgresql 5432:5432 - проброс портов

https://github.com/izhigalko/otus-demo-apigw/blob/master/apigw/traefik/routes.yaml

kubectl apply -f kustomize/auth-service/deployment.yaml -n evg
kubectl apply -f kustomize/base/deployment.yaml -n evg
kubectl apply -f apigw/traefik/routes.yaml -f apigw/traefik/auth.yaml  -n evg



