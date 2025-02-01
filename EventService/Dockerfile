# Use Gradle official image
FROM gradle:8.11.1-jdk AS builder

# Set working directory
WORKDIR /app

# Copy all project files to container
COPY . .

# Build the project
RUN gradle clean build

# Final runtime image
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app
COPY --from=builder /app/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9092
ENTRYPOINT ["java", "-jar", "app.jar"]