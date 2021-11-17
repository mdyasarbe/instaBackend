
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { BehaviorSubject, Observable, of } from 'rxjs';
import { User } from '../_models';
import { Router } from '@angular/router';

import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { Status } from '../_models/status';
 
@Injectable({ providedIn: 'root' })
export class AccountService { 
    private userSubject!: BehaviorSubject<User| null>;
    public user!: Observable<User| null>;
    
    private isloggedIn: boolean;
    private userName:string ="";

    public get userValue(): User | null {
        return this.userSubject.value;
    }
 
    constructor(private router: Router,
        private http: HttpClient) {
            let userval = localStorage.getItem('user');
            if(userval){
                this.userSubject = new BehaviorSubject<User | null >(JSON.parse(userval));
                this.user = this.userSubject.asObservable();
            }else{
                this.userSubject = new BehaviorSubject<User | null >(null);
                this.user = this.userSubject.asObservable();
            }
                 
        this.isloggedIn=false;
    }
 
    login(username: string, password:string) {
        return this.http.post<Status>(`${environment.apiUrl}/signin`, { "userName":username, "userPassword":password })
        .pipe(map(result => {
            let temp = result.response;
            let user = new User();
            user = temp.user;
            user.token = temp.jwtToken;
            localStorage.setItem('user', JSON.stringify(user));
            this.userSubject.next(user);
            return result;
        }));
    }

    register(userdetails:User){
        console.log(userdetails);
        return this.http.post<Status>(`${environment.apiUrl}/signup`, userdetails);
    }
 
    isUserLoggedIn(): boolean {
        return this.isloggedIn;
    }
    
    logout(value:any) {
        // remove user from local storage and set current user to null
        localStorage.removeItem('user');
        this.userSubject = value;
        this.router.navigate(['/login']);
        window.location.reload();
    }
 
} 