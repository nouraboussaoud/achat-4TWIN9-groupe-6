FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/achat-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
