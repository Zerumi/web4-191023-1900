import {Component, OnInit} from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {

  constructor(private http: HttpClient) {

  }
  ngOnInit(): void {

    this.http.get<any>(environment.backendURL + "/app/hello").subscribe(
        {
          next: (resp: any) => {

          },

          error: (err) => {
              console.error(err);
          }
        }
    )

  }

}
