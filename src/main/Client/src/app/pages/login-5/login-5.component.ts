import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';

declare var $:any;

@Component({
  selector: 'app-login-5',
  templateUrl: './login-5.component.html',
})
export class Login5Component implements OnInit, AfterViewInit, OnDestroy {

  constructor() { }

  ngOnInit() {
    $('body').addClass('empty-layout');
  }

  ngAfterViewInit() {
    $('#login-form').validate({
        errorClass: "help-block",
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true
            }
        },
        highlight: function(e) {
            $(e).closest(".form-group").addClass("has-error")
        },
        unhighlight: function(e) {
            $(e).closest(".form-group").removeClass("has-error")
        },
    });
  }

  ngOnDestroy() {
    $('body').removeClass('empty-layout');
  }

}
