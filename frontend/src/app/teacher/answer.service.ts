import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { HttpHeaders } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";

import { Answer } from "./answer.model";
import { Page } from "../page/page.model";
import { LoginService } from "../login/login.service";

import { environment } from "../../environments/environment";

//It will be necessary to access the user information by a new service

@Injectable()
export class AnswerService {
  constructor(private http: HttpClient, private loginService: LoginService) {}
  apiUrl = environment.baseUrl;
  getMarkedAnswers(id: number) {
    return this.http.get<Page<Answer>>(
      this.apiUrl + "/concepts/" + id + "/markedanswers",
      { withCredentials: true }
    );
  }

  getUnmarkedAnswers(id: number) {
    return this.http.get<Page<Answer>>(
      this.apiUrl + "/concepts/" + id + "/unmarkedanswers",
      { withCredentials: true }
    );
  }

  removeAnswer(answerId: number, conceptId: number): Observable<Answer> {
    return this.http
      .delete<Answer>(
        this.apiUrl + "/concepts/" + conceptId + "/answers/" + answerId
      )
      .pipe(catchError(error => this.handleError(error)));
  }

  private handleError(error: any) {
    console.error(error);
    return throwError(
      new Error("Server error (" + error.status + "): " + error)
    );
  }

  postNewAnswer(id: number, answer: Answer) {
    return this.http.post(
      this.apiUrl + "/concepts/" + id + "/answers",
      answer,
      { withCredentials: true }
    );
  }
}
