FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/*.jar

COPY ./target/employee-management-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
