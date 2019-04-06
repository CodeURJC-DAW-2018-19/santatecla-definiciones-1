import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Justification } from "./justification.model";

import { Page } from "../page/page.model";
import { LoginService } from "../login/login.service";
import { environment } from "../../environments/environment";

const BASE_URL = environment.baseUrl;

//It will be necessary to access the user information by a new service

@Injectable()
export class JustificationService {
   constructor(private http: HttpClient, private loginService: LoginService) { }

   apiUrl = environment.baseUrl;
   headers = new HttpHeaders({
      "Content-Type": "application/json"
    });
   getUnmarkedJustifications(id: number, page: number) { //This methods gets us the answers that have at least 1
      return this.http.get<Page<Justification>>(
         this.apiUrl + "/concepts/" + id + "/unmarkedjustifications" + "?page=" + page,
         { withCredentials: true }
      );
   }

   getMarkedJustificationsByAnswer(conceptId: number, answerId: number, page: number){
      return this.http.get<Page<Justification>>(
         this.apiUrl + "/concepts/" + conceptId + "/answers/" + answerId + "/markedjustifications" + "?page=" + page,
         { withCredentials: true }
      );
   }

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

   postNewJustification(id: number, just: Justification) {
      return this.http.post(
         this.apiUrl + "/answers/" + id + "/justifications",
         just,
         { withCredentials: true }
      );
   }
   editJustification(answerId: number, justificationText: string, correct: boolean) {
      let body = {
         justificationText: justificationText,
         correct: correct
       };
       return this.http.put(
         this.apiUrl + "/answers/" + answerId+ "/justifications",
         body,
         { headers: this.headers, withCredentials: true }
   );
   }  

   markJustification(answerId, justId, valid, errorText?) {
      let body = {
         valid: valid
      }
      if (errorText) {
         body["errorText"] = errorText
      }
      return this.http.put(
         this.apiUrl + "/answers/" + answerId + "/correct/" + justId, body, {headers: this.headers, withCredentials:true}
      )
   }
}