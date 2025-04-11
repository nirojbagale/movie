# Use official Maven image to build the application
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a smaller JDK image to run the app
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 5000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
