# santatecla-definiciones-1
## Authors

|Name|E-mail|Github|
|-----|-----|-----|
|Daniel Diz Molinero  |  d.diz.2016@alumnos.urjc.es	    | [danielDiz](https://github.com/danielDiz)|
|Iván Chicano Capelo | i.chicano.2016@alumnos.urjc.es  |	[ivchicano](https://github.com/ivchicano)   |
|Diego Jara López	    | d.jaral@alumnos.urjc.es	        | [DiegoJL97](https://github.com/DiegoJL97)   |
|David Muñoz Alonso	  | d.munoza.2016@alumnos.urjc.es	  | [ggeettaa](https://github.com/ggeettaa)    |
|Ramón Barrabés Parra	| r.barrabes.2016@alumnos.urjc.es |	[ram2701](https://github.com/ram2701)     |

## Trello 

*https://trello.com/b/W7MyhRsn/daw-grupo-5-santatecla-definiciones-1*

## Statement

*https://docs.google.com/document/d/10n4gIg0pwB3BZ_6u_VGCdrfOEw9FunZCnHMr7cPo4Ao/edit?usp=sharing*

## Screenshots

![Home general](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/previewGeneral.PNG)
*Here we can see the home preview of our website, we can remark the topics and concepts, this ones are linked with the next screenshot that is the student view*

![Concept student](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/insideConceptStudent.PNG)
*Now we get into the students view and we can see some questions about the topic and their answer respectively*

<img align="left" height="250" width="340" src="https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/modalQuestionOpen.PNG" />
<img align="left" height="250" width="340" src="https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/modalQuestionClose.PNG" />
<br />
Students can answer questions by pressing the button and a random one will be displayed

![Home teacher](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/previewTeacher.PNG)
*This is the teacher view of the home page. He can do the same as the student, and also add or delete concepts and chapters*

<img align="left" height="250" width="340" src="https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/modalNewConcept.PNG" />
<img align="left" height="250" width="340" src="https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/modalNewChapter.PNG" />
<br />
Each chapter has a button to add a concept and the page has one to add a chapter

![Delete notification](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/deleteNotice.PNG)
*It also notifies us everytime we try to delete something, no matter if it's a concept, chapter, justification nor an answer*

![Concept teacher 1](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/insideConcept1.PNG)
![Concept teacher 2](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/insideConcept2.PNG)
![Concept teacher 3](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/insideConcept3.PNG)
*The teacher view of the concept page is much different from the student one. He cannot answer questions as in the other view, but it corrects the answers and justifications, modify, add or delete them, as well as upload images to it*

<img align="left" height="400" width="400" src="https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/modalNewAnswer.PNG" />
<br />
Here is how it looks when you want to add a new answer

![Login](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/login.PNG)
![Register](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/register.PNG)
*To split the student and teacher views a login or register is needed*

## Diagram

![Diagram screenshot](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/Captura.PNG)

![Diagram show](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/modalDiagram.PNG)

## Development Environment

### Developing (Spring Boot backend)

It's recomended to use an IDE with Maven support at worst, and is better if it has support for Spring and/or Spring Boot applications.
We use the Spring Tool Suite (based on Eclipse). More info at: https://spring.io/tools
You can run it by running the main class (Application).
The application will run on port 8443, so if you're running it on localhost the url will be:
https://localhost:8443
Note that a Mysql database is needed, so you may have to edit the *application.properties* file on the *src/main/resources* directory properly.

### Developing (Angular frontend)

To run a development server with the Angular frontend, all you need to do is run the following command on your terminal (in the /frontend directory)
> npm start
This will run a server with the frontend on development mode. This makes it so the backend server to ask for the information has to be running in https://localhost:8443 (default URL of backend)

Note that, when first running it, you have to install the node.js dependencies
> npm install

### Building the application image with Docker

For this you will need to have Docker installed. More info at: https://docs.docker.com/install/
The easiest way to build the application is using the *create_image.ps1* Powershell script in the *docker* directory. This will run a docker container with maven which will generate the .jar file, compile the Angular frontend in production mode and build a docker image with the application.
If you have Maven installed you can also do:
> mvn package
or:
> mvn clean install
This will generate the .jar in the *target* directory.
Note that a mysql database will be needed.

## How to run the application with Docker Compose

You can run the application using Docker Compose. For that, you have to go to the *docker* directory in your terminal and run the following command:
> docker-compose up

Note that you can also move the *docker-compose.yml* file anywhere and run the command from there, or use:
> docker-compose -f PATH up

Where PATH is the path in your file system where the file *docker-compose.yml* is.
This will download all the needed images (mysql and app) from Docker Hub and run container with the database and the application.
The application will run on port 8080. The new Angular frontend runs on /new/ URL.

## Database Entities

<img src="https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/DiagramaEntidadesBBDDNuevo.png"/>


## Class Diagrams and Templates

<img src="https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/ClassDiagram.png"/>


## API Rest Documentation

The documentation and examples of the API Rest part are very long. They are in the [API](https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/API.md) file.

