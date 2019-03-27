#!/bin/sh
until nc -z -v -w30 db 3306
do
  echo "Waiting for database connection..."
  sleep 5
done
java -jar /app/santatecla-definitions-4.0.jar