import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";

import { AnswerService } from "./answer.service";
import { Answer } from "./answer.model";
import { JustificationService } from "./justification.service";
import { Justification } from "./justification.model";
import { Page } from "../page/page.model";
import { DiagramComponent } from "../diagram/diagram.component";
import { NewAnswerComponent } from "./newanswer.component";
import { MatDialog } from "@angular/material";
import { TdDialogService } from '@covalent/core';
import { NewJustComponent } from './newjust.component';

/**
 * Wrapper component for all teacher information.
 */
@Component({
  selector: "teacher",
  templateUrl: "./teacher.component.html",
  styleUrls: ["./teacher.component.css"]
})
export class TeacherComponent {
  markedAnswers: Answer[] = [];
  markedAnswersPage: number;
  markedOnce: number;
  //-1 means not initialized, 0 means false, 1 means true
  //we need to use -1 so we don't get the alert first time we try to get them

  unmarkedAnswers: Answer[] = [];
  unmarkedAnswersPage: number;
  unmarkedOnce: number;
  //-1 means not initialized, 0 means false, 1 means true

  unmarkedJust: Justification[] = [];
  unmarkedJustPage: number;
  unmarkedJustOnce: number;
  //-1 means not initialized, 0 means false, 1 means true

  markedJust: Map<number, Justification[]> = new Map() // key is answer id
  markedJustPage: Map<number, number> = new Map(); // key is answer id
  markedJustOnce: Map<number, number> = new Map(); // key: answer id   value:  -1 means not initialized, 0 means false, 1 means true

  answerPage: Page<Answer>;
  justPages: Map<number, Page<Justification>>; // key: answer id     value: justification page per answer
  id: number; //concept id

  constructor(
    private answerDialog: MatDialog,
    private diagramDialog: MatDialog,
    private router: Router,
    activatedRoute: ActivatedRoute,
    private answerService: AnswerService,
    private justificationService: JustificationService,
    private dialogService: TdDialogService
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
    this.id = activatedRoute.snapshot.params["id"];
    this.markedAnswersPage = 0;
    this.markedOnce = -1;
    this.unmarkedAnswersPage = 0;
    this.unmarkedOnce = -1;
    this.unmarkedJustPage = 0;
    this.unmarkedJustOnce = -1;
  }

  ngOnInit() {
    this.getMarkedAnswers();
    this.getUnmarkedAnswers();
    this.getUnmarkedJustifications();
  }

  getMarkedAnswers() {
    let once: number = this.markedOnce;
    if ((once == -1) || (once == 0)) {
      let page: number = this.markedAnswersPage++;
      this.answerService
        .getMarkedAnswers(this.id, page)
        .subscribe(
          (data: Page<Answer>) => {
            if ((data.numberOfElements === 0) && (once == 0)) {
              this.markedOnce = 1;
              this.dialogService.openAlert({
                message: 'No hay más respuestas corregidas',
                title: 'No hay más respuestas',
                closeButton: 'Cerrar'
              });
            } else if (data.numberOfElements > 0) {
              if (once == -1) {
                this.markedOnce = 0;
              }
              this.markedAnswers = this.markedAnswers.concat(data.content);
              data.content.forEach(answer => {
                if (!answer.correct) {
                  let id = answer.id;
                  if (!this.markedJustOnce.has(id)) {
                    this.markedJustOnce.set(id, -1);
                    this.markedJustPage.set(id, -1); //starts in -1 bc its increased after
                  }
                  this.getMarkedJustificationsByAnswer(id); 
                }
              });
            }
          },
          error => console.log(error + 'markedanswers')
        );
    }
  }


  getUnmarkedAnswers() {
    let once: number = this.unmarkedOnce;
    if ((once == -1) || (once == 0)) {
      let page: number = this.unmarkedAnswersPage++;
      this.answerService
        .getUnmarkedAnswers(this.id, page)
        .subscribe(
          (data: Page<Answer>) => {
            if ((data.numberOfElements === 0) && (once == 0)) {
              this.unmarkedOnce = 1;
              this.dialogService.openAlert({
                message: 'No hay más respuestas por corregidas',
                title: 'No hay más respuestas',
                closeButton: 'Cerrar'
              });
            } else if (data.numberOfElements > 0) {
              if (once == -1) {
                this.unmarkedOnce = 0;
              }
              this.unmarkedAnswers = this.unmarkedAnswers.concat(data.content);
            }
          },
          error => console.log(error + 'unmarkedanswers')
        );
    }
  }

