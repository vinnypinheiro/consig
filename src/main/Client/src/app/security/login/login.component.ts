import {LoginService} from './login.service';
import {LoginRequest} from '../login-request';
import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LoginResponse} from "../login-response";
import {SharedService} from "../../shared/shared.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new LoginRequest('','',"","");
  message : string;

  constructor(private userService: LoginService,
              private sharedService: SharedService,
              private router: Router) {
  }

  ngOnInit() {
  }


  login(){
    this.message = '';
    this.userService.login(this.user).subscribe((loginResponse:LoginResponse) => {
        localStorage.setItem('token',loginResponse.token);
        localStorage.setItem('isLogged','true');
      localStorage.setItem('loginResponse',JSON.stringify(loginResponse));
      this.sharedService.updateLoginDetails();

        this.router.navigate(['/']);
    } , err => {
      localStorage.clear();
      this.sharedService.updateLoginDetails();
      this.message = 'Erro ';
    });
  }

  cancelLogin(){
    this.message = '';
    this.user = new LoginRequest('', '',"","");
    window.location.href = '/login';
    window.location.reload();
  }

  getFormGroupClass(isInvalid: boolean, isDirty:boolean): {} {
    return {
      'form-group': true,
      'has-error' : isInvalid  && isDirty,
      'has-success' : !isInvalid  && isDirty
    };
  }

}
