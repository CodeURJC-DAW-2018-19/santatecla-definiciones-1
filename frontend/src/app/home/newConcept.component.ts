import { Component, Inject} from "@angular/core";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { Concept } from './concept.model';
import { ChapterService } from './chapter.service';

@Component({
    selector: 'dialogConcept',
    templateUrl: './dialogConcept.component.html'
  })

  export class newConcept {

    id: number;

    conceptName: string;
    URL: string;

    constructor( public dialogRef: MatDialogRef<newConcept>, 
                @Inject(MAT_DIALOG_DATA) public data: any,
                private chapterService: ChapterService) {
        this.id = data["id"];
    }

    newConcept(){
        let concept: Concept = {
            conceptName: this.conceptName,
            URL: this.URL,
        };
        this.chapterService.addConcept(this.id, concept).subscribe(
            data => console.log(data),
            error => console.log(error)
          );
        this.dialogRef.close();
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

  }