import { Injectable } from '@angular/core';
import { HttpClient, HttpClientJsonpModule } from '@angular/common/http';
import { map } from 'rxjs/operators';


import { QuestionPage } from "./questionPage.model";

const BASE_URL = "https://localhost:8443/api";

@Injectable()
export class QuestionsService {

    constructor(private http: HttpClient) { }

    getMarkedQuestions(id: number)  {
       //return this.http.get<QuestionPage>(BASE_URL+"/concepts/"+id+"/markedquestions").pipe(map( response => new QuestionPage(response.json())));
       return this.http.get<QuestionPage>("/api/concepts/"+id+"/markedquestions");
    }

}