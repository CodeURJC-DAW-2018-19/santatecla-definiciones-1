cd ../frontend
docker run --rm --name angular-cli -v ${PWD}:/angular -w /angular node:8.15.1 /bin/bash -c "npm install; npm rebuild; ng build --prod --baseHref=/new/"
Remove-Item -path '..\backend\src\main\resources\static\new\*'
Move-Item -Path './dist/angular/*' -Destination '..\backend\src\main\resources\static\new'
cd ../backend
docker run --rm --name maven -v ${PWD}:/usr/src -w /usr/src maven:3.6-jdk-8 mvn clean install
Remove-Item -path '../docker/app/santatecla-definitions-4.0.jar'
Move-Item -Path './target/santatecla-definitions-4.0.jar' -Destination '../docker/app/santatecla-definitions-4.0.jar'
cd ../docker
docker build -t ivchicano/santatecla-definitions:4 .