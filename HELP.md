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

### Start app with monitoring

* kubectl create namespace monitoring
* helm repo add prometheus-community https://prometheus-community.github.io/helm-charts - добавить репозиторий для прометеуса
* helm repo add stable https://charts.helm.sh/stable
* helm repo update
* helm install prometheus prometheus-community/kube-prometheus-stack -f kustomize/monitoring/prometheus.yaml --atomic -n monitoring
   * helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx - если еще не добавлен - добавить
   * helm repo update
   * helm install nginx ingress-nginx/ingress-nginx -f kustomize/nginx-ingress.yaml --atomic -n monitoring
* kubectl port-forward service/prometheus-grafana 9000:80 n monitoring
* войти в графану - localhost:9000/ - admin/prom-operator
* kubectl port-forward service/prometheus-kube-prometheus-prometheus 9090 - зайдем в прометеус по localhost:9090/
* 



### More useful commands

* kubectl scale --replicas=0 deployment/nginx-ingress-nginx-controller-admission -n evg - опустить ресурс до нужного количества replicas=count 
* kubectl delete namespace evg
* kubectl port-forward --namespace evg svc/evgpostgresdb-postgresql 5432:5432 - проброс портов


### links 
 
*https://prometheus.io/docs/prometheus/latest/querying/basics/ - тут про promql


https://robustperception.io/how-does-a-prometheus-counter-work - про каунтеры почитать
https://www.youtube.com/watch?v=67Ulrq6DxwA  - видео одного из контрибьюторов prometheus
