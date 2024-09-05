# Stage 1: Build the Angular application
FROM node:18 AS angular-build

# Set the working directory for Angular build
WORKDIR /app

# Copy Angular project files
COPY stugbokningssystem-ui/package*.json ./
RUN npm install

# Copy the source files and build the Angular application
COPY stugbokningssystem-ui ./
RUN npm run build --aot

# Stage 2: Build the Spring Boot application with Maven
FROM maven:3.8.8-eclipse-temurin-17 AS backend-build

# Set the working directory for Maven build
WORKDIR /app

# Copy parent POM and backend POM files to download dependencies
COPY ./pom.xml ./pom.xml
COPY ./stugbokningssystem-bff/pom.xml ./stugbokningssystem-bff/

# Copy the entire project structure
COPY . .

# Download dependencies for the backend
RUN mvn dependency:go-offline -B

# Build the backend using Maven (including frontend assets)
RUN mvn clean package -DskipTests -X

# Stage 3: Prepare the runtime environment
FROM openjdk:17-jdk-slim AS runtime

# Install Nginx
RUN apt-get update && apt-get install -y nginx && rm -rf /var/lib/apt/lists/*

# Set the working directory for the application
WORKDIR /app

# Copy the built Spring Boot JAR from the Maven build stage
COPY --from=backend-build /app/stugbokningssystem-bff/target/stugbokningssystem-bff-0.0.1-SNAPSHOT.jar /app/app.jar

# Copy the built Angular files from the Angular build stage to the Nginx directory
COPY --from=angular-build /app/dist/stugbokningssystem-ui /usr/share/nginx/html

# Expose port 9090 for the Spring Boot application
EXPOSE 9090

# Expose port 80 for the Nginx server
EXPOSE 80

# Start the Spring Boot application and Nginx server
CMD ["sh", "-c", "java -jar /app/app.jar & nginx -g 'daemon off;'"]