FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} todo.jar

ENTRYPOINT ["java", "-jar", "/todo.jar"]