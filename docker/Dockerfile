FROM openjdk:8-jdk-alpine
COPY ./app/santatecla-definitions-4.0.jar /app/santatecla-definitions-4.0.jar
COPY ./app/waitDB.sh /app/waitDB.sh
COPY ./app/images /images
EXPOSE 8443
CMD ["/app/waitDB.sh"]