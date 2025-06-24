FROM openjdk:21-jdk-buster
WORKDIR /app

COPY target/movie-service-0.0.1-SNAPSHOT.jar /app/movie-service.jar
ENTRYPOINT ["java", "-jar", "movie-service.jar"]