import {LoginService} from './login.service';
import {LoginRequest} from '../login-request';
import {Component, OnInit, AfterViewInit, OnDestroy} from '@angular/core';
import {Router} from "@angular/router";
import {LoginResponse} from "../login-response";
import {SharedService} from "../../shared/shared.service";
import {ToastrService} from "ngx-toastr";


declare var $:any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, AfterViewInit, OnDestroy {

  user = new LoginRequest('','',"","");
  message : string;

  constructor(private userService: LoginService,
              private sharedService: SharedService,
              private toastr: ToastrService,
              private router: Router) {
  }

  ngOnInit() {

    $('body').addClass('empty-layout');
  }

  ngAfterViewInit() {
    $('#login-form').validate({
      errorClass:"help-block",
      rules: {
        email: {required:true,email:true},
        password: {required:true}
      },
      highlight:function(e){$(e).closest(".form-group").addClass("has-error")},
      unhighlight:function(e){$(e).closest(".form-group").removeClass("has-error")},
    });
  }

  ngOnDestroy() {
    $('body').removeClass('empty-layout');
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
      this.toastr.error('Usuário ou senha incorretos', 'Erro   ', {
        positionClass: 'toast-top-center'
      });

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
