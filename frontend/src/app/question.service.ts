import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


import { QuestionPage } from "./questionPage.model";

const BASE_URL = "https://localhost:8443/api";

//It will be necessary to access the user information by a new service
const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Basic ' + btoa('user:pass')
    })
};

@Injectable()
export class QuestionsService {

    constructor(private http: HttpClient) { }

    getMarkedQuestions(id: number){
       return this.http.get<QuestionPage>("/api/concepts/"+id+"/markedquestions", httpOptions);
    }

}