FROM openjdk:17-jdk-slim AS build
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
COPY --from=build /build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
