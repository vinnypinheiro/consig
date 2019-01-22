import {Observable} from "rxjs/index";
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {SharedService} from "../shared/shared.service";

@Injectable()
export class AuthGuard implements CanActivate {

  constructor( private router: Router,protected sharedService: SharedService) {

  }

  canActivate(
      route: ActivatedRouteSnapshot,
      state: RouterStateSnapshot): Observable<boolean> | boolean {
    if(this.sharedService.isLogged){
        return true;
    }
    this.router.navigate(['/login']);
    return false;
  }

}
