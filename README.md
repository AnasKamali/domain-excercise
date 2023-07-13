# domain-excercise<br/>

Milestone 1: Architecture Artifacts & Design<br/>
1.	Solution Architecture- <br/>
o	Logical Architecture, Physical Architecture, Business context diagram<br/>
[Design and Analysis Pdf](https://github.com/AnasKamali/domain-excercise/blob/main/Milestone1/Design%20and%20Analysis.pptx)<br/>
o	EDA Strategy and implementation with MS architecture<br/>
o	NFRS managed<br/>
[NFRS.pdf](https://github.com/AnasKamali/domain-excercise/blob/main/Milestone1/NFRS.pdf)<br/>
2.	Sequence diagram: Features Implemented<br/>
3.	Design:<br/>
o	DDD & Bounded context<br/>
[Design and Analysis Pdf](https://github.com/AnasKamali/domain-excercise/blob/main/Milestone1/Design%20and%20Analysis.pptx)<br/>
o	Ready heavy – CQRS<br/>
o	DB selections (NOSQL + RDBMS), ER diagram<br/>
<br/>
Milestone 2: Set up MVP<br/>
1.	CONFIGURATION<br/>
o	Configure Monitoring Dashboards - 7<br/>
[discovery server](https://github.com/AnasKamali/domain-excercise/tree/main/discovery-server)<br/>
o	Use of Build Tools - 7<br/>
o	Deployment of services on Cloud managed AKS using CI/CD Pipelines (Desired) – Refer to the Bonus Marks section below<br/>
o	Kafka Set up<br/>
[kafkaimage](https://hub.docker.com/repository/docker/mak8docker/mykafka)<br/>
[zookeperimage](https://hub.docker.com/repository/docker/mak8docker/myzookeper)
<br/>
Milestone 3: Build Phase<br/>
1.	Microservices Ecosystem with Springboot <br/>
a.	Features Implemented<br/>
b.	Springboot annotations<br/>
c.	Transactions<br/>
d.	configurations<br/>
2.	API Security<br/>
a.	O-Auth2.0<br/>
b.	API Gateway<br/>
      [discovery server](https://github.com/AnasKamali/domain-excercise/tree/main/discovery-server)<br/>
       [api-gateway service](https://github.com/AnasKamali/domain-excercise/tree/main/api-gateway)<br/>
3.	Logging & Tooling<br/>
Using zipkin for traceblity-[](https://github.com/AnasKamali/domain-excercise/blob/main/screenshots/zipkinServerRunning.png)<br/>
a.	Observability: Grafana, cloud monitoring<br/>
4.	Exception handling<br/>
[exception handler](https://github.com/AnasKamali/domain-excercise/tree/main/event-app/src/main/java/com/sapient/eventApp/exception/handler)
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
