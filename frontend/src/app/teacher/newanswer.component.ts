import { Component, Inject} from "@angular/core";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material'

@Component({
    selector: 'dialogAnswer',
    templateUrl: './dialogAnswer.component.html',
    styleUrls: ["./newanswer.component.css"]
  })

  export class NewAnswerComponent {
  
    constructor(
      public dialogRef: MatDialogRef<NewAnswerComponent>
    ) {}
  
    onNoClick(): void {
      this.dialogRef.close();
    }
  
  }