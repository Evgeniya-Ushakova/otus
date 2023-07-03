FROM openjdk:17-jdk-slim-buster

ARG APP_HOME=/app
WORKDIR $APP_HOME
COPY build/libs/otus.jar $APP_HOME/otus.jar

ENTRYPOINT exec java $JAVA_OPTS -jar ./otus.jar