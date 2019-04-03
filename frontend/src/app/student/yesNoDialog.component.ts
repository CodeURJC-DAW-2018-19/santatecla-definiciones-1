import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";

@Component({
  selector: "yesnodialog",
  templateUrl: "yesNoDialog.component.html",
  styles: [`
  mat-radio-button {
    margin-bottom: 5px
  }
  `]
})
export class YesNoDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<YesNoDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  solution: string = null;
  disable: boolean = true;
  checkRadio(){
    this.disable = false;
  }
}
