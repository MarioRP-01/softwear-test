import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Product } from '@app/shared/model';
import { Observable, of } from 'rxjs';
import { ProductService } from '../api';

@Injectable({
  providedIn: 'root'
})
export class ProductResolver implements Resolve<Observable<Product>> {
  
  constructor(
    private productService: ProductService
  ) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Product> {

    let id = route.params["productId"];
    return this.productService.getProductById(id).pipe();
  }
}
