FROM openjdk:8-jdk-alpine
COPY santatecla-definitions-0.0.1-SNAPSHOT.jar /usr/src/app/santatecla-definitions-0.0.1-SNAPSHOT.jar
COPY application.properties /usr/src/app/application.properties
EXPOSE 8443
CMD ["java", "-jar", "/usr/src/app/santatecla-definitions-0.0.1-SNAPSHOT.jar"]