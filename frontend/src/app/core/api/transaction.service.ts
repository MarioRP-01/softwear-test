import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Transaction } from '@app/shared/model/transaction';
import { TransactionType } from '@app/shared/model/data/transaction-type';

const BASE_URL = '/api/transactions'

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private httpClient: HttpClient) { }

  getSpecialTransactionId(transactionType: TransactionType): Observable<number[]> {

    let type: string = TransactionType[transactionType].toLowerCase();
    let url: string = BASE_URL + `/my?type=${type}`;

    return this.httpClient.get(url).pipe(
      map((transactions) => this.getIdsFromTransactions(transactions as Transaction[]))
    ) as Observable<number[]>;
  }

  getIdsFromTransactions(transactions: Transaction[]): number[] {

    return transactions.map(transaction => transaction.id);
  }

  addProductToTransaction(transactionId: number, productId:number): Observable<Transaction> {

    let url: string = BASE_URL + `/${transactionId}/products`;

    return this.httpClient.post(url, productId).pipe() as Observable<Transaction>
  }

  removeProductFromTransaction() {

  }
}


