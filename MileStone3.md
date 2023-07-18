Milestone 3: Build Phase<br/>
1.	Microservices Ecosystem with Springboot <br/>
   
a.	Features Implemented<br/>
following are used:-
 api-gateway, eureka-server, eureka-client, outh2-resource-server, security, spring-starter-test, spring-jpa etc<br/>
<br/>
b.	Springboot annotations<br/>
@restcontroller, @transaction, @component, @configuration, @enableEurekaClient, @enableEurekaServer, @Repository, @service
@SpringBootApplication etc
c.	Transactions<br/>
At service level use @Transaction notation for handling transaction with DB.<br/>
Will be rollbacked if any exception occured inside method.<br/>
d.	configurations<br/>
spring-config-server is used for configuration of services.[](https://github.com/AnasKamali/domain-excercise/tree/main/config-server) <br/>
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
Using zipkin for traceblity-![](https://github.com/AnasKamali/domain-excercise/blob/main/Documents/screenshots/zipkinServerRunning.png)<br/>
a.	Observability: Grafana, cloud monitoring<br/>
Prometheus Server Screenshot:
![Alt Text](https://github.com/AnasKamali/domain-excercise/blob/main/Documents/screenshots/prometheus.png)
Grafana Dashboard ScreenShot:
![Alt text](https://github.com/AnasKamali/domain-excercise/blob/main/Documents/screenshots/grafanaEventApp.png)
5.	Exception handling<br/>
[exception handler](https://github.com/AnasKamali/domain-excercise/tree/main/event-app/src/main/java/com/sapient/eventApp/exception/handler)
<br/>
<br/>
