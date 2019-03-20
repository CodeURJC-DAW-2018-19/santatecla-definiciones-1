import { Component } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';

import { QuestionsService } from "./question.service";
import { Question } from "./question.model";
import { QuestionPage } from './questionPage.model';


@Component({
    selector: "student",
    templateUrl: "./student.component.html"
  })

export class StudentComponent {

  markedQuestions: Question[];
  questionPage: QuestionPage;
  id: number;

  constructor(private router: Router, activatedRoute: ActivatedRoute,private questionsService: QuestionsService) {
    this.id = activatedRoute.snapshot.params['id'];
    this.getMarkedQuestions(this.id);
  }

  getMarkedQuestions(id: number){
    this.questionsService.getMarkedQuestions(id).subscribe(
      //it returns null, might be QuestionPage non primitive classes
      (data: QuestionPage) => console.log(data),  
      error => console.log(error)
    );
  }


} 