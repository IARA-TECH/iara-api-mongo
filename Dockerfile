# Etapa 1: Construção
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Define o diretório de trabalho
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]