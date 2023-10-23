import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DataComponent } from './data/data.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { ButtonModule } from 'primeng/button';
import { MainComponent } from './main/main.component';
import { SliderModule } from 'primeng/slider';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RadioButtonModule} from "primeng/radiobutton";
import {RequestInterceptor} from "./request.interceptor";
import {TableModule} from "primeng/table";


@NgModule({
  declarations: [
    AppComponent,
    DataComponent,
    MainComponent
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        HttpClientModule,
        ButtonModule,
        SliderModule,
        ReactiveFormsModule,
        FormsModule,
        RadioButtonModule,
        TableModule
    ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
