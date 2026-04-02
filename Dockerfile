# Step 1: Build stage
FROM maven:3.9.6-eclipse-temurin-22-jammy AS build
WORKDIR /app
COPY . .
# We skip tests to avoid DB connection issues during build
RUN mvn clean package -DskipTests

# Step 2: Run stage
FROM eclipse-temurin:22-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
# Render uses the $PORT environment variable
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]