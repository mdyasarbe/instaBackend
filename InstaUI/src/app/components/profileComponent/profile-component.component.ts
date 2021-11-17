import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AccountService } from '../../service/accountservice';
import { ImageService } from '../../service/imageservice';
import {ImageDetail} from 'src/app/_models/imagedetails';
import { AlertService } from '../../service/alert.service';

@Component({
  selector: 'app-profile-component',
  templateUrl: './profile-component.component.html',
  styleUrls: ['./profile-component.component.css']
})
export class ProfileComponent implements OnInit {
  images!:ImageDetail[];
  downloadCount=0;
  username:string="username";
  constructor(private titleservice:Title
    ,private imageService:ImageService
    ,private accountService: AccountService
    ,private alertService:AlertService) { }

  ngOnInit(): void {
    this.titleservice.setTitle ("Welcome "+this.accountService.userValue?.userFirstName|| 'user')
  }
  // ngAfterViewInit(){
  //  this.imageService.getMyImages().subscribe(
  //     data => {
  //       this.images=data;
  //     },
  //     error => {
  //       console.log(error);
  //     });
  // }
  // downloadImage(id:any){
  //   let arrayind = this.images.findIndex(x=>x.id==id);
  //   this.imageService.downloadImage(id).subscribe(
  //     data => {
  //       this.images[arrayind]=data;
  //     },
  //     error => {
  //       this.alertService.error(error.message);
  //       console.log(error);
  //     });
  // }
    
}
