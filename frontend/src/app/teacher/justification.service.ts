import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Justification } from "./justification.model";
import { LoginService } from "../login/login.service";
import { environment } from "../../environments/environment";

const BASE_URL = environment.baseUrl;

//It will be necessary to access the user information by a new service

@Injectable()
export class JustificationService {
  constructor(private http: HttpClient, private loginService: LoginService) {}

  apiUrl = environment.baseUrl;

   removeJustification(justId: number, answerId: number): Observable<Justification> {
      return this.http
          .delete<Justification>(this.apiUrl + '/answers/' + answerId + '/justifications/' + justId)
          .pipe(
            catchError((error: any) => {
               if (error.status === 400) {
                  return throwError(error.status);
               }
               else {
                  this.handleError(error);
               }
           }));
   }
   

   private handleError(error: any) {
      console.error(error);
      return throwError(new Error('Server error (' + error.status + '): ' + error));
   }
   
   postNewJustification(id: number, just: Justification){
    return this.http.post(
        this.apiUrl   + "/answers/" + id + "/justifications", 
        just,
        { withCredentials: true }
    );
  }
}