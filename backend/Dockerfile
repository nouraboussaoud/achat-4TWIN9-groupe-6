# Stage 1: Build the application
FROM maven:3.8.6-openjdk-11 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /app/target/achat-1.0.jar app.jar

# Configure ports and context path
EXPOSE 8089
ENV SERVER_SERVLET_CONTEXT_PATH=/SpringMVC

ENTRYPOINT ["java", "-jar", "app.jar"]