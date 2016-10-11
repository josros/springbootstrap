FROM java:8

RUN mkdir /execute

ADD execute-service.sh /execute/execute-service.sh
ADD demo-service-one-0.0.1-SNAPSHOT.jar /execute/service.jar

WORKDIR /execute
RUN bash -c 'touch /execute/service.jar'
EXPOSE 8081

ENTRYPOINT ["sh", "/execute/execute-service.sh"]