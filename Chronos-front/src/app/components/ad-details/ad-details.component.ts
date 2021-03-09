import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ad } from 'src/app/model/ad-interfaces';
import { AdServiceService } from 'src/app/services/ad-service.service';

@Component({
  selector: 'app-ad-details',
  templateUrl: './ad-details.component.html',
  styleUrls: ['./ad-details.component.css']
})
export class AdDetailsComponent implements OnInit {

  ad!: Ad;

  constructor( 
    private adService: AdServiceService,
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    const adId: number = Number(this.activatedRoute.snapshot.paramMap.get('adId'));
   this.adService.getAdById(adId).subscribe((data)=>{
    this.ad = data;
   })
  }
}
