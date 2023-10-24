import {Component, AfterViewInit, OnInit, HostListener} from '@angular/core';
import {GraphPoint, PostResponse, Result, ResultRequest} from "../model";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

declare function enable_graph() : void;
declare function on_main_load() : void;
declare function drawGraphByR(r : number) : void;
declare function drawPointXYRRes(x : number, y : number, r : number, result : boolean) : void;

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit, AfterViewInit{

  y_select: number = 0;
  x_select: string = '0';
  r_select: string = '1';
  results: Result[] = [];

  constructor(private http: HttpClient, private router: Router) {
  }

  ngAfterViewInit() {
    // We're loading the player script on after view is loaded
    // @ts-ignore
    import('../../assets/js/graph.js');
  }

  ngOnInit(): void {
    on_main_load();
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

    this.http.get<Result[]>(environment.backendURL + "/app/check-hit").subscribe(
      {
        next: (resp: Result[]) => {
          this.results = resp;
          resp.forEach(res => {
            drawPointXYRRes(res.request.x, res.request.y, res.request.r, res.result);
          })
          drawGraphByR(Number(this.r_select));
        },

        error: (err) => {
          console.error(err);
          console.log(err.message);
        }
      }
    )
  }

  sendData() {
    const request = new ResultRequest();
    request.x = Number(this.x_select);
    request.y = this.y_select;
    request.r = Number(this.r_select);
    this.sendCheckRequest(request);
  }

  sendDataGraph(x: number, y: number) {
    const request = new ResultRequest();
    request.x = x;
    request.y = y;
    request.r = Number(this.r_select);
    this.sendCheckRequest(request);
  }

  sendCheckRequest(request : ResultRequest) {
    this.http.post<Result>(environment.backendURL + "/app/check-hit", request)
      .subscribe((res : Result) => {
          if (res) {
            this.results.push(res);
            drawPointXYRRes(res.request.x, res.request.y, res.request.r, res.result);
          }
        }
      )
  }

  @HostListener('window:onGraph', ['$event.detail'])
  onLogin(detail : GraphPoint) {
    this.sendDataGraph(detail.x, detail.y);
  }

  clearTable() {
    this.http.delete(environment.backendURL + "/app/check-hit").subscribe(
      {
        next: () => {
          this.results = []
        },

        error: (err) => {
          console.error(err);
          console.log(err.message);
        }
      }
    );
  }

  logout() {
    this.http.delete(environment.backendURL + "/app/logout").subscribe(() => {
      sessionStorage.removeItem('token');
      this.router.navigate(['']).then(r => {
        if (!r) {
          console.error("something went wrong...");
        }
      });
    });
  }

  onRChange() {
    drawGraphByR(Number(this.r_select));
  }

  enable_graph() {
    enable_graph();
  }
}
