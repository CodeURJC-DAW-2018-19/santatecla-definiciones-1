import { Component } from "@angular/core";
import { Router } from "@angular/router";

/** 
 * Wrapper component for all teacher information.
*/
@Component({
    selector: "teacher",
    templateUrl: "./teacher.component.html"
  })

export class TeacherComponent {
    constructor(private router: Router) { }
}