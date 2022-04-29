import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { TransactionSpecialType } from '@app/shared/data-type';
import { catchError, map, Observable, of } from 'rxjs';
import { TransactionService } from '../api';
import { Wishlist } from '@app/shared/classes/wishlist';

@Injectable({
  providedIn: 'root'
})
export class WishlistResolver implements Resolve<Wishlist | null> {

  constructor(
    private transactionService: TransactionService
  ) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Wishlist | null> {
    
    let wishlistType = TransactionSpecialType.WISHLIST;
    return this.transactionService.getMySpecialTransactionByType(wishlistType).pipe(map(
      response => new Wishlist(response)
    ), catchError(
      err => {
        console.log(err)
        return of(null)
      }))
  }
}
