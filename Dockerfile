# Start with a base image containing Java 17 runtime
FROM openjdk:17-jdk-slim

# Set the maintainer information for the image
MAINTAINER iritik

# Copy the compiled JAR file from the target directory to the container
COPY target/account-service-0.0.1-SNAPSHOT.jar account-service-0.0.1-SNAPSHOT.jar

# Configure the container to run the JAR file when it starts
ENTRYPOINT ["java", "-jar", "account-service-0.0.1-SNAPSHOT.jar"]