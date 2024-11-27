# OpenJDK Alpine 베이스 이미지
FROM openjdk:17-alpine

# 환경 변수 설정
ENV CHROME_BIN=/usr/bin/chromium-browser \
    CHROMEDRIVER_BIN=/usr/bin/chromedriver

# 필요한 패키지 설치
RUN apk add --no-cache \
    chromium \
    chromium-chromedriver \
    font-noto \
    bash \
    libc6-compat \
    && rm -rf /var/cache/apk/*

WORKDIR /app

COPY target/quit-smoking-backend-1.0.0.jar app.jar

EXPOSE 8080

# 어느 환경인지, local이라면 dev, server 환경이라면 prod를 입력. 예) docker run -e SPRING_PROFILES_ACTIVE=dev
ENTRYPOINT ["java", "-jar", "app.jar"]

