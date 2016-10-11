# Bootstrap architecture for spring boot applications

This is a **Blueprint** for spring boot applications. The [sample application](demo-service-one/Readme.md) demonstrates a strict but powerful 3-layer **Component Architecture** based on a simple **Hello World** example.

The project is fully integrated with **Docker**, due to the use of the *docker-maven-plugin*. It shows a possible interpretation of **RESTfulness** and it can easily be integrated with **Microservice** frameworks such as the [Spring Cloud Netflix](https://cloud.spring.io/spring-cloud-netflix/) stack.

Besides it contains configuration for static code analysis (checkstyle) and code formatting.

Furthermore it illustrates how to externalize common functionality into library projects and how they can be used within different applications.


# Executive Summary

## Prerequisites

1. Install docker
2. Install java 8
3. Install maven
4. Install and apply checkstyle (checkstyle.xml) (optional)
5. Apply formatter (formatter.xml) (optional)

## Execution

### Launch without docker

[see](demo-service-one/Readme.md)

### Launch with docker:

1. Build images with maven: `mvn clean package docker:build -DskipTests`
2. Launch the system using docker-compose: `docker-compose up -d`
3. Execute `docker ps` to check whether all containers are running
4. Now you can go to: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
