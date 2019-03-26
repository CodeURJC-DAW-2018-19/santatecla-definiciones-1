import { Component } from "@angular/core";
import { MatDialogRef } from "@angular/material";
import { DiagramService } from "./diagram.service";
import { Page } from "../page/page.model";
import { Diagram } from "./diagram.model";

@Component({
  selector: "diagram",
  templateUrl: "./diagram.component.html"
})
export class DiagramComponent {
  config: Object;
  constructor(
    private dialogRef: MatDialogRef<DiagramComponent>,
    private diagramService: DiagramService
  ) {
    this.diagramService
      .getDiagram()
      .subscribe(
        (data: Page<Diagram>) => this.addDataToDiagram(data),
        error => console.log(error)
      );
  }

  addDataToDiagram(data: Page<Diagram>) {
    let chapterNames: string[] = [];
    let unmarked: number[] = [];
    let correct: number[] = [];
    let incorrect: number[] = [];
    for (let i = 0; i < data.content.length; i++) {
        chapterNames.push(data.content[i].chapterName);
        unmarked.push(data.content[i].unmarked);
        correct.push(data.content[i].correct);
        incorrect.push(data.content[i].incorrect);
    }
    this.config = {
      toolbox: {},
      color: ["#27A1EE", "#F05050", "#43C472"],
      tooltip: {},
      legend: {
        data: [
          "Respuestas sin corregir",
          "Respuestas incorrectas",
          "Respuestas correctas"
        ]
      },
      xAxis: {
        data: chapterNames
      },
      yAxis: {},
      series: [
        {
          name: "Respuestas sin corregir",
          type: "bar",
          stack: "Tema",
          data: unmarked
        },
        {
          name: "Respuestas incorrectas",
          type: "bar",
          stack: "Tema",
          data: incorrect
        },
        {
          name: "Respuestas correctas",
          type: "bar",
          stack: "Tema",
          data: correct
        }
      ]
    }

    console.log(this.config);
  }

  close() {
    this.dialogRef.close();
  }
}
