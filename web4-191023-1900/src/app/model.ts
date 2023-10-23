export class Model {

}

export class PostResponse {
    message : string;

    constructor() {
        this.message = "";
    }
}

export class LoginRequest {
    username: string;
    password: string;

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

export class ResultRequest {
    x : number;
    y : number;
    r : number;

    constructor() {
      this.x = 0;
      this.y = 0;
      this.r = 0;
    }
}

export class Result {
    request : ResultRequest;
    result : boolean;
    executedAt : Date;
    executionTime : number;

    constructor() {
      this.request = new ResultRequest();
      this.result = false;
      this.executedAt = new Date();
      this.executionTime = 0;
    }
}
