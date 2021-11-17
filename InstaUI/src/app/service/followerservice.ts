
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Status } from '../_models/status';
import { FollowerDetails } from '../_models/followerdetails';

@Injectable({ providedIn: 'root' })
export class FollowerService { 
    constructor(private http: HttpClient){

    }

    

    getFollowers(){
        return this.http.get<FollowerDetails[]>(`${environment.apiUrl}/getfollower`);
    }
    addFollower(data:any){
        return this.http.post<Status>(`${environment.apiUrl}/addfollower`, {"id":data.id,"userName":data.userName});
    }
 
} 