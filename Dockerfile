FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bundle-cargo.jar
ENTRYPOINT ["java","-jar","/bundle-cargo.jar"]
