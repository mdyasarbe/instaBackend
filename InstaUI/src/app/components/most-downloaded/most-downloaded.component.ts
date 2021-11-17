import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-most-downloaded',
  templateUrl: './most-downloaded.component.html',
  styleUrls: ['./most-downloaded.component.css']
})
export class MostDownloadedComponent implements OnInit {

  images:any=[];
  constructor() { }

  ngOnInit(): void {
    this.images=[
      {image:"assets/blog-img212.jpg"},
      {image:"assets/blog-img218.jpg"},
      {image:"assets/blog-img220.jpg"},
      {image:"assets/blog-img219.jpg"},
      {image:"assets/blog-img211.jpg"},
      {image:"assets/blog-img222.jpg"}
    ]
  }

}
