FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user_app.jar
ENTRYPOINT ["java","-jar","/user_app.jar"]