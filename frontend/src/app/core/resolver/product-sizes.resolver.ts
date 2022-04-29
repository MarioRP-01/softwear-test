import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { ProductFilterToId } from '@app/shared/data-type';
import { Product } from '@app/shared/model';
import { catchError, map, Observable, of } from 'rxjs';
import { ProductService } from '../api';

@Injectable({
  providedIn: 'root'
})
export class ProductSizesResolver implements Resolve<Product[] | null> {

  constructor(
    private productService: ProductService
  ) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Product[] | null> {

    let id: number = route.params["productId"];

    return this.productService.getProductByIdWithFilter(id, ProductFilterToId.SIZE).pipe(catchError(
      err => {
        console.log(err)
        return of(null)
      })
    )
  }
}
