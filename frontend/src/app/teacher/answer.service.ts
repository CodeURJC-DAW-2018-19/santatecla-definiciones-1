import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


import { AnswerPage } from "./answerPage.model";

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
       return this.http.get<AnswerPage>("/api/concepts/"+id+"/markedanswers", httpOptions);
    }

    getUnmarkedAnswers(id: number){
      return this.http.get<AnswerPage>("/api/concepts/"+id+"/unmarkedanswers", httpOptions);
   }

}