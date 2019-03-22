import { Component } from "@angular/core";
import { ChapterService } from './chapter.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Chapter } from "./chapter.model";

@Component({
    selector: 'main',
    templateUrl: './main.component.html'
})
export class ChapterComponent{
    id: number;
    chapters: Chapter[]= [];
    

    constructor (private router: Router, activatedRoute: ActivatedRoute, private chapterService: ChapterService){
        this.id = activatedRoute.snapshot.params['id'];
        this.search(this.id)

    }

    search(id: number){
        this.chapterService.search(id).subscribe(
            (data: Chapter) => this.chapters = [(name)],
            error => console.log(error)
        );
    }
}