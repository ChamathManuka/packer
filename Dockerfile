# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Create a volume for temporary files
VOLUME /tmp

# Copy the Spring Boot jar file into the container.
# The ARG allows you to specify which jar file to use (set via Maven).
ARG JAR_FILE=target/backpacker-0.0.1.jar
COPY ${JAR_FILE} packer-app.jar

# Expose the port your Spring Boot application listens on (e.g., 8090)
EXPOSE 8090

# Specify the command to run your application
ENTRYPOINT ["java", "-jar", "/packer-app.jar"]
