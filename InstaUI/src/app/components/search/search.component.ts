import { Component, OnInit, ViewChild } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { AccountService } from '../../service/accountservice';
import { ImageService } from '../../service/imageservice';
import {ImageDetail} from 'src/app/_models/imagedetails';
import { AlertService } from '../../service/alert.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ImageList } from '../imagelist/imagelist.component';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  images!:ImageDetail[];
  username:string="username";
  searchtext = '';
  @ViewChild(ImageList) imagelist!:ImageList;
  constructor(private titleservice:Title
    ,private imageService:ImageService
    ,private accountService: AccountService
    ,private router:Router,private route:ActivatedRoute) {
     

      //this.searchtext=this.router.getCurrentNavigation()?.extras?.state?.searchText;
     }

  ngOnInit(): void {
    this.titleservice.setTitle ("Welcome "+this.accountService.userValue?.userFirstName|| 'user')
  }
  ngAfterViewInit(){

    this.route.queryParams.subscribe(x=>this.searchText(x.searchText));
  }
  searchText(text:any){
    console.log(text)
    this.imagelist.searchImage(text);

    
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
