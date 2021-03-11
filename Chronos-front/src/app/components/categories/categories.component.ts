import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/model/ad-interfaces';
import { AdServiceService } from 'src/app/services/ad-service.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  categories: Category[] = []

  constructor(private adService: AdServiceService) {
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
