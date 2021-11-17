import { Component, OnInit, Input } from '@angular/core';
import { AccountService } from '../../service/accountservice';
import { AlertService } from '../../service/alert.service';
import { ImageService } from '../../service/imageservice';
import { ImageDetail } from '../../_models';

@Component({
    selector: 'app-imagelist',
    templateUrl: './imagelist.component.html',
    styleUrls: ['./imagelist.component.css']
})
export class ImageList implements OnInit {

    images!:ImageDetail[];
    loading:boolean = true;
    @Input() page = "profile";
    
    @Input() text = "";
    constructor(private imageService: ImageService
        , private accountService: AccountService
        , private alertService: AlertService) { }

    ngOnInit(): void {
    }
    ngAfterViewInit() {
        if (this.page == "profile") {
            this.loading= true;
            this.imageService.getMyImages().subscribe(
                data => {
                    this.images = data;
                    
                    this.loading= false;
                },
                error => {
                    console.log(error);
                    this.loading= false;
                });
        } else if(this.page == "hottestimage"){

            this.loading= true;
            this.imageService.getHottestImages().subscribe(
                data => {
                    this.images = data;
                    this.loading= false;
                },
                error => {
                    console.log(error);
                    this.loading= false;
                });
            
           
        }

    }

    searchImage(text:any){

        this.loading= true;
        this.imageService.searchImage(text).subscribe(
            data => {
                this.images = data;
                this.loading= false;
            },
            error => {
                console.log(error);
                this.loading= false;
            });

    }
    downloadImage(id: any) {
        let arrayind = this.images.findIndex(x => x.id == id);
        this.imageService.downloadImage(id).subscribe(
            data => {
                this.images[arrayind] = data;
            },
            error => {
                this.alertService.error(error.message);
                console.log(error);
                this.loading= false;
            });
    }

}