
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { BehaviorSubject, Observable, of } from 'rxjs';
import { User } from '../_models';
import { Router } from '@angular/router';

import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';

import {ImageDetail} from 'src/app/_models/imagedetails';
 
@Injectable({ providedIn: 'root' })
export class ImageService { 
    constructor(private http: HttpClient){

    }

    uploadImage(imagedetails :ImageDetail){
       return this.http.post<ImageDetail>(`${environment.apiUrl}/uploadimage`, imagedetails);
    }

    getMyImages(){
        return this.http.get<ImageDetail[]>(`${environment.apiUrl}/getmyimages`);
    }
    downloadImage(id:any){
        return this.http.put<ImageDetail>(`${environment.apiUrl}/downloadimage`, {"id":id});
    }

    getHottestImages(){
        return this.http.get<ImageDetail[]>(`${environment.apiUrl}/gethottestimage`);
    }

    searchImage(text:any){
        return this.http.get<ImageDetail[]>(`${environment.apiUrl}/searchimage/${text}`);
    }
 
} 