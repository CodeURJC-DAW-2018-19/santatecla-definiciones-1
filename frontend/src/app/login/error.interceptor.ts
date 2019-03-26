import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LoginService } from './login.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
 
    constructor(private loginService: LoginService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log("Error intercepted")
        return next.handle(request).pipe(catchError(err => {
            
            if (err.status === 401) {
                console.log(this.loginService.isLogged)
                // auto logout if 401 response returned from api
                this.loginService.removeCurrentUser();
                //location.reload(true);
                console.log(this.loginService.isLogged)

            }
            
            return throwError(err);
        }))
    }
}