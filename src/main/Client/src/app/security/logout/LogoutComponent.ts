import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {SharedService} from "../../shared/shared.service";

@Component({
  template: ''
})

export class LogoutComponent implements OnInit {

  constructor(private router: Router,
              private sharedService: SharedService) {}

  ngOnInit() {
    localStorage.clear();
    this.sharedService.updateLoginDetails();
    this.router.navigate(['login']);
  }

}
