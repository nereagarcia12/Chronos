import { Component, OnInit } from '@angular/core';
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

  constructor( private adService: AdServiceService) { 
    this.adService.filterAds().subscribe((data) => {
      this.ads = data;
    }
    )
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

}
