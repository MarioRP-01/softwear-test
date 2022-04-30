import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Product, Transaction, PageableTransaction, Static } from '@app/shared/model';
import { TransactionType, TransactionSpecialType } from '@app/shared/data-type';

const BASE_URL = '/api/transactions'

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private httpClient: HttpClient) { }

  getMyTransactionsByType(transactionType: TransactionType, page: number): Observable<PageableTransaction> {

    let type: string = TransactionType[transactionType].toLowerCase();
    let url: string = BASE_URL + `/my?type=${type}&page=${page}`;

    return this.httpClient.get(url).pipe() as Observable<PageableTransaction>;
  }

  getMySpecialTransactionByType(transactionType: TransactionSpecialType): Observable<Transaction> {

    let type: string = TransactionSpecialType[transactionType].toLowerCase();
    let url: string = BASE_URL + `/my?type=${type}`;

    return this.httpClient.get(url).pipe(
      map(transactions => this.getFirstTransaction(transactions as PageableTransaction))
    ) as Observable<Transaction>;
  }

  getFirstTransaction(pageableTransaction: PageableTransaction): Transaction {

    let transaction: Transaction = pageableTransaction.transactions?.[0];
    return transaction
  }

  addProductByAmountToMyTransaction(productId:number, type: TransactionSpecialType, amount: number): Observable<Product> {

    let transactionType: string = TransactionSpecialType[type].toLowerCase();

    let url: string = BASE_URL + `/my/products?type=${transactionType}&amount=${amount}`;
    return this.httpClient.post(url, productId).pipe() as Observable<Product>;
  }

  processCart() {

    let url: string = BASE_URL + `/my`;
    return this.httpClient.post(url, null).pipe() as Observable<Transaction>;
  }

  deleteProductByAmountFromMyTransaction(productId: number, type: TransactionSpecialType, amount: number): Observable<Product> {

    let transactionType: string = TransactionSpecialType[type].toLowerCase();

    let url: string = BASE_URL + `/my/products/${productId}?type=${transactionType}&amount=${amount}`;
    return this.httpClient.delete(url).pipe() as Observable<Product>;
  }

  deleteProductFromMyTransaction(productId: number, type: TransactionSpecialType): Observable<Product> {

    let transactionType: string = TransactionSpecialType[type].toLowerCase();

    let url: string = BASE_URL + `/my/products/${productId}?type=${transactionType}`;
    return this.httpClient.delete(url).pipe() as Observable<Product>;
  }

  deleteAllProductsFromMyTransaction(type: TransactionSpecialType): Observable<Transaction> {

    let transactionType: string = TransactionSpecialType[type].toLowerCase();

    let url: string = BASE_URL + `/my/products?type=${transactionType}`;
    return this.httpClient.delete(url).pipe() as Observable<Transaction>;
  }

  getStatics(): Observable<Static[]> {

    let filter = "statics";
    
    let url: string = BASE_URL + `?filter=${filter}`
    return this.httpClient.get(url).pipe() as Observable<Static[]>
  }

}


