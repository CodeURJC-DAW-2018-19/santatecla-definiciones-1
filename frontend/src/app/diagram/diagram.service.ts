import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Page } from "../page/page.model";
import { Diagram } from "./diagram.model";

// TODO: Remake this class when login's done
@Injectable()
export class DiagramService {
  //TODO: Change this when login's done
  httpOptionsUser = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
      Authorization: "Basic " + btoa("user:pass")
    })
  };
  httpOptionsTeacher = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
      Authorization: "Basic " + btoa("admin:adminpass")
    })
  };
  apiUrl = environment.baseUrl;

  constructor(private http: HttpClient) {}

  getDiagramUser() {
    return this.http.get<Page<Diagram>>(this.apiUrl + "/diagraminfo", this.httpOptionsUser);
  }

  getDiagramTeacher() {
    return this.http.get<Page<Diagram>>(this.apiUrl + "/diagraminfo", this.httpOptionsTeacher);
  }
}
