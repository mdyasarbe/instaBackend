import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AccountService } from './service/accountservice';
import { User } from './_models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  user!: User | null;

    constructor(private accountService: AccountService,private titleservice:Title) {
      let title = 'App';
      if(this.accountService.userValue){
        title = "Welcome" + this.accountService.userValue.userFirstName;
      }
        this.accountService.user.subscribe(x =>{ this.titleservice.setTitle (title)
           this.user = x});
    }
}

/*
import { initializeApp } from "firebase/app";

// TODO: Add SDKs for Firebase products that you want to use

// https://firebase.google.com/docs/web/setup#available-libraries


// Your web app's Firebase configuration

const firebaseConfig = {

  apiKey: "AIzaSyDVFnkJi_fGI0yeFpD8rnUHEPhjLwzqaV0",

  authDomain: "insta.firebaseapp.com",

  projectId: "insta",

  storageBucket: "insta.appspot.com",

  messagingSenderId: "896711331688",

  appId: "1:896711331688:web:5b7f01fe6ebce85a64d0dd"

};


// Initialize Firebase

const app = initializeApp(firebaseConfig);


service firebase.storage {
  match /b/{bucket}/o {
    match /{allPaths=**} {
      allow read, write: if request.auth != null;
    }
  }
}

*/
