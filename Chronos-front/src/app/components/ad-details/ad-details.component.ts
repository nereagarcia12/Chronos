import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Ad } from 'src/app/model/ad-interfaces';
import { Transaction } from 'src/app/model/transaction-interfaces';
import { User } from 'src/app/model/user-interface';
import { AdServiceService } from 'src/app/services/ad-service.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { TransactionService } from 'src/app/services/transaction.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-ad-details',
  templateUrl: './ad-details.component.html',
  styleUrls: ['./ad-details.component.css']
})
export class AdDetailsComponent implements OnInit {

  ad!: Ad;
  user!: User;
  userLogged!: any;
  isLoggedIn: boolean  = false;
  transaction: boolean = false;


  form: FormGroup;
  amount: FormControl;
  description: FormControl;
  serviceRequestFailed = false;
  errorMessage = '';

  constructor( 
    private adService: AdServiceService,
    private transactionService: TransactionService,
    private userService : UserService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private tokenStorage: TokenStorageService) { 
    this.amount = new FormControl('', [Validators.required]);
    this.description = new FormControl('', [Validators.required]);
    this.form = new FormGroup({
      amount: this.amount,
      description: this.description
    });
    }

  ngOnInit(): void {
    const adId: number = Number(this.activatedRoute.snapshot.paramMap.get('adId'));
   this.adService.getAdById(adId).subscribe((data)=>{
    this.ad = data;
    this.userService.getUserByid(this.ad.userId).subscribe((userData)=>{
      this.user = userData;
    })
   })
   if (this.tokenStorage.getToken()) {
    this.isLoggedIn = true;
    this.userLogged = this.tokenStorage.getUser();
  }
  }
  onSubmit():void{
    let transaction = {amount: this.amount.value, description: this.description.value, receiverUserId: this.user.id, originUserId: this.userLogged.id, adId: this.ad.id} 
    this.transactionService.postTransaction(transaction).subscribe( (data) => {
      this.transaction = true;
    },
    err => {
      this.errorMessage = err.error.error;
      this.serviceRequestFailed = true;
    })
  }

}
