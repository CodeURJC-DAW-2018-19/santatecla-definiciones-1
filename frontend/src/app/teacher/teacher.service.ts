import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Concept } from '../home/concept.model';

/**
 * Service for teacher requests to backend
 */
@Injectable()
export class TeacherService {
  constructor(private http: HttpClient) { }
  apiUrl = environment.baseUrl;

  getConceptInfo(id: number) {
    return this.http.get<Concept>(this.apiUrl + "/concepts/" + id, { withCredentials: true });
  }
  updateConceptInfo(id: number, conceptInfo: Concept) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.put<Concept>(this.apiUrl + "/concepts/" + id, conceptInfo, { headers: headers});
  }
}
