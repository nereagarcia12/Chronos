import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ad } from 'src/app/model/ad-interfaces';
import { User } from 'src/app/model/user-interface';
import { AdServiceService } from 'src/app/services/ad-service.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-ad-details',
  templateUrl: './ad-details.component.html',
  styleUrls: ['./ad-details.component.css']
})
export class AdDetailsComponent implements OnInit {

  ad!: Ad;
  user!: User;

  constructor( 
    private adService: AdServiceService,
    private userService : UserService,
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    const adId: number = Number(this.activatedRoute.snapshot.paramMap.get('adId'));
   this.adService.getAdById(adId).subscribe((data)=>{
    this.ad = data;
    this.userService.getUserByid(this.ad.userId).subscribe((userData)=>{
      this.user = userData;
    })
   })
  }
}
