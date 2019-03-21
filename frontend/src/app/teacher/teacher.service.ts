import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment";

export interface ConceptInfo{
    id?: number,
    conceptName?: string,
    URL?: string
}
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
    return this.http.get<ConceptInfo>(this.apiUrl + "/concepts/" + id, this.httpOptions);
  }
  updateConceptInfo(id:number, conceptInfo: ConceptInfo){
    return this.http.put<ConceptInfo>(this.apiUrl + "/concepts/" + id, conceptInfo, this.httpOptions);
  }
}
