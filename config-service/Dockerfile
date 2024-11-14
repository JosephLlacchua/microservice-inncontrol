FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/config-service-0.0.1-SNAPSHOT.jar config-service.jar
ENV EUREKA_URL null
ENTRYPOINT ["java","-jar","/config-service.jar"]