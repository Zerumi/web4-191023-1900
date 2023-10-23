import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {AuthTokenResponse, LoginRequest, PostResponse} from "../model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {

  model : LoginRequest = new LoginRequest();
  sessionID = '';

  constructor(private router: Router,
    private http: HttpClient) { }
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

  login() {
    let url = environment.backendURL + '/app/login';
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
