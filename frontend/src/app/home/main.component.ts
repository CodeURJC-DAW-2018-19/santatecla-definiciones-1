import { Component } from "@angular/core";
import { ChapterService } from "./chapter.service";
import { Chapter } from "./chapter.model";
import { Page } from "../page/page.model";
import { Concept } from "./concept.model";
import { LoginService } from '../login/login.service';
import { HeaderService } from '../header/header.service';
import { MatDialog } from '@angular/material';
import { DiagramComponent } from '../diagram/diagram.component';
import { ImageService } from '../images/image.service';
import { newConcept } from './newConcept.component';

@Component({
  selector: "main",
  templateUrl: "./main.component.html",
  styleUrls: ["./main.component.css"]
})
export class ChapterComponent {
  chapterConcepts: Map<Chapter, Concept[]> = new Map();
  constructor(private conceptDialog: MatDialog,
              private diagramDialog: MatDialog, 
              private chapterService: ChapterService, 
              public loginService: LoginService, 
              public headerService: HeaderService, 
              private imageService: ImageService) {
    this.getChapters();
  }

  getChapters() {
    this.chapterService
      .getChapters()
      .subscribe(
        (data: Page<Chapter>) => this.addChapters(data),
        error => console.log(error)
      );
  }

  addChapters(data: Page<Chapter>) {
    for (let ch of data.content) {
      //Set chapter first to keep ordering
      this.chapterConcepts.set(ch, []);
      this.getConcepts(ch);
    }
  }

  getConcepts(chapter: Chapter) {
    this.chapterService
      .getConceptPerChapter(chapter.id)
      .subscribe(
        (data: Page<Concept>) => this.addConcepts(chapter, data),
        error => console.log(error)
      );
  }

  addConcepts(chapter: Chapter, data: Page<Concept>) {
    let conceptInfo = data.content;
    for (let concept of conceptInfo){
      this.imageService.getImage(concept.id).subscribe(
        (data: Blob) => this.imageService.createImageFromBlob(data, ((image) => concept.image = image)),
        error => console.log(error)
      )
    }
    this.chapterConcepts.set(chapter, conceptInfo);
  }

  showDiagram() {
    this.diagramDialog.open(DiagramComponent, {
      height: "600px",
      width: "800px"
    });
  }

  showDialogNewConcept(id: number){
    
    const dialogRef = this.conceptDialog.open(newConcept,{
      data: {
        id: id
      }
    });

  }
}
