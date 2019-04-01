import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Question } from "./question.model";
import { Page } from "../page/page.model";
import { environment } from "../../environments/environment";
import { LoginService } from "../login/login.service";

const BASE_URL = environment.baseUrl;

@Injectable()
export class QuestionsService {
  constructor(private http: HttpClient, private loginService: LoginService) { }

  getMarkedQuestions(id: number, page: number) {
    return this.http.get<Page<Question>>(
      BASE_URL + "/concepts/" + id + "/markedquestions" + "?page=" + page,
      { withCredentials: true }
    );
  }

  getUnmarkedQuestions(id: number, page: number) {
    return this.http.get<Page<Question>>(
      BASE_URL + "/concepts/" + id + "/unmarkedquestions" + "?page=" + page,
      { withCredentials: true }
    );
  }
}
