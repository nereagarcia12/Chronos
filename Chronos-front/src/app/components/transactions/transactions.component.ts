import { Component, OnInit } from '@angular/core';
import { CompleteTransaction, Transaction } from 'src/app/model/transaction-interfaces';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  transactionsInitiated: CompleteTransaction[] =[]
  isLoggedIn = false;
  user!: any;


  constructor(private transactionService: TransactionService,
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.user = this.tokenStorage.getUser();

    }
    this.getTransaction();
  }

  getTransaction():void {
    this.transactionService.getTransactionByUserId(this.user.id).subscribe((data)=>{
      this.transactionsInitiated = data.filter(trans => trans.originUserId.id == this.user.id);
    } )
  }
  
  reloadPage(): void {
    window.location.reload();
  }

}
