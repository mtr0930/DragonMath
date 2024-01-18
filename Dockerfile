FROM eclipse-temurin:17-jdk-alpine

WORKDIR /dragon-math

CMD ["./gradlew", "clean", "bootJar"]

COPY build/libs/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
