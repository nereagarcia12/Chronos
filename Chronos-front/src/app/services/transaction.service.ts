import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CompleteTransaction, CreateTransaction, Transaction } from '../model/transaction-interfaces';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  readonly url = 'http://localhost:8083';

  constructor(private http: HttpClient) { }


  postTransaction(body: CreateTransaction): Observable <Transaction>{
    return this.http.post<Transaction>(this.url+'/transaction', body);
  }

  getTransactionByUserId(id: number): Observable<CompleteTransaction[]>{
    return this.http.get<CompleteTransaction[]>(this.url + '/transactions/' + id);
  }

}
