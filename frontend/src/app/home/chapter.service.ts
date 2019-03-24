import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { HttpHeaders } from "@angular/common/http";
import { Chapter } from "./chapter.model";
import { Page } from "../page/page.model";
import { environment } from "../../environments/environment";
import { Concept } from './concept.model';

@Injectable()
export class ChapterService {
  httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json"
    })
  };
  apiUrl = environment.baseUrl;
  constructor(private http: HttpClient) {}
  getChapters() {
    return this.http.get<Page<Chapter>>(this.apiUrl + "/chapters/", this.httpOptions);
  }
  getConceptPerChapter(chapterId: number) {
      return this.http.get<Page<Concept>>(this.apiUrl + "/chapters/" + chapterId + "/concepts", this.httpOptions);
  }
}
