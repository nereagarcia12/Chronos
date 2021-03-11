import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Ad, Category, CreateAd } from 'src/app/model/ad-interfaces';
import { AdServiceService } from 'src/app/services/ad-service.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-ad-edit',
  templateUrl: './ad-edit.component.html',
  styleUrls: ['./ad-edit.component.css']
})
export class AdEditComponent implements OnInit {

  categories: Category[] =[]
  ad!: Ad;
  form: FormGroup;
  title: FormControl;
  availability: FormControl;
  category: FormControl;
  description: FormControl;
  isLoggedIn = false;
  user!: any;

  constructor(private adService: AdServiceService,
    private tokenStorage: TokenStorageService,
    private activatedRoute: ActivatedRoute, private route : Router) { 
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
      this.getAd()
    }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.user = this.tokenStorage.getUser();
  }
}

  getAd(): void{
    const adId: number = Number(this.activatedRoute.snapshot.paramMap.get('adId'));
    this.adService.getAdById(adId).subscribe((data)=>{
      this.ad = data;
      this.form.patchValue({
        title: this.ad.title,
        description : this.ad.description,
        availability: this.ad.availability,
        category: this.categories.filter(category => category.name == this.ad.categoryName)[0].id
      }) 
    })
  }


  selectCategories(): void{
    this.categories = [];
    this.adService.getCategories().subscribe((dataCategories)=>{
      this.categories = dataCategories;
    })
  }

  onSubmit():void{
    let ad = {title: this.title.value, description: this.description.value, availability: this.availability.value, categoryId: this.category.value, userId: this.user.id } as CreateAd
    this.adService.editAd(this.ad.id,ad).subscribe( (data) => {
      this.route.navigate(['myAds'])
    })
  }

}
