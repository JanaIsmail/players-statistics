FROM openjdk:17-slim
WORKDIR /app
COPY target/TennisPlayersStats-1.0-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]