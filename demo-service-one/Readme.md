# Demo application
This is a spring boot based application consisting of:

* A simple **hello world** CRUD application structured in 3-layers:
  * Persistence layer using JPA and Hibernate, entities are called <name>PE and the repository is meant to be used in the business layer
  * The business layer accesses the data, it produces and consumes immutable value objects (VO) only.
  * The REST layer uses business components and provides REST(ful) data endpoints to be used in arbitrary frontend components.

* Unit-tests for each layer
* Proposals of architectural design patters such as the builder pattern
* Different data source configurations to demonstrate an easy switch between databases
* Profiles to run different development modes in parallel
* Demonstration of how to use library components with an enable mechanism

In general, the purpose of this application is to demonstrate a strict but powerful 3-layer component architecture based on the spring boot stack.
It demonstrates concepts for everything a basic application needs. The strictness is given by the fact that the business layer must consume immutable objects. This concept does not allow to pass JPA entities through the business layer. Everything a business component consumes or produces must be immutable. This has various reasons, but mainly it forces the developer to keep the 3-layers separated. Things like combining different business components in a way that they change the same object are conceptionally avoided. Value objects are not meant to be shared between different business components either, even if they use the same data structure. Consequently applied, this architecture helps to avoid closely coupled components where there is absolutely no functional relation. 
The approach is aligned on the Entity-Control-Boundary pattern (ECB).

The code can be used as architectural blueprint for new projects based on spring boot. It is also a good starting point for the setup of microservices and it can easily be integrated with the spring-cloud-netflix stack.

## Executive summary

### Prerequisite
1. Install java 8
2. Install maven

### Execute locally

1. Run a maven build: `mvn --file ../pom.xml package`
2. Execute jar with dev profile `java -Dspring.profiles.active=dev -jar target/demo-service-one-0.0.1-SNAPSHOT.jar`

The dev profile causes the application to use an in-memory database. Furthermore, it uses port 8080 to avoid a conflict with the docker container.

3. Now you can go to: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

