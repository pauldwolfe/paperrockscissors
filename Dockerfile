#
# Build stage
#
FROM maven:3.6.3-openjdk-17 AS build
ENV APP_HOME=/home/app
WORKDIR $APP_HOME
COPY src $APP_HOME/src
COPY pom.xml $APP_HOME
RUN mvn -f $APP_HOME/pom.xml clean package

#
# Package stage
#
FROM openjdk:17-jdk-slim AS package
COPY $APP_HOME/target/paperrockscissors-1.0.0-SNAPSHOT-jar-with-dependencies.jar paper-rock-scissors.jar

#
# Run stage
#
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "paper-rock-scissors.jar"]