  getUnmarkedJustifications() {
    let once: number = this.unmarkedJustOnce;
    if ((once == -1) || (once == 0)) {
      let page: number = this.unmarkedJustPage++;
      this.justificationService
        .getUnmarkedJustifications(this.id, page)
        .subscribe(
          (data: Page<Justification>) => {
            if ((data.numberOfElements === 0) && (once == 0)) {
              this.unmarkedJustOnce = 1;
              this.dialogService.openAlert({
                message: 'No hay más justificaciones por corregir',
                title: 'No hay más justificaciones',
                closeButton: 'Cerrar'
              });
            } else if (data.numberOfElements > 0) {
              if (once == -1) {
                this.unmarkedJustOnce = 0;
              }
              this.unmarkedJust = this.unmarkedJust.concat(data.content);
            }
          },
          error => console.log(error + 'unmarkedjustifications')
        );
    }
  }

  getMarkedJustificationsByAnswer(answerId: number) {
    let once: number = this.markedJustOnce.get(answerId);
    if ((once == -1) || (once == 0)) {
      let page: number = this.markedJustPage.get(answerId) + 1;
      this.justificationService
        .getMarkedJustificationsByAnswer(this.id, answerId, page)
        .subscribe(
          (data: Page<Justification>) => {
            if ((data.numberOfElements === 0) && (once == 0)) {
              this.markedJustOnce.set(answerId, 1);
              this.dialogService.openAlert({
                message: 'No hay más justificaciones en esta respuesta', //TODO: put answer name
                title: 'No hay más justificaciones',
                closeButton: 'Cerrar'
              });
            } else if (data.numberOfElements > 0) {
              if (once == -1) {
                this.markedJustOnce.set(answerId, 0);
                this.markedJust.set(answerId, []);
              }
              this.markedJustPage.set(answerId, page);
              let just = this.markedJust.get(answerId).concat(data.content);
              this.markedJust.set(answerId, just);
            }
          },
          error => console.log(error + 'markedjustifications in answer ' + answerId)
        );
    }
  }

  deleteAnswer(answerId: number) {
    this.dialogService.openConfirm({
      message: '¿Quieres eliminar esta respuesta?',
      title: 'Confirmar',
      acceptButton: 'Aceptar',
      cancelButton: 'Cancelar',
      width: '500px',
      height: '175px'
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.answerService
          .removeAnswer(answerId, this.id)
          .subscribe((_) => this.getMarkedAnswers(), /*this.router.navigate(['/teacher/' + this.id]),*/(error) => console.error(error + 'markedanswers on ans delete'));
        console.log(this.markedAnswers);
      }
    });
  }

  deleteJustification(justId: number, answerId: number) {
    this.dialogService.openConfirm({
      message: '¿Quieres eliminar esta justificacion?',
      title: 'Confirmar',
      acceptButton: 'Aceptar',
      cancelButton: 'Cancelar',
      width: '500px',
      height: '175px'
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.justificationService
          .removeJustification(justId, answerId)
          .subscribe((_) => this.getMarkedAnswers(), (error) => {
            if (error === 400) {
              this.dialogService.openAlert({
                message: 'No se puede eliminar una justificación de una respuesta incorrecta si no hay mas justificaciones',
                title: 'Error al borrar',
                closeButton: 'Cerrar',
              });
            } else {
              console.error(error + 'markedanswers on just delete');
            }
          });
      }
    });
  }

  showDiagram() {
    this.diagramDialog.open(DiagramComponent, {
      height: "600px",
      width: "800px"
    });
  }

  openDialogAnswer() {

    const dialogRef = this.answerDialog.open(NewAnswerComponent, {
      data: {
        id: this.id
      }
    });
    dialogRef.afterClosed().subscribe(
      result => {
        this.markedAnswers.push(result);
      }
    );
  }

  addJustification(answerid: number) {
    const dialogRef = this.answerDialog.open(NewJustComponent, {
      data: {
        id: answerid,

      }

    });
    dialogRef.afterClosed().subscribe(
      result => {
        let answer: Answer = this.markedAnswers.find(j => j.id == answerid);
        const index = this.markedAnswers.indexOf(answer, 0);
        if (index > 1) {
          this.markedAnswers.splice(index, 1);
        }
        answer.justifications.push(result);
        this.markedAnswers.push(answer);
      }
    );
  }
}
