FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} admin_app.jar
ENTRYPOINT ["java","-jar","/admin_app.jar"]