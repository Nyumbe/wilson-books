
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app/backend

COPY backend/pom.xml .

RUN mvn dependency:go-offline

COPY backend/src ./src

RUN mvn package

FROM openjdk:17-jdk-slim

WORKDIR /app/backend

COPY --from=build /app/backend/target/backend-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]

