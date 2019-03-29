import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
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
import { MatRadioModule } from '@angular/material/radio';

import { CovalentLayoutModule } from "@covalent/core/layout";
import { CovalentStepsModule } from "@covalent/core/steps";
import { CovalentExpansionPanelModule } from '@covalent/core/expansion-panel';
import { CovalentDialogsModule } from '@covalent/core';
import { CovalentFileModule } from '@covalent/core/file'

import { CovalentBaseEchartsModule } from '@covalent/echarts/base';
import { CovalentBarEchartsModule } from '@covalent/echarts/bar';
import { CovalentTooltipEchartsModule } from '@covalent/echarts/tooltip';

import { ChapterComponent } from "./home/main.component";
import { HeaderComponent } from "./header/header.component";
import { DiagramComponent } from "./diagram/diagram.component";
import { StudentComponent } from "./student/student.component";
import { TeacherComponent } from "./teacher/teacher.component";
import { UrlChangerComponent } from "./teacher/urlchange.component";
import { NewAnswerComponent } from "./teacher/newanswer.component";

import { LoginService } from "./login/login.service";
import { ChapterService } from "./home/chapter.service";
import { DiagramService } from "./diagram/diagram.service";
import { QuestionsService } from "./student/question.service";
import { AnswerService } from "./teacher/answer.service";
import { JustificationService } from "./teacher/justification.service";
import { TeacherService } from "./teacher/teacher.service";
import { BasicAuthInterceptor } from './login/auth.interceptor';
import { ErrorInterceptor } from './login/error.interceptor';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { HeaderService } from './header/header.service';
import { ImageService } from './images/image.service';
import { ImagePosterComponent } from './teacher/imageposter.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ChapterComponent,
    DiagramComponent,
    StudentComponent,
    TeacherComponent,
    UrlChangerComponent,
    ImagePosterComponent,
    NewAnswerComponent
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
    MatPaginatorModule,
    CovalentExpansionPanelModule,
    CovalentDialogsModule,
    CovalentFileModule,
    MatRadioModule
  ],
  providers: [ChapterService, QuestionsService, TeacherService, AnswerService, JustificationService, DiagramService, LoginService, HeaderService, ImageService,
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: LocationStrategy, useClass: HashLocationStrategy }],
  bootstrap: [AppComponent, DiagramComponent],
  entryComponents: [NewAnswerComponent]
})
export class AppModule { }
