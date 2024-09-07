# Use an official Maven image to build the project
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the working directory
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the entire project to the working directory
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use an official OpenJDK image to run the application
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/User-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
