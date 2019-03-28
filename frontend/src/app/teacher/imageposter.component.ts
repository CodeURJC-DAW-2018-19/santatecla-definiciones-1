import { Component } from "@angular/core";
import { ImageService } from "../images/image.service";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "imageposter",
  templateUrl: "./imageposter.component.html",
  styleUrls: ["./imageposter.component.css"]
})
export class ImagePosterComponent {
  conceptId: number;
  image: any;

  constructor(
    public imageService: ImageService,
    private activatedRoute: ActivatedRoute
  ) {
    this.conceptId = activatedRoute.snapshot.params["id"];
    this.loadImage();
  }

  uploadEvent(file: File): void {
    this.imageService.postImage(file, this.conceptId).subscribe(
        (data) => {
            this.loadImage();
        },
        (error: Error) => this.loadImage(),
    );
  }

  loadImage(){
    this.imageService.getImage(this.conceptId).subscribe(
        (data: Blob) => this.imageService.createImageFromBlob(data, ((image) => this.image = image)),
        error => console.log(error)
    )
  }
}
