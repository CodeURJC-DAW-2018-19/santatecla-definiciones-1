# santatecla-definiciones-1
## Authors

|Name|E-mail|Github|
|-----|-----|-----|
|Daniel Diz Molinero  |  d.diz.2016@alumnos.urjc.es	    | [LordHansTopo](https://github.com/LordHansTopo)|
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

### Developing

It's recomended to use an IDE with Maven support at worst, and is better if it has support for Spring and/or Spring Boot applications.
We use the Spring Tool Suite (based on Eclipse). More info at: https://spring.io/tools
You can run it by running the main class (Application).
The application will run on port 8443, so if you're running it on localhost the url will be:
https://localhost:8443
Note that a Mysql database is needed, so you may have to edit the *application.properties* file on the *src/main/resources* directory properly.


### Building the application image with Docker

For this you will need to have Docker installed. More info at: https://docs.docker.com/install/
The easiest way to build the application is using the *create_image.ps1* Powershell script in the *docker* directory. This will run a docker container with maven which will generate the .jar file and build a docker image with the application.
If you have Maven installed you can also do:
> mvn package

This will generate the .jar in the *target* directory.
Note that a mysql database will be needed.

## How to run the application with Docker Compose

You can run the application using Docker Compose. For that, you have to go to the *docker* directory in your terminal and run the following command:
> docker-compose up

Note that you can also move the *docker-compose.yml* file anywhere and run the command from there, or use:
> docker-compose -f PATH up

Where PATH is the path in your file system where the file *docker-compose.yml* is.
This will download all the needed images (mysql and app) from Docker Hub and run container with the database and the application.
The application will run on port 8080.

## Database Entities

<img src="https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/DiagramaEntidadesBBDDNuevo.png"/>


## Class Diagrams and Templates

<img src="https://github.com/CodeURJC-DAW-2018-19/santatecla-definiciones-1/blob/master/img/ClassDiagram.png"/>


# API Rest
We divided the urls in different categories depending on the elements involved in them. Some urls are only accesible from the student view while others from the teacher. All of them are preceded by [/api/](https://localhost:8080/api/).

## Chapters
The standard url is: [/chapters/](https://localhost:8080/api/chapters/).
> **Note:** There is an extra url for the home: ["/"](https://localhost:8080/api/). 

It supports GET, POST and DELETE operations.

The input information is usually a chapter, except for the GET operation where a Pageable attribute is the input. 
The ouput is a ResponseEntity of the chapter, GET operation is an exception too, it returns a Page of chapters.

## Concepts
The standard url is: [/chapters/{id}/concepts/](https://localhost:8080/api/chapters/5/concepts/).
> **Note:** id is the chapter identifier.

It supports GET, POST, DELETE and PUT operations.

The input information is usually a concept and the chapter id, except for the GET operation where a Pageable attribute is the input and not the concept. 
The ouput is a ResponseEntity of the concept, GET operation is an exception too, it returns a Page of concepts.

## Answers

The standard url is: [/concepts/{conceptId}/answers/{id}/](https://localhost:8080/api/concepts/15/answers/28).
> **Note:** conceptId is the concept identifier and id is the answer identifier .
> 
It supports GET, POST, DELETE and PUT operations. There are two GET operations, for marked and unmarked answers.
- Marked answers: [/concepts/{conceptId}/](https://localhost:8080api//concepts/15/) or  [/concepts/{conceptId}/markedanswers/](https://localhost:8080/api/concepts/15/markedanswers).
- Unmarked answers: [/concepts/{conceptId}/unmarkedanswers/](https://localhost:8080/concepts/15/unmarkedanswers.

> **Note:** There is an extra PUT operation: [/concepts/{conceptId}/correct/{answerId}/](https://localhost:8080/api/concepts/15/correct/).
> 
The input information is usually the concept id and the answer id. PUT and POST also get an answer, and the GET methods get the concept id and a Pageable attribute.
> **Note:** The  extra PUT operation gets the ids together with a correct and justificationText parameters.
> 
The ouput is a ResponseEntity of the answer, GET operation is an exception too, it returns a Page of answers, marked or unmarked accordingly.

## Justifications

The standard url is: [/answer/{ansId}/justifications/](https://localhost:8080/api/answer/15/justifications).
> **Note:** ansId is the answer identifier.
> 
It supports POST, DELETE and PUT operations.
> **Note:** There are no GET operations as the way to get the justifications if through the GETs of answer.
> **Note:** There is an extra PUT operation: [/answer/{ansId}/correct/{justId}/](https://localhost:8080/api/concepts/15/correct/).
> 
The input information is usually the answer id and a justification. PUT gets also the justification id.
> **Note:** The  extra PUT operation gets the ids together with a valid and errorText parameters.
> 
The ouput is a ResponseEntity of the justification.

## Question
The standard url is: [/concepts/{id}/](https://localhost:8080/api/concepts/15/).
> **Note:** id is the concept identifier.

It supports GET and POST operations.
- Marked questions: [/concepts/{conceptId}/markedquestions/](https://localhost:8080/api/concepts/15/markedquestions).
- Unmarked questions: [/concepts/{conceptId}/unmarkedquestions/](https://localhost:8080/api/concepts/15/unmarkedquestions).

Their input information is the concept id and a Pageable attribute. The output is a page of questions.
- Generate question: [/concepts/{conceptId}/newquestion/](https://localhost:8080/api/concepts/15/newquestion).

The input is just the concept id and the output is a question.
- Save answer: [/concepts/{conceptId}/saveanswer/](https://localhost:8080/api/concepts/15/newquestion).

The input information is more than usual, question text, question type, answer text, answer option, answer id and question id, together with the concept id. The output is a ResponseEntity of an answer.

## Diagram
The only url is: [/diagraminfo/](https://localhost:8080/api/diagraminfo/).

It only supports GET operations.

The input information is just a Pageable attribute. 
The ouput is a diagram and if the user is logged, their diagram.

## Image
The only url is: [/concepts/{id}/image/](https://localhost:8080/api/concepts/15/image/).
> **Note:** id is the concept identifier.

It supports GET and POST operations.

The input information is just the concept id, the POST operation also need a MultipartFile. 
The ouput is a ResposeEntity of an array of bytes.

## User
There is no standard url. There are two urls. 
- Register: [/register/](https://localhost:8080/api/register).
It input information is a user and output is a user too.
- User: [/currentuser/](https://localhost:8080/api/currentuser/).
There is no input information while output is a user.