import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { TransactionSpecialType } from '@app/shared/data-type';
import { Transaction } from '@app/shared/classes';
import { map, Observable, of } from 'rxjs';
import { TransactionService } from '../api';

@Injectable({
  providedIn: 'root'
})
export class WishlistResolver implements Resolve<Transaction> {

  constructor(
    private transactionService: TransactionService
  ) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Transaction> {
    
    let wishlistType = TransactionSpecialType.WISHLIST;
    return this.transactionService.getMySpecialTransactionByType(wishlistType).pipe(map(
      response => new Transaction(response),
      (error: any) => {
        console.log(error);
        return null;
      }
    ))
  }
}
