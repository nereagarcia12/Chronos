import { getAttrsForDirectiveMatching } from '@angular/compiler/src/render3/view/util';
import { Component, OnInit } from '@angular/core';
import { Ad } from 'src/app/model/ad-interfaces';
import { AdServiceService } from 'src/app/services/ad-service.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-admin-ad',
  templateUrl: './admin-ad.component.html',
  styleUrls: ['./admin-ad.component.css']
})
export class AdminAdComponent implements OnInit {


  myAds: Ad[] = []
  pageOfAds: Array<Ad> = [];
  isLoggedIn = false;
  user!: any;


  constructor(private adService: AdServiceService,
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.user = this.tokenStorage.getUser();
  }
  this.getAds();
}

getAds():void{
  this.adService.filterAds().subscribe((data) =>
  this.myAds = data)
}

deleteAd(id: number){
  this.adService.deleteAd(id).subscribe(()=> {
    this.reloadPage();
  })
}

reloadPage(): void {
  window.location.reload();
}

onChangePage(pageOfAds: Array<Ad>) {
  this.pageOfAds = pageOfAds;
}

}
