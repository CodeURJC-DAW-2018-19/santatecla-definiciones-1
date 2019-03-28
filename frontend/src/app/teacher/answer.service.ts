import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Answer } from './answer.model';
import { Page } from '../page/page.model';
import { LoginService } from "../login/login.service";

import { environment } from "../../environments/environment";


const BASE_URL = environment.baseUrl;

//It will be necessary to access the user information by a new service

@Injectable()
export class AnswerService {
  constructor(private http: HttpClient, private loginService: LoginService) {}

  getMarkedAnswers(id: number) {
    return this.http.get<Page<Answer>>(
      BASE_URL + "/concepts/" + id + "/markedanswers",
      { withCredentials: true }
    );
  }

  getUnmarkedAnswers(id: number) {
    return this.http.get<Page<Answer>>(
      BASE_URL + "/concepts/" + id + "/unmarkedanswers",
      { withCredentials: true }
    );
  }
  apiUrl = environment.baseUrl;

   removeAnswer(answerId: number, conceptId: number): Observable<Answer> {
      return this.http
          .delete<Answer>(this.apiUrl + '/concepts/' + conceptId + '/answers/' + answerId)
          .pipe(catchError((error) => this.handleError(error)));
   }

   private handleError(error: any) {
      console.error(error);
      return throwError(new Error('Server error (' + error.status + '): ' + error));
   }
}