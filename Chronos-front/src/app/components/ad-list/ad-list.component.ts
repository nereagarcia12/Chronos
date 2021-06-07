import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ad, Category } from 'src/app/model/ad-interfaces';
import { Favorite } from 'src/app/model/favorite-interface';
import { AdServiceService } from 'src/app/services/ad-service.service';
import { FavoritesService } from 'src/app/services/favorites.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-ad-list',
  templateUrl: './ad-list.component.html',
  styleUrls: ['./ad-list.component.css']
})
export class AdListComponent implements OnInit {

  ads!: Ad[];
  pageOfAds: Array<Ad> = [];
  userLogged!: any;
  isLoggedIn: boolean  = false;
  categories: Category[] =[]
  selectedCategory = null
  favorites: Favorite[] = []

  form: any = {
    word: null,
  };

  constructor(private adService: AdServiceService,
    private favoriteService: FavoritesService,
    private tokenStorage: TokenStorageService,
    private activatedRoute: ActivatedRoute,
    private router: Router) { 
    this.checkFavorites();
    const word: string = String(this.activatedRoute.snapshot.paramMap.get('word'));
    const category: number = Number(this.activatedRoute.snapshot.paramMap.get('category'));

    if(word != "null"){
      this.adService.filterAds(word).subscribe((data) => {
        if(this.isFavoritePage()){
          const ads = data.filter((ad) => this.favorites.find(((favorite)=> favorite.adId == ad.id)) != undefined);
          this.ads = ads;
          this.pageOfAds = ads;
        } else {
          this.ads = data;
          this.pageOfAds = data;
        }
      }
      )
    } else if(category != 0){
      this.adService.filterAds(undefined,category).subscribe((data) => {
        if(this.isFavoritePage()){
          const ads = data.filter((ad) => this.favorites.find(((favorite)=> favorite.adId == ad.id)) != undefined);
          this.ads = ads;
          this.pageOfAds = ads;
        } else {
          this.ads = data;
          this.pageOfAds = data;
        }
      }
      )
    } else {
      this.adService.filterAds().subscribe((data) => {
        if(this.isFavoritePage()){
          const ads = data.filter((ad) => this.favorites.find(((favorite)=> favorite.adId == ad.id)) != undefined);
          this.ads = ads;
          this.pageOfAds = ads;
        } else {
          this.ads = data;
          this.pageOfAds = data;
        }
      }
      )
    }
    this.selectCategories()
  }

  public isFavoritePage() : Boolean {
    return this.router.url == '/favorites' && this.isLoggedIn;
  }

  private checkFavorites() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.userLogged = this.tokenStorage.getUser();
      this.favoriteService.getFavoriteByUser(this.userLogged.id).subscribe((userFavorites) => {
        this.favorites = userFavorites;
      }
      );
    }
  }

  ngOnInit(): void {
  }

  selectCategories(): void{
    this.categories = [];
    this.adService.getCategories().subscribe((dataCategories)=>{
      this.categories = dataCategories;
    })
  }

  selectCategory(categoryId: number){
    this.adService.filterAds(undefined,categoryId).subscribe((data) => {
      this.ads = data;
    }
    )
  }

  search(){
    const { word } = this.form;
    this.adService.filterAds(word).subscribe((data) => {
      this.ads = data;
    }
    )
  }

  onChangePage(pageOfAds: Array<Ad>) {
    // update current page of items
    this.pageOfAds = pageOfAds;
}
}
