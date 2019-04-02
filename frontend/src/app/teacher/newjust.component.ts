import { Component, Inject} from "@angular/core";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material'
import { Justification } from './justification.model';
import { JustificationService } from "./justification.service";



@Component({
selector: 'dialogJust',
templateUrl: './dialogJust.component.html',
styleUrls: ["./newjust.component.css"]
})
export class NewJustComponent {

    id: number;
    justificationText: string;
    validJustification : boolean;
    error: string;

    constructor( public dialogRef: MatDialogRef<NewJustComponent>, 
        @Inject(MAT_DIALOG_DATA) public data: any,
        private justificationService: JustificationService) {
    this.id = data["id"];
    }
    newJustification(){
        let justification: Justification = {
            justificationText: this.justificationText,
            valid: this.validJustification,
            error: this.error,
            marked: true
        };
        this.justificationService.postNewJustification(this.id, justification).subscribe(
            data =>this.dialogRef.close(data),
            error => console.log(error)
        );
    }
    onNoClick(){
        this.dialogRef.close();
    }
}
