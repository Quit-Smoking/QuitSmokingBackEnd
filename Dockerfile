FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/quit-smoking-backend-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]