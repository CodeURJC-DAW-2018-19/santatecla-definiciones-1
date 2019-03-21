import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";

import { AnswerService } from "./answer.service";
import { Answer } from "./answer.model";
import { AnswerPage } from './answerPage.model';

/** 
 * Wrapper component for all teacher information.
*/
@Component({
    selector: "teacher",
    templateUrl: "./teacher.component.html"
  })

export class TeacherComponent {
  markedAnswers: Answer[];
  unmarkedAnswers: Answer[];
  answerPage: AnswerPage;
  id: number;

  constructor(private router: Router, activatedRoute: ActivatedRoute, private answerService: AnswerService) {
    this.id = activatedRoute.snapshot.params['id'];
    this.getMarkedAnswers(this.id);
    this.getUnmarkedAnswers(this.id);
  }

  getMarkedAnswers(id: number){
    this.answerService.getMarkedAnswers(id).subscribe(
      (data: AnswerPage) => this.markedAnswers = data["content"],
      error => console.log(error)
    );
  }

  getUnmarkedAnswers(id: number){
    this.answerService.getUnmarkedAnswers(id).subscribe(
      (data: AnswerPage) => this.unmarkedAnswers = data["content"],
      error => console.log(error)
    );
  }
}