import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Page } from "../page/page.model";
import { Diagram } from "./diagram.model";
import { LoginService } from '../login/login.service';

@Injectable()
export class DiagramService {
  apiUrl = environment.baseUrl;

  constructor(private http: HttpClient, public loginService: LoginService) { }

  //Backend is responsible for issuing the correct diagram by user
  getDiagram() {
    return this.http.get<Page<Diagram>>(this.apiUrl + "/diagraminfo", { withCredentials: true });
  }
}
