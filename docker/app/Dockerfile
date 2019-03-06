FROM openjdk:8-jdk-alpine
COPY santatecla-definitions-3.0.jar /app/santatecla-definitions-3.0.jar
COPY waitDB.sh /app/waitDB.sh
COPY images /images
EXPOSE 8443
CMD ["/app/waitDB.sh"]