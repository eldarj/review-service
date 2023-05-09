FROM eclipse-temurin:17-jdk-alpine

WORKDIR /service

ARG JAR_FILE_PATH
COPY ${JAR_FILE_PATH} review-service.jar
RUN echo "Hello $JAR_FILE_PATH"; ls -la /service

ENTRYPOINT ["java", "-jar", "/service/review-service.jar"]