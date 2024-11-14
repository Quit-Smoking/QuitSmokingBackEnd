FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/quit-smoking-backend-1.0.0.jar app.jar

EXPOSE 8080

# 어느 환경인지, local이라면 dev, server 환경이라면 prod를 입력. 예) docker run -e SPRING_PROFILES_ACTIVE=dev
ENTRYPOINT ["java", "-jar", "app.jar"]