import { getAttrsForDirectiveMatching } from '@angular/compiler/src/render3/view/util';
import { Component, OnInit } from '@angular/core';
import { Ad } from 'src/app/model/ad-interfaces';
import { AdServiceService } from 'src/app/services/ad-service.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-my-ads',
  templateUrl: './my-ads.component.html',
  styleUrls: ['./my-ads.component.css']
})
export class MyAdsComponent implements OnInit {

  myAds: Ad[] = []
  pageOfAds!: Array<Ad>;
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
  this.adService.getAdsById(this.user.id).subscribe((data) =>
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
