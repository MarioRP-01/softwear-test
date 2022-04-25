import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Product, Transaction } from '@app/shared/model';
import { TransactionType } from '@app/shared/data-type/transaction-type';
import { PageableTransaction } from '@app/shared/model';
import { TransactionSpecialType } from '@app/shared/data-type';

const BASE_URL = '/api/transactions'

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private httpClient: HttpClient) { }

  getMyTransactionByType(transactionType: TransactionType): Observable<Transaction> {

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

  addProductToMyTransaction(productId:number, type: TransactionSpecialType): Observable<Product> {

    let transactionType: string = TransactionSpecialType[type].toLowerCase();

    let url: string = BASE_URL + `/my/products?type=${transactionType}`;
    return this.httpClient.post(url, productId).pipe() as Observable<Product>;
  }

  deleteProductFromMyTransaction(productId:number, type: TransactionSpecialType): Observable<Product> {

    let transactionType: string = TransactionSpecialType[type].toLowerCase();

    let url: string = BASE_URL + `/my/products/${productId}?type=${transactionType}`;
    return this.httpClient.delete(url).pipe() as Observable<Product>;
  }
}


