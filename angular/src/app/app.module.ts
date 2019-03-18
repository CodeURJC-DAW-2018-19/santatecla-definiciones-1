import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatMenuModule } from '@angular/material/menu';

import { CovalentLayoutModule } from '@covalent/core/layout';
import { CovalentStepsModule  } from '@covalent/core/steps';
import { CovalentTabSelectModule } from '@covalent/core/tab-select';

import { HeaderComponent } from "./header.component";
import { TestComponent } from "./test.component";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatMenuModule,
    CovalentLayoutModule,
    CovalentStepsModule,
    CovalentTabSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
