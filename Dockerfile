# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar


# Expose the application port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "user-service.jar"]