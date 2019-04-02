import { Component, ViewChild } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { MatTableDataSource, MatDialog, MatPaginator } from "@angular/material";
import { OnInit } from "@angular/core";

import { QuestionsService } from "./question.service";
import { Question } from "./question.model";
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
    this.router.routeReuseStrategy.shouldReuseRoute = function() {
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
            .afterClosed()
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
    if (justificationId)
      this.questionsService
        .saveAnswer(
          this.id,
          questionType,
          answerText,
          questionText,
          answerId,
          justificationId
        )
        .subscribe(_ => this.addNewQuestion(questionText, questionType, answerText), error => console.log(error));
    else if (answerId)
      this.questionsService
        .saveAnswer(this.id, questionType, answerText, questionText, answerId)
        .subscribe(_ => this.addNewQuestion(questionText, questionType, answerText), error => console.log(error));
    else
      this.questionsService
        .saveAnswer(this.id, questionType, answerText, questionText, answerId)
        .subscribe(_ => this.addNewQuestion(questionText, questionType, answerText), error => console.log(error));
  }

  addNewQuestion(
    questionText: string,
    questionType: number,
    answerText: string
  ) {

  }
}
