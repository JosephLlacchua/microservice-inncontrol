FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/inventory-service-0.0.1-SNAPSHOT.jar inventory-service.jar
ENV EUREKA_URL null
ENV CONFIG_SERVER_HOST null
ENV DB_URL null
ENV DB_USERNAME null
ENV DB_PASSWORD null
ENTRYPOINT ["java","-jar","/inventory-service.jar"]
