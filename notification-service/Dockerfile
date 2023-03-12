FROM openjdk:19-jdk-slim
ADD  target/notification-service-1.0-SNAPSHOT.jar notification-service-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","notification-service-1.0-SNAPSHOT.jar"]