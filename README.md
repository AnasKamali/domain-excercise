# domain-excercise<br/>

Milestone 1: Architecture Artifacts & Design<br/>
1.	Solution Architecture- <br/>
o	Logical Architecture, Physical Architecture, Business context diagram<br/>
[Design and Analysis Pdf](https://github.com/AnasKamali/domain-excercise/blob/main/Milestone1/Design%20and%20Analysis.pptx)<br/>
o	EDA Strategy and implementation with MS architecture<br/>
o	NFRS managed<br/>
[NFRS.pdf](https://github.com/AnasKamali/domain-excercise/blob/main/Milestone1/NFRS.pdf)<br/>
<br/>
2.	Sequence diagram: Features Implemented<br/>
[Sequence Diagram](https://github.com/AnasKamali/domain-excercise/blob/main/Milestone1/Sequential%20Diagrams.docx)<br/>
<br/>
3.	Design:<br/>
o	DDD & Bounded context<br/>
[Design and Analysis Pdf](https://github.com/AnasKamali/domain-excercise/blob/main/Milestone1/Design%20and%20Analysis.pptx)<br/>
o	Ready heavy – CQRS<br/>
o	DB selections (NOSQL + RDBMS), ER diagram<br/>
[ERDiagram and Scripts](https://github.com/AnasKamali/domain-excercise/blob/main/Milestone1/ER%20Diagram%20and%20Scripts.docx)<br/>
<br/>
<br/>
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
Milestone 3: Build Phase<br/>
1.	Microservices Ecosystem with Springboot <br/>
   following are used:-
 api-gateway, eureka-server, eureka-client, outh2-resource-server, security, spring-starter-test, spring-jpa etc<br/>
a.	Features Implemented<br/>
<br/>
b.	Springboot annotations<br/>
@restcontroller, @transaction, @component, @configuration, @enableEurekaClient, @enableEurekaServer, @Repository, @service
@SpringBootApplication etc
c.	Transactions<br/>
At service level use @Transaction notation for handling transaction with DB.<br/>
Will be rollbacked if any exception occured inside method.<br/>
d.	configurations<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
3.	API Security<br/>
A seprate branch security is build for security<br/>
a.	O-Auth2.0<br/>
api-gateway service endpoints are secured using oauth2 protocol with keyclock as auhthentication provider.<br/>
<br/>
[API-Gateway](https://github.com/AnasKamali/domain-excercise/tree/security/api-gateway)<br/>
discovery service is secure using basic auth mechanism with username and password<br/>
[discovry-service](https://github.com/AnasKamali/domain-excercise/tree/security/discovery-server)<br/>
<br/>
b.	API Gateway<br/>
      [discovery server](https://github.com/AnasKamali/domain-excercise/tree/main/discovery-server)<br/>
       [api-gateway service](https://github.com/AnasKamali/domain-excercise/tree/main/api-gateway)<br/>
4.	Logging & Tooling<br/>
Using zipkin for traceblity-[](https://github.com/AnasKamali/domain-excercise/blob/main/screenshots/zipkinServerRunning.png)<br/>
a.	Observability: Grafana, cloud monitoring<br/>
5.	Exception handling<br/>
[exception handler](https://github.com/AnasKamali/domain-excercise/tree/main/event-app/src/main/java/com/sapient/eventApp/exception/handler)
<br/>
<br/>
Milestone 4: Testing Phase<br/>
1.	Testing: <br/>
a.	Junit> Code Coverage >80%<br/>
[eventAppTestCases](https://github.com/AnasKamali/domain-excercise/tree/main/event-app/src/test/java/com/sapient/eventApp)<br/>
[attandanceAppTestCase](https://github.com/AnasKamali/domain-excercise/tree/main/attandance-app/src/test/java/com/sapient/attandanceApp)<br/>
b.	SIT (BDD: Cucumber or Karate)<br/>
c.	JMeter<br/>
3.	Build & Release pipeline: Set up pipeline & Reporting<br/>
4.	Entire solution deployable on cloud (Burner account)<br/>
      [deployment.yaml](https://github.com/AnasKamali/domain-excercise/blob/main/kubernetes/deployment.yaml)
