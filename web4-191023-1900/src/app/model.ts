export class Model {

}

export class PostResponse {
    message : string;

    constructor() {
        this.message = "";
    }
}

export class LoginRequest {
    username: String;
    password: String;

    constructor() {
      this.username = "";
      this.password = "";
    }
}

export class AuthTokenResponse {
    sessionID: string;

    constructor() {
      this.sessionID = "";
    }
}
