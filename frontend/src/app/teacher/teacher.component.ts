import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";

import { AnswerService } from "./answer.service";
import { Answer } from "./answer.model";
import { Justification } from "./justification.model";
import { Page } from '../page/page.model';
//later add justificationPage

/** 
 * Wrapper component for all teacher information.
*/
@Component({
    selector: "teacher",
    templateUrl: "./teacher.component.html",
    styleUrls: ["./teacher.component.css"]
  })

export class TeacherComponent {
  markedAnswers: Answer[] = [];
  unmarkedAnswers: Answer[] = [];
  answerPage: Page<Answer>;
  id: number;

  constructor(private router: Router, activatedRoute: ActivatedRoute, private answerService: AnswerService) {
    this.id = activatedRoute.snapshot.params['id'];
    this.getMarkedAnswers(this.id);
    this.getUnmarkedAnswers(this.id);
  }

  getMarkedAnswers(id: number){
    this.answerService.getMarkedAnswers(id).subscribe(
      (data: Page<Answer>) => this.markedAnswers = data["content"],
      error => console.log(error),
    );
  }

  getUnmarkedAnswers(id: number){
    this.answerService.getUnmarkedAnswers(id).subscribe(
      (data: Page<Answer>) => this.unmarkedAnswers = data["content"],
      error => console.log(error)
    );
  }
}