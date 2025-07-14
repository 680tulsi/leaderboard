# Step 1: Build the JAR inside Docker
FROM gradle:8.4.0-jdk17 AS builder
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build -x test

# Step 2: Run the Spring Boot app
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/leaderboard-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
