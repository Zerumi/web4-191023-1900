import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AuthTokenResponse, LoginRequest} from "../model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DataComponent implements OnInit, OnDestroy {

  time = new Date();
  intervalId : any;

  model : LoginRequest = new LoginRequest();
  sessionID = '';

  constructor(private router: Router,
    private http: HttpClient) {
  }
  ngOnInit() {
    // Using Basic Interval
    this.intervalId = setInterval(() => {
      this.time = new Date();
    }, 1000);
  }

  ngOnDestroy() {
    clearInterval(this.intervalId);
  }

  login() {
    const url = environment.backendURL + '/app/login';
    this.proceedAuthRequest(url);
  }

  register() {
    const url = environment.backendURL + '/app/register';
    this.proceedAuthRequest(url);
  }

  proceedAuthRequest(url : string) {
    this.http.post<AuthTokenResponse>(url, {
      username: this.model.username,
      password: this.model.password
    })
      .subscribe(res => {
          if (res) {
            this.sessionID = res.sessionID;

            sessionStorage.setItem(
              'token',
              this.sessionID
            );

            this.router.navigate(['main']).then(r => {
              if (!r) {
                console.error("something went wrong...");
              }
            });
          } else {
            console.error("auth failed");
          }
        }
      )
  }
}
