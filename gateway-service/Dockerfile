FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar
ENV EUREKA_URL null
ENV CONFIG_SERVER_HOST null
ENTRYPOINT ["java","-jar","/gateway-service.jar"]