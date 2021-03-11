import { Component, OnInit } from '@angular/core';
import { CompleteTransaction } from 'src/app/model/transaction-interfaces';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-transaction-received',
  templateUrl: './transaction-received.component.html',
  styleUrls: ['./transaction-received.component.css']
})
export class TransactionReceivedComponent implements OnInit {

  transactionsReceived : CompleteTransaction[] =[]
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
      this.transactionsReceived = data.filter(trans => trans.receiverUserId.id == this.user.id);
    } )
  }

  acceptTransaction(id:number) :void{
    this.transactionService.acceptTransaction(id, this.user.id).subscribe(() =>{
      this.reloadPage();
    })
  }
  refuseTransaction(id:number) :void{
    this.transactionService.refuseTransaction(id, this.user.id).subscribe(() =>{
      this.reloadPage();
    })
  }

  completeTransaction(id:number) : void{
    this.transactionService.completeTransaction(id, this.user.id).subscribe(() =>{
      this.reloadPage();
    })
  }

  reloadPage(): void {
    window.location.reload();
  }



}
