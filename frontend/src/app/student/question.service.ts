import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Question } from "./question.model";
import { Page } from "../page/page.model";
import { environment } from "../../environments/environment";
import { LoginService } from "../login/login.service";
import { StudentAnswer } from "./studentAnswer.model";

const BASE_URL = environment.baseUrl;

@Injectable()
export class QuestionsService {
  constructor(private http: HttpClient, private loginService: LoginService) {}

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

  getNewQuestion(id: number) {
    return this.http.get<Question>(
      BASE_URL + "/concepts/" + id + "/newquestion",
      { withCredentials: true }
    );
  }

  saveAnswer(
    conceptId: number,
    questionType: number,
    answerText: string,
    questionText: string,
    answerId?: number,
    justificationId?: number
  ) {
    let body: StudentAnswer = {
      questionType: questionType,
      questionText: questionText
    }
    if (answerText === "yes" || answerText === "no") { // If question is a yes/no question
      body.answerOption = answerText;
    } else {
      body.answerText = answerText;
    }
    if (answerId)
      body.answerId = answerId;
    if (justificationId)
      body.justificationQuestionId = justificationId;
    return this.http.post<StudentAnswer>(
      BASE_URL + "/concepts/" + conceptId + "/saveanswer",
      body,
      { withCredentials: true }
    );
  }
}
