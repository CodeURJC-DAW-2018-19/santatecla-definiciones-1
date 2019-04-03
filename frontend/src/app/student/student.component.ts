import { Component, ViewChild } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { MatTableDataSource, MatDialog, MatPaginator } from "@angular/material";
import { OnInit } from "@angular/core";

import { QuestionsService } from "./question.service";
import { Question } from "./question.model";
import { Answer } from "../teacher/answer.model";
import { Justification } from "../teacher/justification.model";
import { Page } from "../page/page.model";
import { DiagramComponent } from "../diagram/diagram.component";

import { TdDialogService } from "@covalent/core";
import { YesNoDialogComponent } from "./yesNoDialog.component";

@Component({
  selector: "student",
  templateUrl: "./student.component.html",
  styleUrls: ["./student.component.css"]
})
export class StudentComponent {
  markedQuestions: Question[] = [];
  markedQuestionsPage: number;
  markedOnce: number;
  //-1 means not initialized, 0 means false, 1 means true
  //we need to use -1 so we don't get the alert first time we try to get them

  unmarkedQuestions: Question[] = [];
  unmarkedQuestionsPage: number;
  unmarkedOnce: number;
  //-1 means not initialized, 0 means false, 1 means true
  //we need to use -1 so we don't get the alert first time we try to get them

  id: number;

  displayedColumnsMarked: string[] = [
    "questionText",
    "userResponse",
    "correct"
  ];
  dataSourceMarked: MatTableDataSource<Question>;
  displayedColumnsUnmarked: string[] = ["questionText", "userResponse"];
  dataSourceUnmarked: MatTableDataSource<Question>;
  //@ViewChild(MatPaginator) markedPaginator: MatPaginator;
  //@ViewChild(MatPaginator) unmarkedPaginator: MatPaginator;

  constructor(
    private dialogs: MatDialog,
    private router: Router,
    activatedRoute: ActivatedRoute,
    private questionsService: QuestionsService,
    private dialogService: TdDialogService
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
    this.id = activatedRoute.snapshot.params["id"];
    this.markedQuestionsPage = 0;
    this.markedOnce = -1;
    this.unmarkedQuestionsPage = 0;
    this.unmarkedOnce = -1;
  }

  ngOnInit() {
    this.getMarkedQuestions();
    this.getUnmarkedQuestions();
  }

  getMarkedQuestions() {
    let once: number = this.markedOnce;
    if (once == -1 || once == 0) {
      let page: number = this.markedQuestionsPage++;
      this.questionsService.getMarkedQuestions(this.id, page).subscribe(
        (data: Page<Question>) => {
          if (data.numberOfElements === 0 && once == 0) {
            this.markedOnce = 1;
            this.dialogService.openAlert({
              message: "No hay más preguntas corregidas",
              title: "No hay más preguntas",
              closeButton: "Cerrar"
            });
          } else if (data.numberOfElements > 0) {
            if (once == -1) {
              this.markedOnce = 0;
            }
            this.markedQuestions = this.markedQuestions.concat(data.content);
            this.dataSourceMarked = new MatTableDataSource(
              this.markedQuestions
            );
          }
        },
        error => console.log(error)
      );
    }
  }

  getUnmarkedQuestions() {
    let once: number = this.unmarkedOnce;
    if (once == -1 || once == 0) {
      let page: number = this.unmarkedQuestionsPage++;
      this.questionsService.getUnmarkedQuestions(this.id, page).subscribe(
        (data: Page<Question>) => {
          if (data.numberOfElements === 0 && once == 0) {
            this.unmarkedOnce = 1;
            this.dialogService.openAlert({
              message: "No hay más preguntas por corregidas",
              title: "No hay más preguntas",
              closeButton: "Cerrar"
            });
          } else if (data.numberOfElements > 0) {
            if (once == -1) {
              this.unmarkedOnce = 0;
            }
            this.unmarkedQuestions = this.unmarkedQuestions.concat(
              data.content
            );
            this.dataSourceUnmarked = new MatTableDataSource(
              this.unmarkedQuestions
            );
          }
        },
        error => console.log(error)
      );
    }
  }

