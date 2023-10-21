import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {PostResponse} from "../model";

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {

  constructor(private http: HttpClient) {

  }
  ngOnInit(): void {

    this.http.get<PostResponse>(environment.backendURL + "/app/hello").subscribe(
        {
          next: (resp: PostResponse) => {
              console.log(resp.message)
          },

          error: (err) => {
              console.error(err);
              console.log(err.message)
          }
        }
    )

  }

}
