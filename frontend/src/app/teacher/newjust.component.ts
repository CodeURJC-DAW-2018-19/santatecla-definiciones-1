import { Component, Inject} from "@angular/core";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material'

import { Answer } from "./answer.model";
import { AnswerService } from "./answer.service";
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
    markedJustification: boolean = true;
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
            marked: this.markedJustification,
            valid: this.validJustification,
            error: this.error
    }

}