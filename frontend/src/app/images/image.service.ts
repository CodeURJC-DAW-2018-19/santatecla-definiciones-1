import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ImageService {
  apiUrl = environment.baseUrl;

  constructor(private http: HttpClient) {}

  getImage(conceptId: number) {
    return this.http.get(this.apiUrl + "/concepts/" + conceptId + "/image", {
      responseType: "blob"
    });
  }
}