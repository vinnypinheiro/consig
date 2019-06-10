import {LoginRequest} from '../login-request';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SHA256} from "../../utils/Sha256";


@Injectable()
export class LoginService {

  constructor(private http: HttpClient) {}

  login(user: LoginRequest){
    //user.password = new SHA256().hash(user.password);
    return this.http.post(`http://10.1.1.239:8080/api/auth`,user);
  }

}
