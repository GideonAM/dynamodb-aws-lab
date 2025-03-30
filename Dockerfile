FROM openjdk:17

ARG JAR_FILE=tagert/*.jar

COPY ${JAR_FILE} todo.jar

ENTRYPOINT ["java", "-jar", "/todo.jar"]