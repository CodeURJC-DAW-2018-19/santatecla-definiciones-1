import { Component, Inject} from "@angular/core";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material'

import { Answer } from "./answer.model";
import { AnswerService } from "./answer.service";
import { Justification } from './justification.model';


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
  
    constructor( public dialogRef: MatDialogRef<NewAnswerComponent>, 
                @Inject(MAT_DIALOG_DATA) public data: any,
                private answerService: AnswerService) {
      this.id = data["id"];
    }

    newAnswer(){
      let justification: Justification = {
        justificationText: this.justificationText,
        marked: this.markedJustification,
        valid: this.validJustification,
        error: this.error
      }
      let answer: Answer = {
        answerText: this.answerText,
        marked: this.marked,
        correct: this.correct,
        justifications: [justification]
      };
      this.answerService.postNewAnswer(this.id,answer).subscribe(
        data => console.log(data),
        error => console.log(error)
      );
      this.dialogRef.close();
    }
    
    onNoClick(): void {
      this.dialogRef.close();
    }
  
  }