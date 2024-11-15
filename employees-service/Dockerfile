FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/employees-service-0.0.1-SNAPSHOT.jar employees-service.jar
ENV EUREKA_URL null
ENV CONFIG_SERVER_HOST null
ENV DB_URL null
ENV DB_USERNAME null
ENV DB_PASSWORD null
ENTRYPOINT ["java","-jar","/employees-service.jar"]
