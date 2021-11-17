import { Component, OnInit } from '@angular/core';
import {ThemePalette} from '@angular/material/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ImageDetail } from '../../_models';

@Component({
  selector: 'app-image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent implements OnInit {

  color: ThemePalette = 'accent';
  checked = false;
  form: any;
  
  submitted = false;

  constructor(private formbuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formbuilder.group({
      image: ['',Validators.required],
      title: ['',Validators.required],
      description: [''],
      tags: [''],
      pictureDetails: [''],
      visibility: [true],
    }) 
  }
  get f(){
    return this.form.controls;
  }

  onClickSubmit() {
    this.submitted = true;

    console.log(this.f.image);
     if(this.form.invalid){
       return;
     }

     let obj = new ImageDetail();
     obj.description = this.f.description.value;
     obj.url =this.f.image.value;
     obj.numofdownloads = 0;
     obj.pictureDetails = this.f.pictureDetails.value;
     obj.title = this.f.title.value;
     obj.tags = this.f.tags.value;
     obj.visibility = this.f.visibility.value;

     
     
    this.submitted = false;
    }
    imageclicked(event:any){
      this.form.patchValue({
        image : event.target.files[0].name
      })

    }

}
