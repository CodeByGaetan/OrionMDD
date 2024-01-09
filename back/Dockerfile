FROM maven:3.8.2-openjdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /target/mdd-api-0.0.1-SNAPSHOT.jar mdd-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","mdd-api.jar"]