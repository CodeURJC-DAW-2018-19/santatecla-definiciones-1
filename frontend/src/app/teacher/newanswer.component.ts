import { Component, Inject } from "@angular/core";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material'

import { Answer } from "./answer.model";
import { AnswerService } from "./answer.service";
import { Justification } from './justification.model';
import { JustificationService } from "./justification.service";



@Component({
  selector: 'dialogAnswer',
  templateUrl: './dialogAnswer.component.html',
  styleUrls: ["./newanswer.component.css"]
})

export class NewAnswerComponent {

  id: number;
  answerText: string;
  correct: boolean;
  marked: boolean = true;

  justificationText: string;
  markedJustification: boolean = true;
  validJustification: boolean;
  error: string;

  constructor(public dialogRef: MatDialogRef<NewAnswerComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private answerService: AnswerService,
    private justificationService: JustificationService) {
    this.id = data["id"];
  }

  newAnswer() {
    let justificationArray = [];
    let justification: Justification = {
      justificationText: this.justificationText,
      marked: this.markedJustification,
      valid: this.validJustification,
    }
    if (this.validJustification !== false) {
      justification.error = this.error;
    }

    if (justification.justificationText != null) {
      justificationArray.push(justification);
    }
    let answer: Answer = {
      answerText: this.answerText,
      marked: this.marked,
      correct: this.correct,
      justifications: justificationArray
    };
    this.answerService.postNewAnswer(this.id, answer).subscribe(
      data => {
        
        this.dialogRef.close(data);
      },
      error => console.log(error)
    );
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

}