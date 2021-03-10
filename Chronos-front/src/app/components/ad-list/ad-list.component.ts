import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Ad, Category } from 'src/app/model/ad-interfaces';
import { AdServiceService } from 'src/app/services/ad-service.service';

@Component({
  selector: 'app-ad-list',
  templateUrl: './ad-list.component.html',
  styleUrls: ['./ad-list.component.css']
})
export class AdListComponent implements OnInit {

  ads!: Ad[];
  categories: Category[] =[]
  selectedCategory = null
  form: any = {
    word: null,
  };

  constructor(private adService: AdServiceService,
    private activatedRoute: ActivatedRoute) { 
    const word: string = String(this.activatedRoute.snapshot.paramMap.get('word'));

    if(word != "null"){
      this.adService.filterAds(word).subscribe((data) => {
        this.ads = data;
      }
      )
    } else {
      this.adService.filterAds().subscribe((data) => {
        this.ads = data;
      }
      )
    }

    this.selectCategories()
  }

  ngOnInit(): void {
  }

  selectCategories(): void{
    this.categories = [];
    this.adService.getCategories().subscribe((dataCategories)=>{
      this.categories = dataCategories;
    })
  }

  selectCategory(number: Number){

  }

  search(){
    const { word } = this.form;
    this.adService.filterAds(word).subscribe((data) => {
      this.ads = data;
    }
    )
  }

}
