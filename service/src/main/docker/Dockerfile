FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD solopay-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=arc","-Djava.net.preferIPv4Stack=true","-jar","/app.jar"]