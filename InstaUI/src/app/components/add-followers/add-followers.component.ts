import { Component, OnInit } from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { Observable, ReplaySubject } from 'rxjs';
import { FollowerDetails } from 'src/app/_models/followerdetails';
import { MatTableDataSource } from '@angular/material/table';
import { FollowerService } from 'src/app/service/followerservice';
import { User } from 'src/app/_models';

@Component({
  selector: 'app-add-followers',
  templateUrl: './add-followers.component.html',
  styleUrls: ['./add-followers.component.css']
})

export class AddFollowersComponent implements OnInit {

  displayedColumns: string[] = ['firstName', 'lastName', 'Add Followers'];
  loading = true;
  data:FollowerDetails[] =[];

  dataSource = new MatTableDataSource(Array());

  constructor(private followerservice: FollowerService) {

  }

  addFollower(data: any) {
    let index = this.data.findIndex(x=>x.id==data.id);
    this.followerservice.addFollower(data).subscribe(data => {
      this.loading = false;
      this.data[index].isFollowed = true;
      this.dataSource = new MatTableDataSource(this.data);

    }, error => {
      this.loading = false;
      console.log(error);
    });
  }
  ngOnInit(): void {
    this.loadData();
  }
  loadData() {
    this.loading = true;
    this.followerservice.getFollowers().subscribe(data => {
      this.data = data;
      this.loading = false;
      this.dataSource = new MatTableDataSource(data);


    }, error => {
      this.loading = false;
      console.log(error);
    });
  }
}
