FROM openjdk:19-jdk-slim
ADD  target/order-service-1.0-SNAPSHOT.jar order-service-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","order-service-1.0-SNAPSHOT.jar"]