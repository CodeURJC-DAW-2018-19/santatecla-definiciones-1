import { Component } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';

import { QuestionsService } from "./question.service";
import { Question } from "./question.model";
import { QuestionPage } from './questionPage.model';


@Component({
    selector: "student",
    templateUrl: "./student.component.html",
    styleUrls: ["./student.component.css"]
  })

export class StudentComponent {
  markedQuestions: Question[];
  unmarkedQuestions: Question[];
  questionPage: QuestionPage;
  id: number;

  constructor(private router: Router, activatedRoute: ActivatedRoute,private questionsService: QuestionsService) {
    this.id = activatedRoute.snapshot.params['id'];
    this.getMarkedQuestions(this.id);
    this.getUnmarkedQuestions(this.id);
  }

  getMarkedQuestions(id: number){
    this.questionsService.getMarkedQuestions(id).subscribe(
      (data: QuestionPage) => this.markedQuestions = data["content"],
      error => console.log(error)
    );
  }

  getUnmarkedQuestions(id: number){
    this.questionsService.getUnmarkedQuestions(id).subscribe(
      (data: QuestionPage) => this.unmarkedQuestions = data["content"],
      error => console.log(error)
    );
  }


} 