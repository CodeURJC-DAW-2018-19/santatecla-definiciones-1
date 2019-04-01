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
  unmarkedAnswers: Answer[] = [];
  answerPage: Page<Answer>;
  justPages:  Map<number, Page<Justification>>; //key: answer.id value:justification page per answer
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
    this.id = activatedRoute.snapshot.params["id"];
    this.getMarkedAnswers(this.id);
    this.getUnmarkedAnswers(this.id);
  }

  getMarkedAnswers(id: number) {
    this.answerService
      .getMarkedAnswers(id)
      .subscribe(
        (data: Page<Answer>) => (this.markedAnswers = data["content"]),
        error => console.log(error + 'markedanswers')
      );
  }

  getUnmarkedAnswers(id: number) {
    this.answerService
      .getUnmarkedAnswers(id)
      .subscribe(
        (data: Page<Answer>) => (this.unmarkedAnswers = data["content"]),
        error => console.log(error + 'unmarkedanswer')
      );
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
                .subscribe((_) => this.getMarkedAnswers(this.id), /*this.router.navigate(['/teacher/' + this.id]),*/ (error) => console.error(error + 'markedanswers on ans delete'));
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
                .subscribe((_) => this.getMarkedAnswers(this.id), (error) => {
                  if(error === 400){
                    this.dialogService.openAlert({
                      message: 'No se puede eliminar una justificación de una respuesta incorrecta si no hay mas justificaciones',
                      title: 'Error al borrar',
                      closeButton: 'Cerrar',
                    });
                  }else{
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

  openDialogAnswer(){

    const dialogRef = this.answerDialog.open(NewAnswerComponent, {
      data: {
        id: this.id
      }
    });

  }

  addJustification(answerid: number){
    const dialogRef = this.answerDialog.open(NewJustComponent, {
      data: {
        id: answerid,
         


      }

    });
    dialogRef.afterClosed().subscribe(
      result => {
        //TODO
        
      }
    )

  }
}
