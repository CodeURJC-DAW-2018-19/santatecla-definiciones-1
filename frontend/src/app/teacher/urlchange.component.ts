import { Component } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { TeacherService } from "./teacher.service";
import { Concept } from '../home/concept.model';

/**
 * Url changer for teacher
 */
//TODO: Error handling
@Component({
  selector: "urlchange",
  templateUrl: "./urlchange.component.html",
  styleUrls: ["./urlchange.component.css"]
})
export class UrlChangerComponent {
  url: string = "";
  id: number;
  constructor(
    private router: Router,
    activatedRoute: ActivatedRoute,
    private teacherService: TeacherService
  ) {
    this.id = activatedRoute.snapshot.params["id"];
    this.teacherService
      .getConceptInfo(this.id)
      .subscribe(
        (data: Concept) => (this.url = data["URL"]),
        error => console.log(error)
      );
  }
  saveUrl() {
    let conceptInfo = {
      URL: this.url
    };
    this.teacherService
      .updateConceptInfo(this.id, conceptInfo)
      .subscribe(
        (data: Concept) => (this.url = data["URL"]),
        error => console.log(error)
      );
  }
}
