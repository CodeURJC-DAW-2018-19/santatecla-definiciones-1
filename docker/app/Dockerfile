FROM openjdk:8-jdk-alpine
COPY santatecla-definitions-0.0.1-SNAPSHOT.jar /app/santatecla-definitions-0.0.1-SNAPSHOT.jar
COPY waitDB.sh /app/waitDB.sh
EXPOSE 8443
CMD ["/app/waitDB.sh"]