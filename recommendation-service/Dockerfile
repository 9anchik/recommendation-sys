FROM openjdk:21-jdk-buster
WORKDIR /app

COPY target/recommendation-service-0.0.1-SNAPSHOT.jar /app/recommendation-service.jar
ENTRYPOINT ["java", "-jar", "recommendation-service.jar"]
