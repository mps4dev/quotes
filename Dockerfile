FROM openjdk:8-jdk-alpine

MAINTAINER Marcin Szuppe "Marcin.Szuppe@clurgo.com"

EXPOSE 8070

VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
