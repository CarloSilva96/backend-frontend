FROM openjdk:11
WORKDIR /app
COPY target/backend-0.0.1-SNAPSHOT.jar backend.jar
EXPOSE 8080:8080
CMD ["java", "-jar", "./backend.jar"]

