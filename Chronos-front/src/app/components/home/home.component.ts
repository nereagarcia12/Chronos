import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  form: any = {
    word: null,
  };

  constructor(private route: Router) { }

  ngOnInit(): void {
  }

  search(){
    const { word } = this.form;   
    this.route.navigate(['/listing/'+word])
  }

}
