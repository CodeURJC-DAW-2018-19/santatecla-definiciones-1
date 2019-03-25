import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Concept } from '../home/concept.model';

/**
 * Service for teacher requests to backend
 */
@Injectable()
export class TeacherService {
  constructor(private http: HttpClient) {}
  httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
      //TODO: Change this when login's done
      Authorization: "Basic " + btoa("admin:adminpass")
    })
  };
  apiUrl = environment.baseUrl;

  getConceptInfo(id: number) {
    return this.http.get<Concept>(this.apiUrl + "/concepts/" + id, this.httpOptions);
  }
  updateConceptInfo(id:number, conceptInfo: Concept){
    return this.http.put<Concept>(this.apiUrl + "/concepts/" + id, conceptInfo, this.httpOptions);
  }
}
