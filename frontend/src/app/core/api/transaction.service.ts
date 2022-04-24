import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Transaction } from '@app/shared/model';
import { TransactionType } from '@app/shared/data-type/transaction-type';
import { PageableTransaction } from '@app/shared/model';

const BASE_URL = '/api/transactions'

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private httpClient: HttpClient) { }

  getSpecialTransactionId(transactionType: TransactionType): Observable<Transaction> {

    let type: string = TransactionType[transactionType].toLowerCase();
    let url: string = BASE_URL + `/my?type=${type}`;

    return this.httpClient.get(url).pipe(
      map(transactions => this.getFirstTransaction(transactions as PageableTransaction))
    ) as Observable<Transaction>;
  }

  getFirstTransaction(pageableTransaction: PageableTransaction): Transaction {

    let transaction: Transaction = pageableTransaction.transactions?.[0];
    return transaction
  }

  addProductToTransaction(transactionId: number, productId:number): Observable<Transaction> {

    let url: string = BASE_URL + `/${transactionId}/products`;

    return this.httpClient.post(url, productId).pipe() as Observable<Transaction>
  }

  removeProductFromTransaction() {

  }
}


