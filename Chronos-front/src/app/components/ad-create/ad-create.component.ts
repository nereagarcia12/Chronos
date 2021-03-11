import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Category, CreateAd } from 'src/app/model/ad-interfaces';
import { AdServiceService } from 'src/app/services/ad-service.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { AdListComponent } from '../ad-list/ad-list.component';

@Component({
  selector: 'app-ad-create',
  templateUrl: './ad-create.component.html',
  styleUrls: ['./ad-create.component.css']
})
export class AdCreateComponent implements OnInit {

  categories: Category[] =[]

  form: FormGroup;
  title: FormControl;
  availability: FormControl;
  category: FormControl;
  description: FormControl;
  isLoggedIn = false;
  user!: any;



  constructor(private adService: AdServiceService,
    private tokenStorage: TokenStorageService, private route: Router) { 
    this.title = new FormControl('', [Validators.required]);
    this.availability = new FormControl('', [Validators.required]);
    this.category = new FormControl('', [Validators.required]);
    this.description = new FormControl('', [Validators.required]);
    this.form = new FormGroup({
      title: this.title,
      availability: this.availability,
      category: this.category,
      description: this.description
    });
    this.selectCategories()
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.user = this.tokenStorage.getUser();
    }
  }

  selectCategories(): void{
    this.categories = [];
    this.adService.getCategories().subscribe((dataCategories)=>{
      this.categories = dataCategories;
    })
  }
 
  onSubmit():void{
    let ad = {title: this.title.value, description: this.description.value, availability: this.availability.value, categoryId: this.category.value, userId: this.user.id } as CreateAd
    this.adService.postAd(ad).subscribe( (data) => {
      this.route.navigate(['myAds'])
    })
  }

}
