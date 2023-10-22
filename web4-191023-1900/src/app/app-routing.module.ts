import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DataComponent } from "./data/data.component";
import {MainComponent} from "./main/main.component";

// making (связывание)
const routes: Routes = [
  {
    path: '',
    component: DataComponent
  },
  {
    path: 'main',
    component: MainComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
