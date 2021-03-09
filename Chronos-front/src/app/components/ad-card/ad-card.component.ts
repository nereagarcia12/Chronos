import { Component, OnInit, Input } from '@angular/core';
import { Ad } from 'src/app/model/ad-interfaces';

@Component({
  selector: 'app-ad-card',
  templateUrl: './ad-card.component.html',
  styleUrls: ['./ad-card.component.css']
})
export class AdCardComponent implements OnInit {

  @Input() ad!: Ad;
  
  constructor() { }

  ngOnInit(): void {
  }

}
