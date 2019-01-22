import {Injectable} from '@angular/core';
import {HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";

@Injectable()
export class CustomHttpInterceptor implements HttpInterceptor {


  intercept(req: HttpRequest<any>, next: HttpHandler) {

   let token = localStorage.getItem("token");
    if (token) {
      req = req.clone({
        setHeaders: {
          'Authorization': token,
          'Content-type': 'application/json; charset=utf8'
        }
      });
    }
    return next.handle(req);

  }

}
