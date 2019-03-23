import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Page } from '../page/page.model';
import { Diagram } from './diagram.model';

@Injectable()
export class DiagramService {
  //TODO: Change this when login's done
  httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json",
      Authorization: "Basic " + btoa("user:pass")
    })
  };
  constructor(private http: HttpClient) {}

  getDiagram() {
      return this.http.get<Page<Diagram>>("", this.httpOptions);
  }
}
