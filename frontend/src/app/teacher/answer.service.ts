import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Answer } from './answer.model';
import { Page } from '../page/page.model';
import { environment } from "../../environments/environment";

const BASE_URL = "https://localhost:8443/api";

//It will be necessary to access the user information by a new service
const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Basic ' + btoa('admin:adminpass')
    })
};

@Injectable()
export class AnswerService {

    constructor(private http: HttpClient) { }

    getMarkedAnswers(id: number){
       return this.http.get<Page<Answer>>("/api/concepts/"+id+"/markedanswers", httpOptions);
    }

    getUnmarkedAnswers(id: number){
      return this.http.get<Page<Answer>>("/api/concepts/"+id+"/unmarkedanswers", httpOptions);
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