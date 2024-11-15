FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/accommodation-service-0.0.1-SNAPSHOT.jar accommodation-service.jar
ENV EUREKA_URL null
ENV CONFIG_SERVER_HOST null
ENV DB_URL null
ENV DB_USERNAME null
ENV DB_PASSWORD null
ENTRYPOINT ["java","-jar","/accommodation-service.jar"]
