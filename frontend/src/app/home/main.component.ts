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
import { TdDialogService } from '@covalent/core';

@Component({
  selector: "main",
  templateUrl: "./main.component.html",
  styleUrls: ["./main.component.css"]
})
export class ChapterComponent {
  chaptersPage: number;
  chaptersOnce: number;
  //-1 means not initialized, 0 means false, 1 means true
  //we need to use -1 so we don't get the alert first time we try to get them

  conceptsPage: Map<Chapter, number> = new Map();
  conceptsOnce: Map<Chapter, boolean> = new Map();
  //-1 means not initialized, 0 means false, 1 means true
  //we need to use -1 so we don't get the alert first time we try to get them

  chapterConcepts: Map<Chapter, Concept[]> = new Map();


  constructor(private dialogService: TdDialogService,
    private conceptDialog: MatDialog,
    private diagramDialog: MatDialog,
    private chapterService: ChapterService,
    public loginService: LoginService,
    public headerService: HeaderService,
    private imageService: ImageService) {
    this.chaptersPage = 0;
    this.chaptersOnce = -1;
  }

  ngOnInit() {
    this.getChapters();
  }

  getChapters() {
    let once: number = this.chaptersOnce;
    if ((once == -1) || (once == 0)) {
      let page: number = this.chaptersPage++;
      this.chapterService
        .getChapters(page)
        .subscribe(
          (data: Page<Chapter>) => {
            if ((data.numberOfElements === 0) && (once == 0)) {
              this.chaptersOnce = 1;
              this.dialogService.openAlert({
                message: 'No hay más temas disponibles',
                title: 'No hay más temas',
                closeButton: 'Cerrar'
              });
            } else if (data.numberOfElements > 0) {
              if (once == -1) {
                this.chaptersOnce = 0;
              }
              this.addChapters(data);
            }
          },
          error => console.log(error)
        );
    }
  }

  addChapters(data: Page<Chapter>) {
    for (let ch of data.content) {
      //Set chapter first to keep ordering
      console.log(this.chapterConcepts.get(ch));
      if (!this.chapterConcepts.has(ch)) {
        //console.log(this.chapterConcepts.has(ch));
        this.chapterConcepts.set(ch, []);
        if (!this.conceptsPage.has(ch)) { //add the chapter to both maps
          this.conceptsPage.set(ch, -1);
          this.conceptsOnce.set(ch, false);
        }
        this.getConcepts(ch);
        //console.log(this.chapterConcepts);
      }
    }
  }

  getConcepts(chapter: Chapter) {
    if (this.conceptsOnce.has(chapter)) {
      let once: boolean = this.conceptsOnce.get(chapter)
      let page: number = this.conceptsPage.get(chapter) + 1;
      this.conceptsPage.set(chapter, page);
      this.chapterService
        .getConceptPerChapter(chapter.id, page)
        .subscribe(
          (data: Page<Concept>) => {
            if ((data.numberOfElements === 0) && (once == true)) {
              this.conceptsOnce.delete(chapter);
              this.conceptsPage.delete(chapter);
              this.dialogService.openAlert({
                message: 'No hay más conceptos para ' + chapter.chapterName,
                title: 'No hay más conceptos',
                closeButton: 'Cerrar'
              });
            } else if (data.numberOfElements > 0) {
              if (once == false) {
                this.conceptsOnce.set(chapter, true);
              }
              this.addConcepts(chapter, data);
            }
          },
          error => console.log(error)
        );

    }
  }


  addConcepts(chapter: Chapter, data: Page<Concept>) {
    let conceptInfo = data.content;
    for (let concept of conceptInfo) {
      this.imageService.getImage(concept.id).subscribe(
        (data: Blob) => this.imageService.createImageFromBlob(data, ((image) => concept.image = image)),
        error => console.log(error)
      )
    }
    conceptInfo = this.chapterConcepts.get(chapter).concat(data.content);
    this.chapterConcepts.set(chapter, conceptInfo);
  }

  showDiagram() {
    this.diagramDialog.open(DiagramComponent, {
      height: "600px",
      width: "800px"
    });
  }

  showDialogNewConcept(id: number) {

    const dialogRef = this.conceptDialog.open(newConcept, {
      data: {
        id: id
      }
    });
    dialogRef.afterClosed().subscribe(
      result => {
        let chapters = Array.from(this.chapterConcepts.keys());
        let chapter: Chapter = chapters.find(j => j.id == id);
        let concepts = this.chapterConcepts.get(chapter);
        concepts.push(result);
        this.chapterConcepts.set(chapter, concepts);
      },
      error => console.log(error)
    );

  }

  deleteConcept(chapterId: number, conceptId: number) {
    this.dialogService.openConfirm({
      message: '¿Quieres eliminar este concepto?',
      title: 'Confirmar',
      acceptButton: 'Aceptar',
      cancelButton: 'Cancelar',
      width: '500px',
      height: '175px'
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.chapterService
          .deleteConcept(chapterId, conceptId)
          .subscribe((_) => {
            let chapters = Array.from(this.chapterConcepts.keys());
            let chapter: Chapter = chapters.find(j => j.id == chapterId);
            let concepts = this.chapterConcepts.get(chapter);
            let concept: Concept = concepts.find(i => i.id == conceptId);
            const index = concepts.indexOf(concept, 0);
            if (index > -1) {
              concepts.splice(index, 1);
            }
            this.chapterConcepts.set(chapter, concepts);
          },
            (error) => console.error(error + 'markedanswers on ans delete'));
      }
    });
  }

  deleteChapter(chapter: Chapter) {
    this.dialogService.openConfirm({
      message: '¿Quieres eliminar este tema?',
      title: 'Confirmar',
      acceptButton: 'Aceptar',
      cancelButton: 'Cancelar',
      width: '500px',
      height: '175px'
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.chapterService.deleteChapter(chapter.id).subscribe(
          (_) => this.chapterConcepts.delete(chapter),
          error => console.error(error)
        )
      }
    });
  }

  addChapter() {
    this.dialogService.openPrompt({
      message: "Elige un nombre para el tema: ",
      title: "Crear tema",
      acceptButton: "Crear",
      cancelButton: "Cancelar",
      width: '500px',
      height: '250px'
    }).afterClosed().subscribe((newChapterName: string) => {
      if (newChapterName) {
        this.chapterService.createChapter(newChapterName).subscribe(
          (data: Chapter) => {this.chapterConcepts.set(data, []); console.log()},
          error => console.error(error)
        );
      } else {
        alert("Tienes que escribir un nombre para el tema en el campo de texto.");
      }
    })
  }
}
