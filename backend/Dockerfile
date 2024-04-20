
FROM openjdk:17-jdk-slim
COPY /target/wilson-books-api-0.0.1-SNAPSHOT.jar wilson-books-api.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","wilson-books-api.jar"]
