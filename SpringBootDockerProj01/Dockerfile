FROM eclipse-temurin:17-jdk-alpine
COPY target/*.jar /usr/app/app.jar
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","app.jar"]