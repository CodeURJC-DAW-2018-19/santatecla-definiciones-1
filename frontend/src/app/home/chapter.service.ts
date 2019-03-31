import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Chapter } from "./chapter.model";
import { Page } from "../page/page.model";
import { environment } from "../../environments/environment";
import { Concept } from "./concept.model";

@Injectable()
export class ChapterService {
  apiUrl = environment.baseUrl;

  constructor(private http: HttpClient) {}

  getChapters(page: number) {
    return this.http.get<Page<Chapter>>(this.apiUrl + "/chapters?sort=id" + "&page=" + page, {
      withCredentials: true
    });
  }

  getConceptPerChapter(chapterId: number, page: number) {
    return this.http.get<Page<Concept>>(
      this.apiUrl + "/chapters/" + chapterId + "/concepts" + "?page=" + page,
      { withCredentials: true }
    );
  }

  addConcept(id: number, concept: Concept) {
    return this.http.post(
      this.apiUrl + "/chapters/" + id + "/concepts",
      concept,
      { withCredentials: true }
    );
  }

  deleteConcept(chapterId: number, conceptId: number) {
    return this.http.delete(
      this.apiUrl + "/chapters/" + chapterId + "/concepts/" + conceptId,
      { withCredentials: true }
    );
  }

}
