# Stage 1: Build the Angular application
FROM node:18 AS angular-build

WORKDIR /app

# Copy Angular project files
COPY ./stugbokningssystem-ui/package*.json ./stugbokningssystem-ui/
RUN cd stugbokningssystem-ui && npm install

# Copy all Angular source files and build the Angular project
COPY ./stugbokningssystem-ui ./stugbokningssystem-ui
RUN cd stugbokningssystem-ui && npm run build --prod

# Stage 2: Build the Spring Boot application with Maven
FROM maven:3.8.8-eclipse-temurin-17 AS backend-build

WORKDIR /app

# Copy parent and backend pom.xml files to download dependencies
COPY ./pom.xml ./pom.xml
COPY ./stugbokningssystem-bff/pom.xml ./stugbokningssystem-bff/

# Copy the entire project structure
COPY . .

# Download dependencies for the backend
RUN mvn dependency:go-offline -B

# Copy the Angular build output to the Spring Boot static directory
COPY --from=angular-build /app/stugbokningssystem-ui/dist /app/stugbokningssystem-bff/src/main/resources/static

# Build the backend using Maven (including frontend assets)
RUN mvn clean package -DskipTests

# Stage 3: Run the Spring Boot application
FROM openjdk:17-jdk-slim AS runtime

WORKDIR /app

# Copy the built Spring Boot JAR from the build stage
COPY --from=backend-build /app/stugbokningssystem-bff/target/stugbokningssystem-bff-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the default Spring Boot port
EXPOSE 9090

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]