FROM eclipse-temurin:17-jdk-alpine
COPY target/springboot-rest-app.jar /usr/app/
WORKDIR /usr/app/
ENTRYPOINT ["java","-jar","springboot-rest-app.jar"]