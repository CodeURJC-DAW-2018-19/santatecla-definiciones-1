cd ..
docker run --rm --name maven -v ${PWD}:/usr/src -w /usr/src maven:3.6-jdk-8 mvn package
Copy-Item './target/santatecla-definitions-3.0.jar' './docker/app/santatecla-definitions-3.0.jar'
cd docker
docker build -t santatecla-definitions .