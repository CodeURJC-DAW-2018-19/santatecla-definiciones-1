import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";

import { AnswerService } from "./answer.service";
import { Answer } from "./answer.model";
import { Justification } from "./justification.model";
import { Page } from "../page/page.model";
import { DiagramComponent } from "../diagram/diagram.component";
import { MatDialog } from "@angular/material";
import { TdDialogService } from '@covalent/core';
//later add justificationPage

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
  id: number;

  constructor(
    private diagramDialog: MatDialog,
    private router: Router,
    activatedRoute: ActivatedRoute,
    private answerService: AnswerService,
    private _dialogService: TdDialogService
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
        error => console.log(error)
      );
  }

  getUnmarkedAnswers(id: number) {
    this.answerService
      .getUnmarkedAnswers(id)
      .subscribe(
        (data: Page<Answer>) => (this.unmarkedAnswers = data["content"]),
        error => console.log(error)
      );
  }

  deleteAnswer(answerId: number) {
    this._dialogService.openConfirm({
        message: '¿Quieres eliminar esta respuesta?',
        title: 'Confirmar', 
        width: '500px', 
        height: '175px'
    }).afterClosed().subscribe((accept: boolean) => {
        if (accept) {
            this.answerService
                .removeAnswer(answerId, this.id) 
                .subscribe((_) => this.getMarkedAnswers(this.id), /*this.router.navigate(['/teacher/' + this.id]),*/ (error) => console.error(error));
            console.log(this.markedAnswers);
        }
    });
}

  showDiagram() {
    this.diagramDialog.open(DiagramComponent, {
      height: "600px",
      width: "800px"
    });
  }
}
