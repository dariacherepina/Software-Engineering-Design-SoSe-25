# Use OpenJDK base image
FROM openjdk:17-slim

# Create app directory
WORKDIR /app

# Copy Java source code into the container
COPY . .

# Compile the Java file
RUN javac *.java

# Run the compiled Java class
CMD ["java", "UserServiceHandler"]
