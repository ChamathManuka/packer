FROM openjdk:11
ARG JAR_FILE=/builds/backpackers/backpacker-backend/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
