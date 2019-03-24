import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { MatTableModule } from "@angular/material/table";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";

import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { MatTabsModule } from "@angular/material/tabs";
import { MatListModule } from "@angular/material/list";
import { MatCardModule } from "@angular/material/card";
import { MatDividerModule } from "@angular/material/divider";
import { MatInputModule } from "@angular/material/input";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatDialogModule } from "@angular/material/dialog";
import { MatPaginatorModule } from '@angular/material/paginator';

import { CovalentLayoutModule } from "@covalent/core/layout";
import { CovalentStepsModule } from "@covalent/core/steps";

import { CovalentBaseEchartsModule } from '@covalent/echarts/base';
import { CovalentBarEchartsModule } from '@covalent/echarts/bar';
import { CovalentTooltipEchartsModule } from '@covalent/echarts/tooltip';

import { HeaderComponent } from "./header/header.component";
import { DiagramComponent } from "./diagram/diagram.component";
import { StudentComponent } from "./student/student.component";
import { TeacherComponent } from "./teacher/teacher.component";
import { UrlChangerComponent } from "./teacher/urlchange.component";

import { DiagramService } from "./diagram/diagram.service";
import { QuestionsService } from "./student/question.service";
import { AnswerService } from "./teacher/answer.service";
import { TeacherService } from "./teacher/teacher.service";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DiagramComponent,
    StudentComponent,
    TeacherComponent,
    UrlChangerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CovalentLayoutModule,
    CovalentStepsModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatTabsModule,
    MatListModule,
    MatCardModule,
    MatFormFieldModule,
    MatDividerModule,
    HttpClientModule,
    FormsModule,
    MatTableModule,
    MatDialogModule,
    CovalentBaseEchartsModule,
    CovalentBarEchartsModule,
    CovalentTooltipEchartsModule,
    MatPaginatorModule
  ],
  providers: [QuestionsService, TeacherService, AnswerService, DiagramService],
  bootstrap: [AppComponent, DiagramComponent]
})
export class AppModule {}
