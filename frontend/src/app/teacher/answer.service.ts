import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { Answer } from "./answer.model";
import { Page } from "../page/page.model";
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

  postNewAnswer(id: number,answer: Answer){
    return this.http.post(
      BASE_URL + "/concepts/" + id + "/answers",
      answer,
      { withCredentials: true }
    );
  }

}
