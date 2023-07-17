Milestone 2: Set up MVP<br/>
1.	CONFIGURATION<br/>
o	Configure Monitoring Dashboards - 7<br/>
eureka-discovery server is used for discovering service.
[discovery server](https://github.com/AnasKamali/domain-excercise/tree/main/discovery-server) <br/>
Distributed tracing is used using spring observablity and zipkin <br/>
Matrics and service status calculation is done by exposing actuator/prometheus endpoint to prometheus server. <br/>
For Distributed Monitoring Grafana dashboard is used with prometheus as datasource. <br/>
o	Use of Build Tools - 7 <br/>
maven is used for dependency management, build jar,test. <br/>
jenkins ci/cd pipeline are used for automation of build,test,containarization <br/>
spring-boot maven pluging for creating image. <br/>
docker repository for container image management. <br/>
docker-compose, minkube, kubectl, docker desktop for local deployment and testing. <br/>
<br/>
o	Deployment of services on Cloud managed AKS using CI/CD Pipelines (Desired) – Refer to the Bonus Marks section below<br/>
[deployment.yaml](https://github.com/AnasKamali/domain-excercise/blob/main/kubernetes/deployment.yaml)
<br/>
o	Kafka Set up<br/>
[kafkaimage](https://hub.docker.com/repository/docker/mak8docker/mykafka)<br/>
[zookeperimage](https://hub.docker.com/repository/docker/mak8docker/myzookeper)
<br/>
<br/>

