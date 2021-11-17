import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../../service/accountservice';

import { ImageService } from '../../service/imageservice';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  searchText="";

  constructor(private accountService: AccountService,private imageservice:ImageService, private route: Router) { }

  ngOnInit(): void {
  }
  onSearch(){
    //this.route.navigate([`/search/${this.searchText}`],{state:{searchText:this.searchText}});
    this.route.navigate([`/search`],{queryParams:{searchText:this.searchText}});
  }
  logout(){
    let k= null;
    this.accountService.logout(k);
  }

}
