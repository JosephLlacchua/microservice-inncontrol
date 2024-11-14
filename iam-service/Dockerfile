FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/iam-service-0.0.1-SNAPSHOT.jar iam-service.jar
ENV EUREKA_URL null
ENV CONFIG_SERVER_HOST null
ENTRYPOINT ["java","-jar","/iam-service.jar"]