  showDiagram() {
    this.dialogs.open(DiagramComponent, {
      height: "600px",
      width: "800px"
    });
  }

  getNewQuestion() {
    this.questionsService.getNewQuestion(this.id).subscribe(
      data => {
        if (data.yesNoQuestion) {
          this.dialogs
            .open(YesNoDialogComponent, {
              width: "800px",
              data: {
                questionText: data.questionText
              }
            })
            .afterClosed() // TODO: if closed 'badly' don't save answer
            .subscribe((answer: string) => {
              if (data.justification)
                this.saveAnswer(
                  data.type,
                  answer,
                  data.questionText,
                  data.answer.id,
                  data.justification.id
                );
              else if (data.answer)
                this.saveAnswer(
                  data.type,
                  answer,
                  data.questionText,
                  data.answer.id
                );
              else this.saveAnswer(data.type, answer, data.questionText);
            });
        } else {
          this.dialogService
            .openPrompt({
              message: data.questionText,
              title: "Pregunta",
              cancelButton: "Cancelar",
              acceptButton: "Enviar"
            })
            .afterClosed()
            .subscribe((answer: string) => {
              if (answer) {
                if (data.justification)
                  this.saveAnswer(
                    data.type,
                    answer,
                    data.questionText,
                    data.answer.id,
                    data.justification.id
                  );
                else if (data.answer)
                  this.saveAnswer(
                    data.type,
                    answer,
                    data.questionText,
                    data.answer.id
                  );
                else this.saveAnswer(data.type, answer, data.questionText);
              }
            });
        }
      },
      error => console.log(error)
    );
  }

  saveAnswer(
    questionType: number,
    answerText: string,
    questionText: string,
    answerId?: number,
    justificationId?: number
  ) {
    if (justificationId != null)
      this.questionsService
        .saveAnswer(
          this.id,
          questionType,
          answerText,
          questionText,
          answerId,
          justificationId
        )
        .subscribe(
          data => this.addNewQuestion(questionText, answerText, questionType),
          error => console.log(error)
        );
    else if (answerId != null)
      this.questionsService
        .saveAnswer(this.id, questionType, answerText, questionText, answerId)
        .subscribe(
          data => this.addNewQuestion(questionText, answerText, questionType),
          error => console.log(error)
        );
    else
      this.questionsService
        .saveAnswer(this.id, questionType, answerText, questionText, answerId)
        .subscribe(
          data => this.addNewQuestion(questionText, answerText, questionType, data.correct),
          error => console.log(error)
        );
  }

  addNewQuestion(
    questionText: string,
    answerText: string,
    questionType: number,
    correct?: boolean
  ) {
    let question: Question;
    if (questionType == 2 || questionType == 3) {
      question = {
        questionText: questionText,
        type: questionType,
        userResponse: true,
        marked: false,
        yesNoQuestion: false,
        correct: false,
      }
      question["yesNoQuestion"] = true;
      question["correct"] = correct;
      this.markedQuestions.push(question);
      this.dataSourceMarked = new MatTableDataSource(
        this.markedQuestions
      );
    } else {
      if (questionType == 0) {
        let ans: Answer = {
          answerText: answerText,
          marked: false,
          correct: false,
        };
        question = {
          questionText: questionText,
          type: questionType,
          userResponse: true,
          marked: false,
          yesNoQuestion: false,
          correct: false,
          answer: ans
        };
      }
      else if (questionType == 1) {
        let jus: Justification = {
          justificationText: answerText,
          marked: false,
          valid: false,
          error: ''
        };
        question = {
          questionText: questionText,
          type: questionType,
          userResponse: true,
          marked: false,
          yesNoQuestion: false,
          correct: false,
          justification: jus
        };
      }
      this.unmarkedQuestions.push(question);
      this.dataSourceUnmarked = new MatTableDataSource(
        this.unmarkedQuestions
      );
    }
    //TODO: Add reload
  }
}
