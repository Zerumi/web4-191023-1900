import {Component, AfterViewInit, OnInit} from '@angular/core';
import {PostResponse, Result, ResultRequest} from "../model";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit, AfterViewInit{

  y_select: number = 0;
  x_select: string = '0';
  r_select: string = '0';
  results: Result[] = [{
    "request": {
      "x": 2.0,
      "y": 2.0,
      "r": 2.0
    },
    "result": false,
    "executedAt": new Date(Date.now()),
    "executionTime": 553667
  }];

  constructor(private http: HttpClient) {
  }

  ngAfterViewInit() {
    // We're loading the player script on after view is loaded
    // @ts-ignore
    import('../../assets/js/graph.js');
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

  sendData() {
    let request = new ResultRequest();
    request.x = Number(this.x_select);
    request.y = this.y_select;
    request.r = Number(this.r_select);
    this.http.post<Result>(environment.backendURL + "/app/check-hit", request)
      .subscribe((res : Result) => {
        if (res) {
          this.results.push(res);
        }
      }
    )
  }
}
