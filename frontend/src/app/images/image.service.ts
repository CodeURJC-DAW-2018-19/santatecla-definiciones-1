import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class ImageService {
  apiUrl = environment.baseUrl;

  constructor(private http: HttpClient) {}

  getImage(conceptId: number) {
    return this.http.get(this.apiUrl + "/concepts/" + conceptId + "/image", {
      responseType: "blob"
    });
  }

  postImage(image: Blob, conceptId: number) {
    const formData: FormData = new FormData();
    formData.append("file", image);
    return this.http.post(
      this.apiUrl + "/concepts/" + conceptId + "/image",
      formData
    );
  }

  createImageFromBlob(image: Blob, callback: (arg0: string | ArrayBuffer) => void) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
       callback(reader.result);
    }, false);
 
    if (image) {
       reader.readAsDataURL(image);
    }
 }
}
