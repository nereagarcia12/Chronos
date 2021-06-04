import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Ad } from 'src/app/model/ad-interfaces';
import { Favorite } from 'src/app/model/favorite-interface';
import { FavoritesService } from 'src/app/services/favorites.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-ad-card',
  templateUrl: './ad-card.component.html',
  styleUrls: ['./ad-card.component.css']
})
export class AdCardComponent implements OnInit {

  @Input() ad!: Ad;
  @Input() favorites: Favorite[] = []
  isAdFavorite: boolean = false
  showAd: boolean = true
  userLogged!: any;
  isLoggedIn: boolean  = false;

  constructor(private favoriteService: FavoritesService,
    private tokenStorage: TokenStorageService,
    private router: Router) {
      if (this.tokenStorage.getToken()) {
        this.isLoggedIn = true;
        this.userLogged = this.tokenStorage.getUser();
      }
     }

  ngOnInit(): void {
    if(this.isLoggedIn && this.favorites.length > 0){
      const favorite = this.favorites.find( favorite => favorite.adId == this.ad.id);
      if(favorite!=undefined){
        this.isAdFavorite = true;
      }
    }
  }

  private isFavoritePage() : Boolean {
    return this.router.url == '/favorites' && this.isLoggedIn;
  }

  saveFavorite(): void{
    this.favoriteService.saveFavorite({adId: this.ad.id, userId: this.userLogged.id})
    .subscribe((value) => {this.isAdFavorite = true});
  }

  deleteFavorite(): void{
    this.favoriteService.deleteFavorite(this.ad.id, this.userLogged.id)
    .subscribe((value) => {this.isAdFavorite = false
    this.showAd = false});
  }

}
