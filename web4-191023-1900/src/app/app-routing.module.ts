import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DataComponent } from "./data/data.component";
import {MainComponent} from "./main/main.component";
import {AuthenticationGuard} from "./authentication.guard";

// making (связывание)
const routes: Routes = [
  {
    path: '',
    canActivate: [AuthenticationGuard], children: [
      {
        path: '',
        component: DataComponent
      },
      {
        path: 'main',
        component: MainComponent
      },
      {
        path: '**',
        redirectTo: ''
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
