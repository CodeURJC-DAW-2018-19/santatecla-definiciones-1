import { Component } from "@angular/core";
import { ChapterService } from "./chapter.service";
import { Chapter } from "./chapter.model";
import { Page } from "../page/page.model";
import { Concept } from "./concept.model";

@Component({
  selector: "main",
  templateUrl: "./main.component.html"
})
export class ChapterComponent {
  chapterConcepts: Map<Chapter, Concept[]> = new Map();

  constructor(private chapterService: ChapterService) {
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
    for (let i = 0; i < data.content.length; i++) {
      this.getConcepts(data.content[i])
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
    this.chapterConcepts.set(chapter, data.content);
  }

}
