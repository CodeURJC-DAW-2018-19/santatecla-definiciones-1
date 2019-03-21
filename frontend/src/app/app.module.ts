import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";

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

import { CovalentLayoutModule } from "@covalent/core/layout";
import { CovalentStepsModule } from "@covalent/core/steps";

import { HeaderComponent } from "./header.component";
import { StudentComponent } from "./student.component";
import { TeacherComponent } from "./teacher/teacher.component";
import { UrlChangerComponent } from "./teacher/urlchange.component";

import { QuestionsService } from "./question.service";
import { TeacherService } from "./teacher/teacher.service";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
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
    FormsModule
  ],
  providers: [QuestionsService, TeacherService],
  bootstrap: [AppComponent]
})
export class AppModule {}
