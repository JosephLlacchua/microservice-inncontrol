FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/eureka-service-0.0.1-SNAPSHOT.jar eureka-service.jar
ENV CONFIG_SERVER_HOST null
ENV EUREKA_URL null
ENTRYPOINT ["java","-jar","/eureka-service.jar"]