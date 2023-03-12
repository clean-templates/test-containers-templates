FROM openjdk:19-jdk-slim
ADD  target/delivery-service-1.0-SNAPSHOT.jar delivery-service-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","delivery-service-1.0-SNAPSHOT.jar"]