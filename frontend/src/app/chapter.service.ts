import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Chapter } from './chapter.model';


export interface ChaptertInfo{
    id?: number,
    chapterName?: string,
    URL?: string
}


@Injectable()
export class ChapterService{
        httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
        })
    }; 
    constructor(private http: HttpClient) { }
    search(id: number){
        return this.http.get<Chapter>("/api/chapters/", this.httpOptions);

    }
}